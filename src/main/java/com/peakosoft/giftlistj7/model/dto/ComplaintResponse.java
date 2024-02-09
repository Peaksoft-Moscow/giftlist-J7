package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.enums.ComplaintStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ComplaintResponse {
    private Long id;
    private GiftResponse gift;
    private AuthResponse user;
    private ComplaintStatus complaints;
    private String description;
    private LocalDate createDate;

}
