package com.converter.dependencyConverter.services.conversionServices.jsonConversion;

import com.converter.dependencyConverter.exception.exceptions.JSONConversionException;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class JSONConversionService implements JSONConverter{

    public String convertJsonToXml(String json) {

        JSONObject jsonObject = new JSONObject(json);

        String unformattedXml = XML.toString(jsonObject);

        String formatted;
        try {
            formatted = new XMLOutputter(Format.getPrettyFormat()).outputString(new SAXBuilder()
                    .build(new StringReader(unformattedXml)));
        } catch (JDOMException e) {
            throw new JSONConversionException("An Error Occurred While Converting JSON File", e);
        } catch (IOException e) {
            throw new JSONConversionException("An Error Occurred While Reading JSON File", e);
        }

        return formatted;
    }

    public String convertJsonToYaml(String json){

        JsonNode jsonNode;
        try {
            jsonNode = new ObjectMapper().readTree(json);
        } catch (IOException e) {
            throw new JSONConversionException("An Error Occurred While Reading JSON File", e);
        }

        String yaml;
        try {
            yaml = new YAMLMapper().writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            throw new JSONConversionException("An Error Occurred While Processing JSON File", e);
        }

        return yaml;
    }
}
