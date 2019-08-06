package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.presentation.models.InputOutputModel;
import com.converter.dependencyConverter.services.conversionServices.jsonConversion.JSONConversionService;
import com.converter.dependencyConverter.services.conversionServices.xmlConversion.XMLConversionService;
import com.converter.dependencyConverter.services.conversionServices.xmlConversion.XMLConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class XMLConversionController {

    private XMLConverter xmlConverter;
    private JSONConversionService jsonConversionService;
    private String pageInfo;

    @Autowired
    public XMLConversionController(XMLConversionService xmlConversionService, JSONConversionService jsonConversionService) {
        this.xmlConverter = xmlConversionService;
        this.jsonConversionService = jsonConversionService;
    }

    @GetMapping("/xml-to-json")
    public String convertXmlToJson(Model model){

        InputOutputModel inputOutputModel = new InputOutputModel();

        model.addAttribute("inputOutputModel", inputOutputModel);

        pageInfo = "xmltojson";

        return "xmlToJson";
    }

    @PostMapping("/xml-to-json")
    public String convertXmlToJson(@ModelAttribute InputOutputModel inputOutputModel){

        inputOutputModel.setError(false);

        String json = xmlConverter.convertXmlToJson(inputOutputModel.getInputOutput());

        inputOutputModel.setInputOutput(json);

        return "xmlToJson";
    }

    @GetMapping("/xml-to-yaml")
    public String convertXmlToYaml(Model model){

        InputOutputModel inputOutputModel = new InputOutputModel();

        model.addAttribute("inputOutputModel", inputOutputModel);

        pageInfo = "xmltoyaml";

        return "xmlToYaml";
    }

    @PostMapping("/xml-to-yaml")
    public String convertXmlToYaml(@ModelAttribute InputOutputModel inputOutputModel) throws IOException {

        inputOutputModel.setError(false);

        String json = xmlConverter.convertXmlToJson(inputOutputModel.getInputOutput());

        String yaml = jsonConversionService.convertJsonToYaml(json);

        inputOutputModel.setInputOutput(yaml);

        return "xmlToYaml";
    }

    @ExceptionHandler(Throwable.class)
    public String handleForJsonToXml(Model model){

        InputOutputModel inputOutputModel = new InputOutputModel();

        inputOutputModel.setError(true);

        model.addAttribute("inputOutputModel", inputOutputModel);

        if(pageInfo.equals("xmltojson")){
            return "xmlToJson";
        }

        else if (pageInfo.equals("xmltoyaml")){
            return "xmlToYaml";
        }

        return "home";
    }
}
