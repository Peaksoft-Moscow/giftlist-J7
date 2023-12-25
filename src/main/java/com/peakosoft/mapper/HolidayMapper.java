package com.peakosoft.mapper;

import com.peakosoft.model.dto.HolidayRequest;
import com.peakosoft.model.dto.HolidayResponse;
import com.peakosoft.model.entities.Holiday;
import com.peakosoft.model.entities.User;
import lombok.RequiredArgsConstructor;
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
        holiday.setCreateDate(LocalDate.now());
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
