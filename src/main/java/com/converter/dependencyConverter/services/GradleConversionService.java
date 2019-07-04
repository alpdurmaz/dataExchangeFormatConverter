package com.converter.dependencyConverter.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GradleConversionService implements DependencyConverter{

    public List<String> convertAsList(String dependency){

        List<String> mavenDependencyList = Arrays.asList();

        String [] lines = dependency.split("\\n");

        for (String singleDependency : lines) {

            String [] elements = singleDependency.split("[:,]");

            if(!validateElements(elements)){
                throw new RuntimeException("Invalid Expression in" + singleDependency);
            }

            String groupId = elements[1].replace('\'',' ');
            String artifactId = elements[3].replace('\'',' ');
            String version = elements[5].replace('\'',' ');

            String singleOutput = "<dependency>\n\t<groupId>" + groupId + "</groupId>\n\t" + "<artifactId>" + artifactId + "</artifactId>\n\t"
                + "<version>" + version + "</version>\n" + "</dependency>";

            mavenDependencyList.add(singleOutput);
        }

        return mavenDependencyList;
    }

    private boolean validateElements(String [] elements) {

        boolean flag = true;

        if(!elements[0].equals("compile group") || !elements[0].equals("testCompile group")){
            flag = false;
        }

        if(!elements[2].equals("name")){
            flag = false;
        }

        if(!elements[4].equals("version")){
            flag = false;
        }

        if(elements[1].startsWith("'") &&  elements[1].endsWith("'")
                && elements[3].startsWith("'") && elements[3].endsWith("")
                && elements[5].startsWith("'") && elements[5].endsWith("'")){
            flag = false;
        }

        return flag;
    }



    public String convertDependency(String dependency){

        String [] elements = dependency.split(",");
        String groupId = "", artifactId = "", version = "";

        for (String element : elements) {

            if(element.split("'").length < 2){
                continue;
            }

            if(element.contains("compile group") || element.contains("testCompile group")){
                groupId = element.split("'")[1];
            }

            if(element.contains("name")){
                artifactId = element.split("'")[1];
            }

            if(element.contains("version")){
                version = element.split("'")[1];
            }
        }

        return "<dependency>\n\t<groupId>" + groupId + "</groupId>\n\t" + "<artifactId>" + artifactId + "</artifactId>\n\t"
                + "<version>" + version + "</version>\n" + "</dependency>";

    }
}
