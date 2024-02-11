package com.peakosoft.giftlistj7.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MailingResponse {
    Long id;
    String email;
    String sender;
    String massage;
    String image;

}
