package com.peakosoft.giftlistj7.controller;


import com.peakosoft.giftlistj7.model.dto.HolidayRequest;
import com.peakosoft.giftlistj7.model.dto.HolidayResponse;
import com.peakosoft.giftlistj7.model.dto.WishListResponse;
import com.peakosoft.giftlistj7.service.HolidayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name="Authorization")
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
    @GetMapping("/search")
    @Operation(summary = "search holidays by their  names")
    public List<HolidayResponse> searchHolidayByName(@RequestParam(name = "text",required = false)String text){
        return holidayService.searchHolidayByName(text);
    }
}
