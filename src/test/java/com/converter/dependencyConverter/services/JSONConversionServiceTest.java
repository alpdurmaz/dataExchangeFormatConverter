package com.converter.dependencyConverter.services;

import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONConversionServiceTest {

    private JSONConversionService testJsonConversionService;

    private static final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<ROOT>\n<TAG1>\n<TAG1-2>tag2</TAG1-2>\n" +
            "<TAG1-3>tag3</TAG1-3>\n</TAG1>\n</ROOT>";

    private static final String json = "{\"ROOT\":{\"TAG1\":{\"TAG1-2\":\"tag2\",\"TAG1-3\":\"tag3\"}}}";

    private static final String yaml = "---\nROOT:\nTAG1:\nTAG1-2: \"tag2\"\nTAG1-3: \"tag3\"";

    @Before
    public void setUp(){
        testJsonConversionService = new JSONConversionService();
    }

    @Test
    public void whenConvertJsonToXmlRunShouldReturnXmlFormat() throws JDOMException, IOException {

        assertThat(testJsonConversionService.convertJsonToXml(json)).isEqualToIgnoringWhitespace(xml);
    }

    @Test
    public void whenConvertJsonToYamlRunShouldReturnYamlFormat() throws IOException {

        assertThat(testJsonConversionService.convertJsonToYaml(json)).isEqualToIgnoringWhitespace(yaml);
    }
}
