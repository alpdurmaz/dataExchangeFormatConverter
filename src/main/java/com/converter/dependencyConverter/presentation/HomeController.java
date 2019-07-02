package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.services.GradleConversionService;
import com.converter.dependencyConverter.services.MavenConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private GradleConversionService gradleConversionService;
    private MavenConversionService mavenConversionService;

    @GetMapping("/")
    public String convert(Model model){

        return "convertDependency";
    }

}
