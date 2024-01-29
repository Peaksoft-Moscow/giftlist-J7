package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.exception.IncorrectCodeException;
import com.peakosoft.giftlistj7.model.dto.MailingRequest;
import com.peakosoft.giftlistj7.model.dto.MailingResponse;
import com.peakosoft.giftlistj7.model.dto.mapper.MailingMapper;
import com.peakosoft.giftlistj7.model.dto.mapper.NotificationMapper;
import com.peakosoft.giftlistj7.model.entities.Mailing;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.repository.MailingRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    MailingMapper mailingMapper;
    JavaMailSender javaMailSender;
    UserRepository userRepository;
    MailingRepository mailingRepository;

    public MailingResponse createMailing(MailingRequest request) {
        Mailing email = mailingMapper.mapToEntity(request);
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.isSubscribe()) {
                try {
                    sendMessage(request.getEmail(), request.getSender(), request.getMassage());
                } catch (MailSendException e) {
                    log.info("Error sending email");
                    throw new IncorrectCodeException("Error sending email");
                }
            }
        }
        mailingRepository.save(email);
        return mailingMapper.mapToResponse(email);
    }

    public void sendMessage(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Not found bound email" + email));
        if (user != null) {
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

//    public List<MailSenderResponse> findAll() {
//        return repository.findAll()
//                .stream()
//                .map(mailSenderMapper::mapToResponse).collect(Collectors.toList());
//   }
//    public void generateResetCode(User user) {
//        Random random = new Random();
//         resetCode=String.valueOf(random.nextInt("777777"));
//    }
}
