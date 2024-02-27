package com.peakosoft.giftlistj7.service;


import com.peakosoft.giftlistj7.exception.NotFoundException;
import com.peakosoft.giftlistj7.model.dto.BookingResponse;
import com.peakosoft.giftlistj7.model.dto.HolidayResponse;
import com.peakosoft.giftlistj7.model.entities.Booking;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.Holiday;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import com.peakosoft.giftlistj7.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final WishListRepository giftListRepository;

    @Transactional
    public User book(Long giftId, Principal principal) {
        Gift gift = giftListRepository.findById(giftId).orElseThrow(() -> new RuntimeException("not found"));
        if (gift.getBookingStatus().equals(BookingStatus.BOOKED)) {
            throw new RuntimeException(" Gift is already booked ");
        }
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(
                () -> new RuntimeException("not found")
        );
        Booking book = bookingRepository.findBookingByGiftId(giftId);
        if (book != null) {
            throw new RuntimeException("Booking already exists for this gift");
        }

        List<Booking> bookings = bookingRepository.findBookingByUserId(user.getId());
        if (!bookings.isEmpty()) {
            for (Booking booking : bookings) {
                if (Objects.equals(booking.getGift().getId(), giftId)) {
                    throw new RuntimeException("User has already booked this gift");
                }
            }
        }
        Booking booking = new Booking();
        booking.setImage(gift.getImage());
        booking.setGiftStatus(gift.getGiftStatus());
        booking.setGiftName(gift.getName());
        booking.setCreateDate(LocalDate.now());
        booking.setBookingStatus(BookingStatus.BOOKED);

        gift.setBookingStatus(BookingStatus.BOOKED);
        booking.setUser(user);
        booking.setGift(gift);
        gift.setBooking(booking);
        bookingRepository.save(booking);
        return user;
    }


    public String remove(Long bookingId, Principal principal) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));

        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (!booking.getUser().equals(user)) {
            throw new RuntimeException("User does not have permission to remove this booking");
        }
        Gift gift = booking.getGift();
        if (gift != null ){
            gift.setBookingStatus(BookingStatus.UNBOOKED);
            gift.setBooking(null);
            giftListRepository.save(gift);

        }
        bookingRepository.deleteById(booking.getId());
        return "Successful deleted";
    }

    public List<Booking> getAllBooking() {
        User user = userRepository.findByEmail(getPrincipalEmail().getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return bookingRepository.findBookingByUserId(user.getId());
    }


    private User getPrincipalEmail() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    public List<Booking> searchBookingGiftByName(String text){
        List<Booking> bookings = bookingRepository.searchBookingGiftByName(text);
        if (bookings.isEmpty()){
            throw new NotFoundException("No booking found with the name: "+text);
        }
        return bookings;
    }
}
