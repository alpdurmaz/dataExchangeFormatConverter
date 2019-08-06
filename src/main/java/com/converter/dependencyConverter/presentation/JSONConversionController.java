package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.presentation.models.InputOutputModel;
import com.converter.dependencyConverter.services.conversionServices.jsonConversion.JSONConversionService;
import com.converter.dependencyConverter.services.conversionServices.jsonConversion.JSONConverter;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class JSONConversionController {

    private JSONConverter jsonConverter;
    private String pageInfo;

    @Autowired
    public JSONConversionController(JSONConversionService jsonConversionService) {
        this.jsonConverter = jsonConversionService;
    }

    @GetMapping("/json-to-xml")
    public String convertJsonToXml(Model model){

        InputOutputModel inputOutputModel = new InputOutputModel();
        inputOutputModel.setError(false);

        model.addAttribute("inputOutputModel", inputOutputModel);

        pageInfo = "jsontoxml";

        return "jsonToXml";
    }

    @PostMapping("/json-to-xml")
    public String convertJsonToXml(@ModelAttribute InputOutputModel inputOutputModel) throws JDOMException, IOException {

        inputOutputModel.setError(false);

        String xml = jsonConverter.convertJsonToXml(inputOutputModel.getInputOutput());

        inputOutputModel.setInputOutput(xml);

        return "jsonToXml";
    }

    @GetMapping("/json-to-yaml")
    public String convertJsonToYaml(Model model){

        InputOutputModel inputOutputModel = new InputOutputModel();
        inputOutputModel.setError(false);

        model.addAttribute("inputOutputModel", inputOutputModel);

        pageInfo = "jsontoyaml";

        return "jsonToYaml";
    }

    @PostMapping("/json-to-yaml")
    public String convertJsonToYaml(@ModelAttribute InputOutputModel inputOutputModel) throws IOException {

        inputOutputModel.setError(false);

        String yaml = jsonConverter.convertJsonToYaml(inputOutputModel.getInputOutput());

        inputOutputModel.setInputOutput(yaml);

        return "jsonToYaml";
    }

    @ExceptionHandler(Throwable.class)
    public String handleForJsonToXml(Model model){

        InputOutputModel inputOutputModel = new InputOutputModel();

        inputOutputModel.setError(true);

        model.addAttribute("inputOutputModel", inputOutputModel);

        if(pageInfo.equals("jsontoxml")){
            return "jsonToXml";
        }

        else if (pageInfo.equals("jsontoyaml")){
            return "jsonToYaml";
        }

        return "home";
    }
}
