package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.enums.Complaints;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ComplaintResponse {
    private Long id;
    private Complaints complaints;
    private String description;
    private LocalDate createDate;

}
