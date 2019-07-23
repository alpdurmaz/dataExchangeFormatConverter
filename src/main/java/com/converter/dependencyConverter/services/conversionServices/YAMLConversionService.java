package com.converter.dependencyConverter.services.conversionServices;

import com.converter.dependencyConverter.exception.exceptions.JSONConversionException;
import com.converter.dependencyConverter.exception.exceptions.YAMLConversionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class YAMLConversionService {

    public String convertYamlToJson(String yaml) {

        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());

        Object object;
        try {
            object = yamlReader.readValue(yaml, Object.class);
        } catch (IOException e) {
            throw new YAMLConversionException("An Error Occurred While Reading YAML File", e);
        }

        ObjectMapper jsonWriter = new ObjectMapper();

        try {
            return jsonWriter.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JSONConversionException("An Error Occurred While Processing JSON File", e);
        }
    }
}
