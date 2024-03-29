package com.peakosoft.giftlistj7.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peakosoft.giftlistj7.model.entities.Category;
import com.peakosoft.giftlistj7.model.entities.SubCategory;
import com.peakosoft.giftlistj7.model.enums.Condition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharityRequest {
    private String image;
    private String giftName;
    private Condition condition;
    private String description;
    private String subCategoryName;

}
