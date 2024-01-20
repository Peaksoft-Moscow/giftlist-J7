package com.peakosoft.giftlistj7.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum BookingStatus implements GrantedAuthority {
    EXPECTATION,
    BOOKED,
    UNBOOKED;

    @Override
    public String getAuthority() {
        return name();
    }
}
