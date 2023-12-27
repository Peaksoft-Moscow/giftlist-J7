package com.peakosoft.giftlistj7.model.dto.mapper;


import com.peakosoft.giftlistj7.model.dto.HolidayRequest;
import com.peakosoft.giftlistj7.model.dto.HolidayResponse;
import com.peakosoft.giftlistj7.model.entities.Holiday;
import lombok.RequiredArgsConstructor;
import com.peakosoft.giftlistj7.model.entities.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class HolidayMapper implements Mapper<HolidayRequest, Holiday, HolidayResponse> {

    @Override
    public Holiday mapToEntity(HolidayRequest holidayRequest) {
        Holiday holiday = new Holiday();
        holiday.setName(holidayRequest.getName());
        holiday.setImage(holidayRequest.getImage());
        holiday.setDate(LocalDate.now());
        return holiday;
    }

    @Override
    public HolidayResponse mapToResponse(Holiday holiday) {
        User user=new User();
        return HolidayResponse.builder()
                .id(holiday.getId())
                .name(holiday.getName())
                .image(holiday.getImage())
                .date(holiday.getDate())
                .createDate(LocalDate.now())
                .ownerName(user.getName()+" "+user.getLastName()).
                build();
    }

}