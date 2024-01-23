package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.dto.mapper.MailSenderMapper;
import com.peakosoft.giftlistj7.model.dto.mapper.UserMapper;
import com.peakosoft.giftlistj7.repository.MailSenderRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final MailSenderMapper mailSenderMapper;
    private final MailSenderRepository mailSenderRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
}
