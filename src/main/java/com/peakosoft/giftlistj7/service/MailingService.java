package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.dto.MailRequest;
import com.peakosoft.giftlistj7.model.dto.MailResponse;
import com.peakosoft.giftlistj7.model.dto.mapper.MailingMapper;
import com.peakosoft.giftlistj7.model.entities.Mailing;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.repository.MailingRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MailingService {

    private final MailingMapper mailingMapper;
    private final MailingRepository mailingRepository;
    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;
    private final JavaMailSender javaMailSender;


    public MailResponse createMailing(MailRequest mailRequest) {
        Mailing mailing = mailingMapper.mapToEntity(mailRequest);
        List<User> users = userRepository.findAllBySubscribe();
        for (User user : users) {
            mailSenderService.sendMessageToUsers(user.getEmail(), mailRequest.getTheme(), mailRequest.getText());
        }
        mailingRepository.save(mailing);
        return mailingMapper.mapToResponse(mailing);
    }
}
