package com.converter.dependencyConverter.services.dependencyConversionServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        dependency = dependency.replaceFirst("[\n]$", "");
        dependency = dependency.replaceFirst("[\r]$", "");
        dependency = dependency.trim();

        if(dependency.startsWith("<") && dependency.endsWith(">")){
            dependencyConverter = mavenConversionService;
        }

        else{
            dependencyConverter = gradleConversionService;
        }
    }

    @Override
    public List<String> convertDependency(String dependency) {

        recognize(dependency);

        return dependencyConverter.convertDependency(dependency.trim());
    }
}
