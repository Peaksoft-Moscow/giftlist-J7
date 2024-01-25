package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class EmailService {
    JavaMailSender javaMailSender;
    UserRepository userRepository;
    public void sendMessage(String email){
        User user=userRepository.findByEmail(email).orElseThrow(
                ()-> new EntityNotFoundException("Not found bound email"+email));
        if(user!=null) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setFrom("giftList.m7@gmail.com");
            mailMessage.setSubject("Reset Password");
            String massage = " forgot password :\n" + "https://localhost:8080/set-password?code";
            mailMessage.setText(massage);
            javaMailSender.send(mailMessage);
        } else {
            System.out.println("User wish such an email");
        }
    }
    public void generateResetCode(User user){
        Random random=new Random();
//         resetCode=String.valueOf(random.nextInt("777777"));
    }
}
