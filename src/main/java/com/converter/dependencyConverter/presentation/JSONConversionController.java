package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.presentation.models.InputOutputModel;
import com.converter.dependencyConverter.services.conversionServices.JSONConversionService;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class JSONConversionController {

    private JSONConversionService jsonConversionService;

    @Autowired
    public JSONConversionController(JSONConversionService jsonConversionService) {
        this.jsonConversionService = jsonConversionService;
    }

    @GetMapping("/json-to-xml")
    public String convertJsonToXml(Model model){

        model.addAttribute("inputOutputModel", new InputOutputModel());

        return "jsonToXml";
    }

    @PostMapping("/json-to-xml")
    public String convertJsonToXml(@ModelAttribute InputOutputModel inputOutputModel) throws JDOMException, IOException {

        String xml = jsonConversionService.convertJsonToXml(inputOutputModel.getInputOutput());

        inputOutputModel.setInputOutput(xml);

        return "jsonToXml";
    }

    @GetMapping("/json-to-yaml")
    public String convertJsonToYaml(Model model){

        model.addAttribute("inputOutputModel", new InputOutputModel());

        return "jsonToYaml";
    }

    @PostMapping("/json-to-yaml")
    public String convertJsonToYaml(@ModelAttribute InputOutputModel inputOutputModel) throws IOException {

        String yaml = jsonConversionService.convertJsonToYaml(inputOutputModel.getInputOutput());

        inputOutputModel.setInputOutput(yaml);

        return "jsonToYaml";
    }
}
