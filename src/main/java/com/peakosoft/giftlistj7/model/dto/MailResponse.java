package com.peakosoft.giftlistj7.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class MailResponse {
    private Long id;
    private String theme;
    private String text;
    private LocalDate createDate;
    private String response;
}
