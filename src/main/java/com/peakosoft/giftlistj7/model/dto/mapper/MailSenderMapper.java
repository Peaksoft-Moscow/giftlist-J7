package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.MailRequest;
import com.peakosoft.giftlistj7.model.dto.MailResponse;
import com.peakosoft.giftlistj7.model.entities.MailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSenderMapper {
    public MailSender mapToEntity(MailRequest request){
        MailSender mailSender=new MailSender();
        mailSender.setText(request.getText());
        mailSender.setSubject(request.getSubject());
        return mailSender;
    }
    public MailResponse mapToResponse(MailSender mailSender){
        return MailResponse.builder()
                .id(mailSender.getId())
                .text(mailSender.getText())
                .subject(mailSender.getSubject())
                .localDate(mailSender.getLocalDate())
                .response("Mail successfully send").build();
    }
}
