package com.converter.dependencyConverter.services.formatServices;

import com.converter.dependencyConverter.exception.exceptions.JSONConversionException;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;

@Service
public class XMLFormatService {

    public String formatXML(String xml) {

        String formatted;

        try {
            formatted = new XMLOutputter(Format.getPrettyFormat()).outputString(new SAXBuilder()
                    .build(new StringReader(xml)));
        } catch (JDOMException e) {
            throw new JSONConversionException("An Error Occurred While Converting JSON File", e);
        } catch (IOException e) {
            throw new JSONConversionException("An Error Occurred While Reading JSON File", e);
        }

        return formatted;
    }
}
