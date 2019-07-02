package com.converter.dependencyConverter;

import com.converter.dependencyConverter.services.MavenConversionService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MavenConversionServiceTest {

    private static final String gradleDependency = "compile group: 'org.scala-lang', name: 'scala-library', version: '2.13.0'";
    private static final String result = "<dependency>\n\t<groupId>org.scala-lang</groupId>\n\t<artifactId>scala-library</artifactId>\n" +
            "\t<version>2.13.0</version>\n</dependency>";

    private MavenConversionService testMavenConversionService;

    @Before
    public void setUp(){
        testMavenConversionService = new MavenConversionService();
    }

    @Test
    public void test(){
        assertThat(testMavenConversionService.convertToMavenDependency(gradleDependency)).isEqualTo(result);
    }
}
