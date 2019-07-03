package com.converter.dependencyConverter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DependencyConversionService implements DependencyConverter{

    private DependencyConverter dependencyConverter;
    private MavenConversionService mavenConversionService;
    private GradleConversionService gradleConversionService;

    @Autowired
    public DependencyConversionService(MavenConversionService mavenConversionService, GradleConversionService gradleConversionService) {
        this.mavenConversionService = mavenConversionService;
        this.gradleConversionService = gradleConversionService;
    }

    private void recognize(String dependency){

        if(dependency.startsWith("<") && dependency.endsWith(">")){
            dependencyConverter = mavenConversionService;
        }

        else{
            dependencyConverter = gradleConversionService;
        }
    }

    @Override
    public String convertDependency(String dependency) {

        recognize(dependency);

        return dependencyConverter.convertDependency(dependency);
    }
}
