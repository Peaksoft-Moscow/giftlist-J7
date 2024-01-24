package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.enums.Complaints;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ComplaintRequest {
    private Complaints complaints;
    private String description;
}
