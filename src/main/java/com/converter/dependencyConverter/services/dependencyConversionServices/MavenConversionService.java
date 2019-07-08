package com.converter.dependencyConverter.services.dependencyConversionServices;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class MavenConversionService implements DependencyConverter {

    public List<String> convertDependency(String dependency) {

        List<String> gradleList = new ArrayList<>();

        String [] elements = dependency.trim().split("(?=<dependency>)");

        for (String element : elements) {
            String gradle = convertSingleTag(element);

            gradleList.add(gradle);
        }

        return gradleList;
    }

    private Document createDocument(String xml) throws ParserConfigurationException, IOException, SAXException {

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));

        return document;
    }

    private String createGradleDeclaration(Document document, String mavenPomTag){

        return document.getElementsByTagName(mavenPomTag).item(0).getTextContent();
    }

    private String convertSingleTag(String element){

        Document document = null;
        try {
            document = createDocument(element);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        if(document.getElementsByTagName("dependency").item(0) == null){
            throw new RuntimeException("Invalid Dependency");
        }

        String compileGroup = createGradleDeclaration(document, "groupId");
        String name = createGradleDeclaration(document, "artifactId");
        String version = createGradleDeclaration(document, "version");

        if(document.getElementsByTagName("scope").item(0) != null){
            if(document.getElementsByTagName("scope").item(0)
                    .getTextContent().equalsIgnoreCase("test")){
                return "testCompile group:'" + compileGroup + "'" +  ", " + "name" +  ":" + name + "'" + ", " +  "version" + ":"
                        + "'" + version + "'";
            }
        }

        return "compile group:'" + compileGroup + "'" +  ", " + "name" +  ":" + name + "'" + ", " +  "version" + ":"
                + "'" + version + "'";

    }
}
