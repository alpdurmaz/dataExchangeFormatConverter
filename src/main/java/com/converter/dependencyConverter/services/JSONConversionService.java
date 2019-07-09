package com.converter.dependencyConverter.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.io.StringReader;

@Service
public class JSONConversionService{

    public String convertJsonToXml(String json) throws JDOMException, IOException {

        JSONObject jsonObject = new JSONObject(json);

        String unformattedXml = XML.toString(jsonObject);

        String formatted = new XMLOutputter(Format.getPrettyFormat()).outputString(new SAXBuilder()
                .build(new StringReader(unformattedXml)));

        return formatted;
    }

    public String convertJsonToYaml(String json) throws IOException {

        JsonNode jsonNode = new ObjectMapper().readTree(json);

        String yaml = new YAMLMapper().writeValueAsString(jsonNode);

        return yaml;
    }
}
