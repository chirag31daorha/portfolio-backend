package com.chirag.portfolio.service;

import com.chirag.portfolio.dto.ContactRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${portfolio.receiver-email}")
    private String receiverEmail;

    public void sendContactEmail(ContactRequest request) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(receiverEmail);
        message.setSubject("[Portfolio] " + request.getSubject());
        message.setReplyTo(request.getEmail());
        message.setText(
            "Name: " + request.getName() + "\n" +
            "Email: " + request.getEmail() + "\n\n" +
            "Message:\n" + request.getMessage()
        );

        mailSender.send(message);
    }
}