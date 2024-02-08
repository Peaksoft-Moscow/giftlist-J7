package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.exception.NotFoundException;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
@RequiredArgsConstructor
public class MailSenderService {
    private final UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    private String username;
    public void send(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
    public void sendToFriends(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
    public void sendComplaints( String emailFrom, String status, String description) {
        User admin = userRepository.findById(1L).orElseThrow(()-> new NotFoundException("Not found user by id: " + 1));
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailFrom);
        mailMessage.setTo(admin.getEmail());
        mailMessage.setSubject(status);
        mailMessage.setText(description);
        mailSender.send(mailMessage);
    }

}



