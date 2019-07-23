package com.converter.dependencyConverter.services.conversionServices.dependencyConversionServices;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GradleConversionServiceTest {

    private static final String gradleDependency = "compile group: 'com.amazonaws', name: 'aws-java-sdk', version: '1.11.586'";

    private static final String result = "<dependency>\n\t<groupId>com.amazonaws</groupId>\n" +
            "\t<artifactId>aws-java-sdk</artifactId>\n\t<version>1.11.586</version>\n</dependency>";

    private GradleConversionService testGradleConversionService;

    @Before
    public void setUp(){
        testGradleConversionService = new GradleConversionService();
    }

    @Test
    public void whenDependencyListGivenShouldReturnXmlFormat(){

        assertThat(testGradleConversionService.convertDependency(gradleDependency)).isEqualTo(result);
    }
}
