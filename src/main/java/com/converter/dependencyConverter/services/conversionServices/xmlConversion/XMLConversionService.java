package com.converter.dependencyConverter.services.conversionServices.xmlConversion;

import com.converter.dependencyConverter.services.conversionServices.xmlConversion.XMLConverter;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

@Service
public class XMLConversionService implements XMLConverter {

    public String convertXmlToJson(String xml) {

        JSONObject xmlJson = XML.toJSONObject(xml);

        String jsonString = xmlJson.toString(4);

        return jsonString;
    }
}
