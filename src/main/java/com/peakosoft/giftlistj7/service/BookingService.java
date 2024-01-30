package com.peakosoft.giftlistj7.service;


import com.peakosoft.giftlistj7.model.entities.Booking;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import com.peakosoft.giftlistj7.repository.BookingRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import com.peakosoft.giftlistj7.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final WishListRepository giftListRepository;

    public User book(Long giftId, Principal principal) {

        // Метод для бронирования подарка
        Gift gift = giftListRepository.findById(giftId).orElseThrow(() -> new RuntimeException("not found"));
        if (gift.getBookingStatus().equals(BookingStatus.BOOKED)) {
            throw new RuntimeException(" Gift is already booked ");
        }
        //находим user по емаилу
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("not found"));
        //находим booking по id и положили booking
        Booking book = bookingRepository.findBookingByGiftId(giftId);
        if (book != null) {
            throw new RuntimeException("Booking already exists for this gift");
        }

        // лист бронирование - туда положилиb userId
        List<Booking> bookings = bookingRepository.findBookingByUserId(user.getId());
        if (!bookings.isEmpty()) {
            for (Booking booking : bookings) {
                if (booking.getGift().getId() == giftId) {
                    throw new RuntimeException("User has already booked this gift");
                }
            }
            Booking booking = new Booking();
            booking.setCreateDate(LocalDate.now());
            booking.setUser(user);
            booking.setGift(gift);
            bookingRepository.save(booking);
            return user;

        }
        return null;
    }


    public Booking remove(Long bookingId, Principal principal) {
        // Находим бронь по её идентификатору
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        // Проверяем, имеет ли пользователь право удалить данную бронь
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!booking.getUser().equals(user)) {
            throw new RuntimeException("User does not have permission to remove this booking");

        }
        bookingRepository.delete(booking);
        return booking;
    }
    //выводим всех бронирование
    public List<Booking> getAllBooking(){
        return bookingRepository.findAll();
    }


}
