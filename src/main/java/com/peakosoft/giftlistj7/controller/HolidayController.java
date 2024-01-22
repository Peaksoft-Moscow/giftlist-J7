package com.peakosoft.giftlistj7.controller;


import com.peakosoft.giftlistj7.model.dto.HolidayRequest;
import com.peakosoft.giftlistj7.model.dto.HolidayResponse;
import com.peakosoft.giftlistj7.service.HolidayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("api/holiday")
@RequiredArgsConstructor
@Slf4j
public class HolidayController {
    private final HolidayService holidayService;

    @PostMapping("/save")
    public ResponseEntity<HolidayResponse> create(@RequestBody HolidayRequest holidayRequest, Principal principal) {
        HolidayResponse response = holidayService.create(holidayRequest, principal.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public HolidayResponse findById(@PathVariable("id") Long id) {
        return holidayService.findById(id);
    }

    @GetMapping()
    public List<HolidayResponse> findAll(Principal principal) {
        return holidayService.findAll(principal.getName());
    }

    @PutMapping("update/{id}")
    public HolidayResponse update(@PathVariable("id") Long holidayId, @RequestBody HolidayRequest request, Principal principal) {
        return holidayService.update(holidayId, request, principal);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Principal principal) {
        holidayService.removeById(id, principal);
        return "Holiday with id" + id + "successfully deleted";
    }
}
