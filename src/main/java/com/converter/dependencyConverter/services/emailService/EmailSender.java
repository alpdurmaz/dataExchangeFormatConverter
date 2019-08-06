package com.converter.dependencyConverter.services.emailService;

public interface EmailSender {
    void sendSimpleMessage(String from, String to, String subject, String text);
}
