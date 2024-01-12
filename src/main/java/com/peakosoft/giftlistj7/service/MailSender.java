package com.peakosoft.giftlistj7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class MailSender {
    @Autowired
    private JavaMailSender mailSender;
    private String username;
    @Autowired
    public void send(String emailTo, String subject, String massage) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(massage);
        mailSender.send(mailMessage);
    }

}
