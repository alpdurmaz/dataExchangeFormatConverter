package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.presentation.models.EmailModel;
import com.converter.dependencyConverter.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    private EmailService emailService;

    @Autowired
    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/contact")
    public String sendEmail(Model model){

        EmailModel emailModel = new EmailModel();

        model.addAttribute("emailModel", emailModel);

        return "contact";
    }

    @PostMapping("/contact")
    public String sendEmail(@ModelAttribute EmailModel emailModel){

        emailService.sendSimpleMessage(emailModel.getFrom()
                , System.getenv("EMAIL"), emailModel.getSubject(), emailModel.getMessage());

        return "contact";
    }
}
