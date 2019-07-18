package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.presentation.models.InputOutputModel;
import com.converter.dependencyConverter.services.JSONConversionService;
import com.converter.dependencyConverter.services.XMLConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class XMLConversionController {

    private XMLConversionService xmlConversionService;
    private JSONConversionService jsonConversionService;

    @Autowired
    public XMLConversionController(XMLConversionService xmlConversionService, JSONConversionService jsonConversionService) {
        this.xmlConversionService = xmlConversionService;
        this.jsonConversionService = jsonConversionService;
    }

    @GetMapping("/xml-to-json")
    public String convertXmlToJson(Model model){

        model.addAttribute("inputOutputModel", new InputOutputModel());

        return "xmlToJson";
    }

    @PostMapping("/xml-to-json")
    public String convertXmlToJson(@ModelAttribute InputOutputModel inputOutputModel){

        String json = xmlConversionService.convertXmlToJson(inputOutputModel.getInputOutput());

        inputOutputModel.setInputOutput(json);

        return "xmlToJson";
    }

    @GetMapping("/xml-to-yaml")
    public String convertXmlToYaml(Model model){

        model.addAttribute("inputOutputModel", new InputOutputModel());

        return "xmlToYaml";
    }

    @PostMapping("/xml-to-yaml")
    public String convertXmlToYaml(@ModelAttribute InputOutputModel inputOutputModel) throws IOException {

        String json = xmlConversionService.convertXmlToJson(inputOutputModel.getInputOutput());

        String yaml = jsonConversionService.convertJsonToYaml(json);

        inputOutputModel.setInputOutput(yaml);

        return "xmlToYaml";
    }
}
