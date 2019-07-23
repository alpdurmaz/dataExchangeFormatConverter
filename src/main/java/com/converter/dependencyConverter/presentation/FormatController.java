package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.presentation.models.InputOutputModel;
import com.converter.dependencyConverter.services.formatServices.XMLFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormatControllers {

    private XMLFormatService xmlFormatService;

    @Autowired
    public FormatControllers(XMLFormatService xmlFormatService) {
        this.xmlFormatService = xmlFormatService;
    }

    @GetMapping("/formatXML")
    public String formatXML(Model model){

        model.addAttribute("inputOutputModel", new InputOutputModel());

        return "formatXml";
    }

    @PostMapping("/formatXML")
    public String formatXML(@ModelAttribute InputOutputModel inputOutputModel){

        String unformatted = inputOutputModel.getInputOutput();
        String formatted = xmlFormatService.formatXML(unformatted);

        inputOutputModel.setInputOutput(formatted);

        return "formatXml";
    }
}
