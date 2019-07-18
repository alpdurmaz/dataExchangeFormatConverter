package com.converter.dependencyConverter.services.dependencyConversionServices;

import com.converter.dependencyConverter.services.dependencyConversionServices.MavenConversionService;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class MavenConversionServiceTest {

    private MavenConversionService testMavenConversionService;

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
        testMavenConversionService = new MavenConversionService();
    }

    @Test
    public void whenXmlGivenShouldReturnGradleFormat() throws IOException, SAXException, ParserConfigurationException {

        assertThat(testMavenConversionService.convertDependency(xml).get(0)).isEqualTo(result);
    }

    @Test
    public void whenXmlTestScopeGivenShouldReturnResultTestScopeFormat() throws IOException, SAXException, ParserConfigurationException {

        assertThat(testMavenConversionService.convertDependency(xmlTestScope)).isEqualTo(resultTestScope);
    }

    @Test(expected = RuntimeException.class)
    public void whenBadXmlGivenShouldThrownException() throws IOException, SAXException, ParserConfigurationException {

        testMavenConversionService.convertDependency(badXml);
    }
}
