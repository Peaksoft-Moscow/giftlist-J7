package com.peakosoft.giftlistj7.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MailRequest {
    private String photo;
    private String theme;
    private String text;
}