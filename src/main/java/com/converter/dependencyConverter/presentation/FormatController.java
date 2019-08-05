package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.presentation.models.InputOutputModel;
import com.converter.dependencyConverter.services.formatServices.XMLFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

@Controller
public class FormatController {

    private XMLFormatService xmlFormatService;

    @Autowired
    public FormatController(XMLFormatService xmlFormatService) {
        this.xmlFormatService = xmlFormatService;
    }

    @GetMapping("/formatXML")
    public String formatXML(Model model){

        InputOutputModel inputOutputModel = new InputOutputModel();

        model.addAttribute("inputOutputModel", inputOutputModel);

        return "formatXml";
    }

    @PostMapping("/formatXML")
    public String formatXML(@ModelAttribute InputOutputModel inputOutputModel) throws ParserConfigurationException, TransformerException, SAXException, IOException {

        inputOutputModel.setError(false);

        String unformatted = inputOutputModel.getInputOutput();
        String formatted = xmlFormatService.formatXML(unformatted);

        inputOutputModel.setInputOutput(formatted);

        return "formatXml";
    }

    @ExceptionHandler(Throwable.class)
    public String handleForJsonToXml(Model model) {

        InputOutputModel inputOutputModel = new InputOutputModel();

        inputOutputModel.setError(true);

        model.addAttribute("inputOutputModel", inputOutputModel);

        return "formatXml";
    }
}
