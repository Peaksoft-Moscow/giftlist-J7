package com.peakosoft.giftlistj7.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MailingRequest {
    String email;
    String sender;
    String message;
    String mailingName;
    String image;

}
