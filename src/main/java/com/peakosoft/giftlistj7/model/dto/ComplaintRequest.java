package com.peakosoft.giftlistj7.model.dto;

import com.peakosoft.giftlistj7.model.enums.ComplaintName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintRequest {
    private Long giftId;
    private ComplaintName complaints;
    private String description;
}
