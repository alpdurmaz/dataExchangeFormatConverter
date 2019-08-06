package com.converter.dependencyConverter.services;

import com.converter.dependencyConverter.services.emailService.EmailService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

public class EmailServiceTest {

    private JavaMailSender mockJavaMailSender;
    private EmailService testEmailService;
    private SimpleMailMessage simpleMailMessage;

    private static final String FROM = "TEST@TEST.com";
    private static final String TO = "TEST@TEST.com";
    private static final String SUBJECT = "SUBJECT";
    private static final String MESSAGE = "MESSAGE";

    @Before
    public void setUp(){
        mockJavaMailSender = mock(JavaMailSender.class);
        simpleMailMessage = new SimpleMailMessage();
        testEmailService = new EmailService(mockJavaMailSender);

        simpleMailMessage.setFrom(FROM);
        simpleMailMessage.setTo(TO);
        simpleMailMessage.setSubject(SUBJECT + " FROM : " + FROM);
        simpleMailMessage.setText(MESSAGE);
    }

    @Test
    public void whenSendSimpleMailRunShouldCallSendMethod(){

        testEmailService.sendSimpleMessage(FROM, TO, SUBJECT, MESSAGE);
        verify(mockJavaMailSender, times(1)).send(simpleMailMessage);
    }
}
