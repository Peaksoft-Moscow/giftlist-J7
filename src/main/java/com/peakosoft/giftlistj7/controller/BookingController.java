package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.entities.Booking;
import com.peakosoft.giftlistj7.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {

    public final BookingService bookingService;

    @PostMapping("/book/{id}")
    public ResponseEntity<String> book(@PathVariable("id") Long id, Principal principal) {
        System.out.println(id);
        bookingService.book(id,principal);
        return new ResponseEntity<>("Successfully", HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/remove")
    public Booking remove(@RequestParam Long bookingId, Principal principal){
        return bookingService.remove(bookingId, principal);
    }
    @GetMapping("/getAll")
    public List<Booking> getAllBooking(){
        return bookingService.getAllBooking();
    }

}
