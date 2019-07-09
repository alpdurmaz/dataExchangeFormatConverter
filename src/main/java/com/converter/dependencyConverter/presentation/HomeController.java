package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.presentation.model.InputOutputModel;
import com.converter.dependencyConverter.services.dependencyConversionServices.DependencyConversionService;
import com.converter.dependencyConverter.services.xmlJsonConversion.XmlJsonConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    private DependencyConversionService dependencyConversionService;
    private XmlJsonConversionService xmlJsonConversionService;

    @Autowired
    public HomeController(DependencyConversionService dependencyConversionService, XmlJsonConversionService xmlJsonConversionService) {
        this.dependencyConversionService = dependencyConversionService;
        this.xmlJsonConversionService = xmlJsonConversionService;
    }

    @GetMapping("/index")
    public String convertdependency(Model model){

        model.addAttribute("inputOutputModel", new InputOutputModel());

        return "index";
    }

    @PostMapping("/index")
    public String convertdependency(@ModelAttribute InputOutputModel inputOutputModel){

        List<String> dependencyList = dependencyConversionService.convertDependency(inputOutputModel.getDependency());

        String dependency = "";

        for (String element : dependencyList) {
            dependency += element + "\n";

        }

        inputOutputModel.setDependency(dependency);

        return "index";
    }

    @GetMapping("/xmlJson")
    public String convertXmlToJson(Model model){

        model.addAttribute("inputOutputModel", new InputOutputModel());

        return "xmlJson";
    }

    @PostMapping("/xmlJson")
    public String convertXmlToJson(@ModelAttribute InputOutputModel inputOutputModel){

        String json = xmlJsonConversionService.convert(inputOutputModel.getDependency());

        inputOutputModel.setDependency(json);

        return "xmlJson";
    }
}
