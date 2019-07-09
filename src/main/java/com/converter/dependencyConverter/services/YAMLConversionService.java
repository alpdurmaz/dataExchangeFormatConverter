package com.converter.dependencyConverter.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class YAMLConversionService {

    public String convertYamlToJson(String yaml) throws IOException {

        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());

        Object object = yamlReader.readValue(yaml, Object.class);

        ObjectMapper jsonWriter = new ObjectMapper();

        return jsonWriter.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}
