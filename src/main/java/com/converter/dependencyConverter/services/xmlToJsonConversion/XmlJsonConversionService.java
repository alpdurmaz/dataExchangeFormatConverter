package com.converter.dependencyConverter.services.xmlToJsonConversion;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

@Service
public class XmlJsonConversionService {

    public String convert(String format){

        if(format.startsWith("<")){
            if(format.endsWith(">")){
                return convertXmlToJson(format);
            }
        }

        if(format.startsWith("{")){
            if(format.endsWith("}")){
                return convertJsonToXml(format);
            }
        }

        return "Invalid Format";
    }

    public String convertXmlToJson(String xml) {

        JSONObject xmlJson = XML.toJSONObject(xml);

        String jsonString = xmlJson.toString(4);

        return jsonString;
    }

    public String convertJsonToXml(String json){

        JSONObject jsonObject = new JSONObject(json);

        return XML.toString(jsonObject);
    }
}
