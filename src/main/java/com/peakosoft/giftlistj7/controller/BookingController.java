package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.BookingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth/save-booking")
public class BookingController {
    public String book(@RequestParam String bookName, @RequestParam Long bookingId){
      //  public BookingResponse.bui
        return null;

    }
}
