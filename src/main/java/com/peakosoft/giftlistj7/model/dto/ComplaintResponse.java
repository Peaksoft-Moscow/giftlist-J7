package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.ComplaintName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ComplaintResponse {
    private Long id;
    private Gift gift;
    private User user;
    private ComplaintName complaints;
    private String description;
    private LocalDate createDate;

}
