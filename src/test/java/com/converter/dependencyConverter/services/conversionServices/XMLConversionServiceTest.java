package com.converter.dependencyConverter.services;

import com.converter.dependencyConverter.services.conversionServices.XMLConversionService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class XMLConversionServiceTest {

    private XMLConversionService testXmlConversionService;

    private static final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<ROOT>\n<TAG1>\n<TAG1-2>tag2</TAG1-2>\n" +
            "<TAG1-3>tag3</TAG1-3>\n</TAG1>\n</ROOT>";

    private static final String json = "{\"ROOT\":{\"TAG1\":{\"TAG1-2\":\"tag2\",\"TAG1-3\":\"tag3\"}}}";

    @Before
    public void setUp(){

        testXmlConversionService = new XMLConversionService();
    }

    @Test
    public void whenConvertXmlToJsonRunShouldReturnJsonFormat(){

        assertThat(testXmlConversionService.convertXmlToJson(xml)).isEqualToIgnoringWhitespace(json);

    }
}
