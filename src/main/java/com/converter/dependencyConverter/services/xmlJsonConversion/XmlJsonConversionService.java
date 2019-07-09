package com.converter.dependencyConverter.services.xmlJsonConversion;

import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;

@Service
public class XmlJsonConversionService {

    public String convert(String format) {

        if(format.startsWith("<")){
            return convertXmlToJson(format);
        }

        if(format.startsWith("{")){
            return convertJsonToXml(format);
        }

        return "Invalid Format";
    }

    public String convertXmlToJson(String xml) {

        JSONObject xmlJson = XML.toJSONObject(xml);

        String jsonString = xmlJson.toString(4);

        return jsonString;
    }

    public String convertJsonToXml(String json) {

        JSONObject jsonObject = new JSONObject(json);

        String unformattedXml = XML.toString(jsonObject);

        String formatted = null;
        try {
            formatted = new XMLOutputter(Format.getPrettyFormat()).
                    outputString(new SAXBuilder().build(new StringReader(unformattedXml)));
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return formatted;
    }
}
