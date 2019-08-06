package com.converter.dependencyConverter.services.conversionServices;

import com.converter.dependencyConverter.services.conversionServices.yamlConversion.YAMLConversionService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class YAMLConversionServiceTest {

    private YAMLConversionService testYamlConversionService;

    private static final String json = "{\"ROOT\":{\"TAG1\":{\"TAG1-1\":\"val1\",\"TAG1-2\":\"val2\"}}}";

    private static final String yaml = "ROOT:\n" +
            "    TAG1:\n" +
            "        TAG1-1: \"val1\"\n" +
            "        TAG1-2: \"val2\"";


    @Before
    public void setUp(){
        testYamlConversionService = new YAMLConversionService();
    }

    @Test
    public void whenConvertYamlToJsonRunShouldReturnJsonFormat() throws IOException {

        assertThat(testYamlConversionService.convertYamlToJson(yaml)).isEqualToIgnoringWhitespace(json);

    }
}
