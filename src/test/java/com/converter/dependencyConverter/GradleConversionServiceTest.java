package com.converter.dependencyConverter;

import com.converter.dependencyConverter.services.GradleConversionService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GradleConversionServiceTest {

    private static final String gradleDependency = "compile group: 'org.scala-lang', name: 'scala-library', version: '2.13.0'";
    private static final String result = "<dependency>\n\t<groupId>org.scala-lang</groupId>\n\t<artifactId>scala-library</artifactId>\n" +
            "\t<version>2.13.0</version>\n</dependency>";

    private GradleConversionService testGradleConversionService;

    @Before
    public void setUp(){
        testGradleConversionService = new GradleConversionService();
    }

    @Test
    public void test(){
        assertThat(testGradleConversionService.convertDependency(gradleDependency)).isEqualTo(result);
    }
}
