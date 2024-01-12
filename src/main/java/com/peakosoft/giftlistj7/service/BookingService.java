package com.peakosoft.giftlistj7.service;


import com.peakosoft.giftlistj7.repository.BookingRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;


@Service
@RequiredArgsConstructor
public class BookingService {
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public boolean book(Principal principal, Long bookingId) {
    }
}