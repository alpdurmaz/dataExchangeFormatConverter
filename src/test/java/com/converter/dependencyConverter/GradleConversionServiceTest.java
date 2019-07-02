package com.converter.dependencyConverter;

import com.converter.dependencyConverter.services.GradleConversionService;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class GradleConversionServiceTest {

    private GradleConversionService testGradleConversionService;

    private static final String xml = "<dependency>\n<groupId>org.springframework</groupId>\n" +
            "<artifactId>spring-core</artifactId>\n<version>5.1.8.RELEASE</version>\n</dependency>";

    private static final String result = "compile group:'org.springframework', name:spring-core', version:'5.1.8.RELEASE'";

    private static final String xmlTestScope = "<dependency>\n<groupId>org.springframework</groupId>\n" +
            "<artifactId>spring-core</artifactId>\n<version>5.1.8.RELEASE</version>\n<scope>test</scope>\n</dependency>";

    private static final String resultTestScope = "testCompile group:'org.springframework', name:spring-core', version:'5.1.8.RELEASE'";

    private static final String badXml = "<dep>\n<groupId>org.springframework</groupId>\n" +
            "<artifactId>spring-core</artifactId>\n<version>5.1.8.RELEASE</version>\n</dep>";

    @Before
    public void setUp(){
        testGradleConversionService = new GradleConversionService();
    }

    @Test
    public void whenXmlGivenShouldReturnGradleFormat() throws IOException, SAXException, ParserConfigurationException {

        assertThat(testGradleConversionService.convertToGradleDependency(xml)).isEqualTo(result);
    }

    @Test
    public void whenXmlTestScopeGivenShouldReturnResultTestScopeFormat() throws IOException, SAXException, ParserConfigurationException {

        assertThat(testGradleConversionService.convertToGradleDependency(xmlTestScope)).isEqualTo(resultTestScope);
    }

    @Test(expected = RuntimeException.class)
    public void whenBadXmlGivenShouldThrownException() throws IOException, SAXException, ParserConfigurationException {

        testGradleConversionService.convertToGradleDependency(badXml);
    }
}
