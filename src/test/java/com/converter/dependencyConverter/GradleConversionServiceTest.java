package com.converter.dependencyConverter;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class GradleConversionServiceTest {

    private GradleConversionService testGradleConversionService;

    private static final String xml = "<dependency>\n<groupId>org.springframework</groupId>\n" +
            "<artifactId>spring-core</artifactId>\n<version>5.1.8.RELEASE</version>\n</dependency>";

    @Before
    public void setUp(){
        testGradleConversionService = new GradleConversionService();
    }

    @Test
    public void whenXmlGivenShouldReturnGradleFormat() throws IOException, SAXException, ParserConfigurationException {

        System.out.println(testGradleConversionService.convertToGradleDependency(xml));
    }
}
