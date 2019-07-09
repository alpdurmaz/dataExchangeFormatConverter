package com.converter.dependencyConverter.xmlToJsonConversion;

import com.converter.dependencyConverter.services.xmlJsonConversion.XmlJsonConversionService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlJsonConversionServiceTest {

    private XmlJsonConversionService testXmlJsonConversionService;

    private static final String xml = "<ROOT>\n" +
            "        <TAG1>\n" +
            "            <TAG1-2>tag2</TAG1-2>\n" +
            "            <TAG1-3>tag3</TAG1-3>\n" +
            "        </TAG1>\n" +
            "    </ROOT>";


    private static final String json = "{\"ROOT\":{\"TAG1\":{\"TAG1-2\":\"tag2\",\"TAG1-3\":\"tag3\"}}}";

    @Before
    public void setUp(){

        testXmlJsonConversionService = new XmlJsonConversionService();
    }


    @Test
    public void whenXmlGivenShouldReturnJsonFormat() {

        assertThat(testXmlJsonConversionService.convertXmlToJson(xml)).isEqualToIgnoringWhitespace(json);

    }

    @Test
    public void whenJsonGivenShouldReturnXml(){

        assertThat(testXmlJsonConversionService.convert(json)).isEqualToIgnoringWhitespace(xml);
    }
}
