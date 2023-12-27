package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.dto.HolidayRequest;
import com.peakosoft.giftlistj7.model.dto.HolidayResponse;
import com.peakosoft.giftlistj7.model.dto.mapper.HolidayMapper;
import com.peakosoft.giftlistj7.model.entities.Holiday;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.repository.HolidayRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class HolidayService {
    private final HolidayRepository holidayRepository;
    private final HolidayMapper holidayMapper;
    private final UserRepository userRepository;

    public HolidayResponse create(HolidayRequest request, String username) {
        Holiday holiday = holidayMapper.mapToEntity(request);
        User user = userRepository.findByEmail(username).
                orElseThrow(() -> new EntityNotFoundException("not found bound username" + username));
//        holiday.setCreateDate(LocalDate.now());
        holiday.setUser(user);
        holidayRepository.save(holiday);
        log.info("Success is create holiday");
        return holidayMapper.mapToResponse(holiday);

    }


    public HolidayResponse findById(Long id) {
        Holiday holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found by id" + id));
        holidayRepository.save(holiday);
        return holidayMapper.mapToResponse(holiday);
    }

    public HolidayResponse update(Long id, HolidayRequest request) {
        Holiday holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found by id" + id));
        holiday.setName(request.getName());
        holiday.setImage(request.getImage());
//        holiday.setCreateDate(LocalDate.now());
        holidayRepository.save(holiday);
        return holidayMapper.mapToResponse(holiday);
    }

    public List<HolidayResponse> findAll(String name) {
        User user = userRepository.findByEmail(name)
                .orElseThrow(() -> new EntityNotFoundException("not found bound username" + name));
        List<Holiday> myHoliday = holidayRepository.findAllHolidaysByUserId(user.getId());
        return myHoliday.stream().map(holidayMapper::mapToResponse).toList();
    }

    public void removeById(Long id) {
        Holiday holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found by id" + id));
        holidayRepository.delete(holiday);

    }
}
