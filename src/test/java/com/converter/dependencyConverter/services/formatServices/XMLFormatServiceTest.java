package com.converter.dependencyConverter.services.formatServices;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class XMLConversionServiceTest {

    private static final String XML = "<tag>content</tag>";
    private XMLFormatService testXmlFormatService;

    @Before
    public void setUp(){
        testXmlFormatService = new XMLFormatService();

    }

    @Test
    public void whenFormatXMLCall_shouldReturnString(){

        assertThat(testXmlFormatService.formatXML(XML)).isInstanceOf(String.class);

    }
}
