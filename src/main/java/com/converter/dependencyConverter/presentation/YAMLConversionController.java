package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.presentation.models.InputOutputModel;
import com.converter.dependencyConverter.services.JSONConversionService;
import com.converter.dependencyConverter.services.YAMLConversionService;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class YAMLConversionController {

    private YAMLConversionService yamlConversionService;
    private JSONConversionService jsonConversionService;

    @Autowired
    public YAMLConversionController(YAMLConversionService yamlConversionService, JSONConversionService jsonConversionService) {
        this.yamlConversionService = yamlConversionService;
        this.jsonConversionService = jsonConversionService;
    }

    @GetMapping("/yaml-to-json")
    public String convertYamltoJson(Model model){

        model.addAttribute("inputOutputModel", new InputOutputModel());

        return "yamlToJson";
    }

    @PostMapping("/yaml-to-json")
    public String convertYamltoJson(@ModelAttribute InputOutputModel inputOutputModel) throws IOException {

        String json = yamlConversionService.convertYamlToJson(inputOutputModel.getInputOutput());

        inputOutputModel.setInputOutput(json);

        return "yamlToJson";
    }

    @GetMapping("/yaml-to-xml")
    public String convertYamltoXml(Model model){

        model.addAttribute("inputOutputModel", new InputOutputModel());

        return "yamlToXml";
    }

    @PostMapping("/yaml-to-xml")
    public String convertYamltoXml(@ModelAttribute InputOutputModel inputOutputModel) throws IOException, JDOMException {

        String json = yamlConversionService.convertYamlToJson(inputOutputModel.getInputOutput());

        String xml = jsonConversionService.convertJsonToXml(json);

        inputOutputModel.setInputOutput(json);

        return "yamlToXml";
    }
}
