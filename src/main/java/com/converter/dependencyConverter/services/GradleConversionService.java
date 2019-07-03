package com.converter.dependencyConverter.services;

import org.springframework.stereotype.Service;

@Service
public class GradleConversionService implements DependencyConverter{

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
