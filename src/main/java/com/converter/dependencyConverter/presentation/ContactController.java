package com.converter.dependencyConverter.presentation;

import com.converter.dependencyConverter.presentation.models.EmailModel;
import com.converter.dependencyConverter.services.emailService.EmailSender;
import com.converter.dependencyConverter.services.emailService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    private EmailSender emailSender;

    @Autowired
    public ContactController(EmailService emailService) {
        this.emailSender = emailService;
    }

    @GetMapping("/contact")
    public String sendEmail(Model model){

        EmailModel emailModel = new EmailModel();

        model.addAttribute("emailModel", emailModel);

        return "contact";
    }

    @PostMapping("/contact")
    public String sendEmail(@ModelAttribute EmailModel emailModel){

        emailSender.sendSimpleMessage(emailModel.getFrom()
                , System.getenv("EMAIL"), emailModel.getSubject(), emailModel.getMessage());

        return "contact";
    }
}
