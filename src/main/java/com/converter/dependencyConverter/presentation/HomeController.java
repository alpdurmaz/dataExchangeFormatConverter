package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.presentation.model.InputOutputModel;
import com.converter.dependencyConverter.services.JSONConversionService;
import com.converter.dependencyConverter.services.XMLConversionService;
import com.converter.dependencyConverter.services.YAMLConversionService;
import com.converter.dependencyConverter.services.dependencyConversionServices.DependencyConversionService;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

    private DependencyConversionService dependencyConversionService;
    private XMLConversionService xmlConversionService;
    private JSONConversionService jsonConversionService;
    private YAMLConversionService yamlConversionService;

    @Autowired
    public HomeController(DependencyConversionService dependencyConversionService
            , XMLConversionService xmlConversionService
            , JSONConversionService jsonConversionService, YAMLConversionService yamlConversionService) {

        this.dependencyConversionService = dependencyConversionService;
        this.xmlConversionService = xmlConversionService;
        this.jsonConversionService = jsonConversionService;
        this.yamlConversionService = yamlConversionService;
    }

    @GetMapping("/")
    public String home(){

        return "home";
    }

    @GetMapping("/convert-dependency")
    public String convertdependency(Model model){

        model.addAttribute("inputOutputModel", new InputOutputModel());

        return "convertDependency";
    }

    @PostMapping("/convert-dependency")
    public String convertdependency(@ModelAttribute InputOutputModel inputOutputModel){

        List<String> dependencyList = dependencyConversionService.convertDependency(inputOutputModel.getInputOutput());

        String dependency = "";

        for (String element : dependencyList) {
            dependency += element + "\n";

        }

        inputOutputModel.setInputOutput(dependency);

        return "convertDependency";
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
