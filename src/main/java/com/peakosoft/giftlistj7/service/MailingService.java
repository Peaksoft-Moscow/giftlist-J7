package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.dto.AuthResponse;
import com.peakosoft.giftlistj7.model.dto.MailRequest;
import com.peakosoft.giftlistj7.model.dto.MailResponse;
import com.peakosoft.giftlistj7.model.dto.mapper.MailingMapper;
import com.peakosoft.giftlistj7.model.dto.mapper.UserMapper;
import com.peakosoft.giftlistj7.model.entities.Mailing;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.repository.MailingRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailingService {

    private final MailingMapper mailingMapper;
    private final MailingRepository mailingRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JavaMailSender mailSender;

    public MailResponse save(MailRequest mailRequest){
        Mailing mailing=mailingMapper.mapToEntity(mailRequest);
        List<User> users=userRepository.findAll();
        List<AuthResponse> responses=new ArrayList<>();
        for (User user:users){
            sendMessage(mailing,user.getEmail());
            responses.add(userMapper.mapToResponse(user));
        }
        mailRequest.setCreateDate(LocalDate.now());
        mailingRepository.save(mailing);
        return mailingMapper.mapToResponse(mailing);
    }
    public void sendMessage(Mailing mailing,String email){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("GiftList");
        message.setText(mailing.getText());
        message.
        mailSender.send(message);

    }
}
