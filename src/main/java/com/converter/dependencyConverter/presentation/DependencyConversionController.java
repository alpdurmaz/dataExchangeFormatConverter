package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.presentation.models.InputOutputModel;
import com.converter.dependencyConverter.services.conversionServices.dependencyConversion.DependencyConversionService;
import com.converter.dependencyConverter.services.conversionServices.dependencyConversion.DependencyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DependencyConversionController {

    private DependencyConverter dependencyConverter;

    @Autowired
    public DependencyConversionController(DependencyConversionService dependencyConversionService) {
        this.dependencyConverter = dependencyConversionService;
    }

    @GetMapping("/convert-dependency")
    public String convertdependency(Model model){

        InputOutputModel inputOutputModel = new InputOutputModel();

        inputOutputModel.setError(false);

        model.addAttribute("inputOutputModel", inputOutputModel);

        return "convertDependency";
    }

    @PostMapping("/convert-dependency")
    public String convertdependency(@ModelAttribute InputOutputModel inputOutputModel) {

        inputOutputModel.setError(false);

        List<String> dependencyList = dependencyConverter.convertDependency(inputOutputModel.getInputOutput());

        String dependency = "";

        for (String element : dependencyList) {
            dependency += element + "\n";

        }

        inputOutputModel.setInputOutput(dependency);

        return "convertDependency";
    }

    @ExceptionHandler(Throwable.class)
    public String handle(Model model){

        InputOutputModel inputOutputModel = new InputOutputModel();
        inputOutputModel.setError(true);

        model.addAttribute("inputOutputModel", inputOutputModel);

        return "convertDependency";
    }
}
