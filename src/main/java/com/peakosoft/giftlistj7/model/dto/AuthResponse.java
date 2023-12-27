package com.peakosoft.giftlistj7.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private boolean subscribe;
    private String response;
}