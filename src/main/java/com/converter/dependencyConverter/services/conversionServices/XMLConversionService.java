package com.converter.dependencyConverter.services.conversionServices;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

@Service
public class XMLConversionService {

    public String convertXmlToJson(String xml) {

        JSONObject xmlJson = XML.toJSONObject(xml);

        String jsonString = xmlJson.toString(4);

        return jsonString;
    }
}
