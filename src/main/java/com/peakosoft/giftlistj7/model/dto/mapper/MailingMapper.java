package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.MailRequest;
import com.peakosoft.giftlistj7.model.dto.MailResponse;
import com.peakosoft.giftlistj7.model.entities.Mailing;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MailingMapper {
    public Mailing mapToEntity(MailRequest request){
        Mailing mailing=new Mailing();
        mailing.setText(request.getText());
        mailing.setTheme(request.getTheme());
        return mailing;
    }
    public MailResponse mapToResponse(Mailing mailing){
        return MailResponse.builder()
                .id(mailing.getId())
                .text(mailing.getText())
                .theme(mailing.getTheme())
                .createDate(LocalDate.now())
                .response("Mail successfully send").build();
    }
}
