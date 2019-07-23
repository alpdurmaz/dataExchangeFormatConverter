package com.converter.dependencyConverter.services.conversionServices;

import com.converter.dependencyConverter.exception.exceptions.MavenConversionException;
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

    public List<String> convertDependency(String dependency){

        List<String> gradleList = new ArrayList<>();

        String [] elements = dependency.trim().split("(?=<dependency>)");

        for (String element : elements) {
            String gradle = convertSingleTag(element);

            gradleList.add(gradle);
        }

        return gradleList;
    }

    private Document createDocument(String xml) {

        Document document;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
        } catch (SAXException e) {
            throw new MavenConversionException("An Error Occurred While Parsing XML", e);
        } catch (IOException e) {
            throw new MavenConversionException("An Error Occurred While Reading XML File", e);
        } catch (ParserConfigurationException e) {
            throw new MavenConversionException("An Error Occurred in Parse Configuration", e);
        }

        return document;
    }

    private String createGradleDeclaration(Document document, String mavenPomTag){

        return document.getElementsByTagName(mavenPomTag).item(0).getTextContent();
    }

    private String convertSingleTag(String element) {

        Document document = createDocument(element);

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
