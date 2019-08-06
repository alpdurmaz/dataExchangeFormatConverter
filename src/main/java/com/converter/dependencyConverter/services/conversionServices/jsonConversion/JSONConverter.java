package com.converter.dependencyConverter.services.conversionServices.jsonConversion;

public interface JSONConverter {
    String convertJsonToXml(String json);
    String convertJsonToYaml(String json);
}
