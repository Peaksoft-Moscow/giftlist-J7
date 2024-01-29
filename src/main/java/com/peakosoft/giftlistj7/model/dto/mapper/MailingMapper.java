package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.MailRequest;
import com.peakosoft.giftlistj7.model.dto.MailResponse;
import com.peakosoft.giftlistj7.model.entities.Mailing;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MailingMapper {
    public Mailing mapToEntity(MailRequest request) {
        Mailing mailing = new Mailing();
        mailing.setText(request.getText());
        mailing.setTheme(request.getTheme());
        mailing.setPhoto(request.getPhoto());
        mailing.setCreateDate(LocalDate.now());
        return mailing;
    }

    public MailResponse mapToResponse(Mailing mailing) {
        return MailResponse.builder()
                .id(mailing.getId())
                .photo(mailing.getPhoto())
                .theme(mailing.getTheme())
                .text(mailing.getText())
                .createDate(LocalDate.now()).build();
    }
}
