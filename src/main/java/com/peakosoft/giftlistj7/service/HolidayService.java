package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.exception.EntityNotFoundException;
import com.peakosoft.giftlistj7.model.dto.HolidayRequest;
import com.peakosoft.giftlistj7.model.dto.HolidayResponse;
import com.peakosoft.giftlistj7.model.dto.mapper.HolidayMapper;
import com.peakosoft.giftlistj7.model.entities.Holiday;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.repository.HolidayRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;


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
        List<Holiday> holidays = holidayRepository.findAll();
        for (Holiday holiday1 : holidays) {
            if (holiday1.getName().equals(holiday.getName())) {
                throw new EntityNotFoundException("the user already has such a holiday");
            }
        }
        holiday.setCreateDate(LocalDate.now());
        holiday.setUser(user);
        holidayRepository.save(holiday);
        log.info("Success is create holiday");
        return holidayMapper.mapToResponse(holiday);

    }

    public List<HolidayResponse> searchAndPagination(String text, int page, int size) {
        String name = text == null ? "" : text;
        Pageable pageable = PageRequest.of(page - 1, size);
        List<Holiday> applications = holidayRepository.searchAndPagination(name.toUpperCase(), pageable);
        return applications.stream()
                .map(holidayMapper::mapToResponse).
                collect(toList());

    }

    public HolidayResponse findById(Long id) {
        Holiday holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found by id" + id));
        holidayRepository.save(holiday);
        return holidayMapper.mapToResponse(holiday);
    }

    public HolidayResponse update(Long id, HolidayRequest request, Principal principal) {
        Holiday holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found by id" + id));
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("not found by email" + principal.getName()));
        if (user.getId() == holiday.getUser().getId()) {
            holiday.setName(request.getName());
            holiday.setImage(request.getImage());
            holiday.setCreateDate(LocalDate.now());
            holidayRepository.save(holiday);
        }
        return holidayMapper.mapToResponse(holiday);
    }

    public List<HolidayResponse> findAll(String name) {
        User user = userRepository.findByEmail(name)
                .orElseThrow(() -> new EntityNotFoundException("not found bound username" + name));
        List<Holiday> myHoliday = holidayRepository.findAllHolidaysByUserId(user.getId());
        return myHoliday.stream().map(holidayMapper::mapToResponse).toList();
    }

    public void removeById(Long id, Principal principal) {
        Holiday holiday = holidayRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("not found by id" + id));
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("not found by email" + principal.getName()));
        if (user.getId() == holiday.getUser().getId()) {
            holidayRepository.delete(holiday);
        }

    }
}
