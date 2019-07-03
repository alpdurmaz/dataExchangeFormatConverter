package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.presentation.model.InputOutputModel;
import com.converter.dependencyConverter.services.DependencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private DependencyConversionService dependencyConversionService;

    @Autowired
    public HomeController(DependencyConversionService dependencyConversionService) {
        this.dependencyConversionService = dependencyConversionService;
    }

    @GetMapping("/index")
    public String convert(Model model){

        model.addAttribute("inputOutputModel", new InputOutputModel());

        return "index";
    }

    @PostMapping("/index")
    public String convert(@ModelAttribute InputOutputModel inputOutputModel){

        String result = dependencyConversionService.convertDependency(inputOutputModel.getDependency());

        inputOutputModel.setDependency(result);

        return "index";
    }

}
