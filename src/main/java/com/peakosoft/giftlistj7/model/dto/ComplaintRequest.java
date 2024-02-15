package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.enums.ComplaintStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintRequest {
    private ComplaintStatus complaints;
    private String description;
}
