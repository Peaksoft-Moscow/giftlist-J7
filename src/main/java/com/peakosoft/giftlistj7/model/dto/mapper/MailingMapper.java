package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.MailingResponse;
import com.peakosoft.giftlistj7.model.entities.Mailing;
import org.springframework.stereotype.Component;

@Component
public class MailingMapper implements Mapper<MailingRequest, Mailing, MailingResponse>{
    @Override
    public Mailing mapToEntity(MailingRequest mailingRequest) {
        return null;
    }

    @Override
    public MailingResponse mapToResponse(Mailing mailing) {
        return null;
    }
}
