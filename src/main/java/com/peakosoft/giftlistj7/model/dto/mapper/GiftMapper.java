package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.GiftResponse;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GiftMapper {
    private final UserMapper userMapper;
    private final HolidayMapper holidayMapper;

    public GiftResponse mapToResponse(Gift gift) {
        return GiftResponse.builder()
                .id(gift.getId())
                .name(gift.getName())
                .giftStatus(gift.getGiftStatus())
                .user(userMapper.mapToResponse(gift.getUser()))
                .holiday(holidayMapper.mapToResponse(gift.getHoliday()))
                .category(gift.getCategory())
                .subCategory(gift.getSubCategory())
                .build();
    }
}
