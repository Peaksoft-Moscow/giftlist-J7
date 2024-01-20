package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.entities.Booking;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/booking")
public class BookingController {
    public final BookingService bookingService;

    public User
    @PostMapping("/book/{id}")
    public User book(@PathVariable("id") Long giftId, Principal principal) {
        return bookingService.book(giftId,principal);
    }
    @GetMapping("/remove")
    public Booking remove(@RequestParam Long bookingId, Principal principal){
        return bookingService.remove(bookingId, principal);
    }
}
