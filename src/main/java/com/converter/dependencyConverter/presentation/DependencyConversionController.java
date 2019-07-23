package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.presentation.models.InputOutputModel;
import com.converter.dependencyConverter.services.conversionServices.dependencyConversionServices.DependencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DependencyConversionController {

    private DependencyConversionService dependencyConversionService;

    @Autowired
    public DependencyConversionController(DependencyConversionService dependencyConversionService) {
        this.dependencyConversionService = dependencyConversionService;
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
}
