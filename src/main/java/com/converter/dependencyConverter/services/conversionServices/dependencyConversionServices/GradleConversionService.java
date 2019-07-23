package com.converter.dependencyConverter.services.conversionServices.dependencyConversionServices;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradleConversionService implements DependencyConverter{

    public List<String> convertDependency(String dependency){

        List<String> mavenDependencyList = new ArrayList<>();

        String [] lines = dependency.split("[\\n]+");

        for (String singleDependency : lines) {

            String [] elements = singleDependency.split("[:,]+");

            if(!validateElements(elements)){
                throw new RuntimeException("Invalid Expression in " + singleDependency);
            }

            String groupId = elements[1].replace('\'',' ').trim();
            String artifactId = elements[3].replace('\'',' ').trim();
            String version = elements[5].replace('\'',' ').trim();

            String singleOutput = "<dependency>\n\t<groupId>" + groupId + "</groupId>\n\t" + "<artifactId>" + artifactId + "</artifactId>\n\t"
                + "<version>" + version + "</version>\n" + "</dependency>";

            mavenDependencyList.add(singleOutput);
        }

        return mavenDependencyList;
    }

    private boolean validateElements(String [] elements) {

        boolean flag = true;

        if(!elements[0].trim().equals("compile group")){
            if(!elements[0].trim().equals("testCompile group")) {
                flag = false;
            }
        }

        if(!elements[2].trim().equals("name")){
            flag = false;
        }

        if(!elements[4].trim().equals("version")){
            flag = false;
        }

        if(elements[1].startsWith("'") &&  elements[1].endsWith("'")
                && elements[3].startsWith("'") && elements[3].endsWith("")
                && elements[5].startsWith("'") && elements[5].endsWith("'")){
            System.out.println("element[3-5]");
            flag = false;
        }

        return flag;
    }
}
