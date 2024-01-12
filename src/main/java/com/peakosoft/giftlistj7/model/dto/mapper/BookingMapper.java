package com.peakosoft.giftlistj7.model.dto.mapper;

import com.peakosoft.giftlistj7.model.dto.BookingRequest;
import com.peakosoft.giftlistj7.model.dto.BookingResponse;
import com.peakosoft.giftlistj7.model.entities.Booking;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BookingMapper {
    public Booking mapToEntity(BookingRequest bookingRequest){
        Booking booking = new Booking();
        booking.setImage(bookingRequest.getImage());
        booking.setName(booking.getName());
        booking.setCreateDate(LocalDate.now());
        return  booking;
    }
    public BookingResponse mapToResponse(Booking booking){
        return BookingResponse.builder()
                .id(booking.getId())
                .image(booking.getImage())
                .name(booking.getName())
                .bookingStatus(booking.getBookingStatus())
                .createDate(booking.getCreateDate()).build();
    }
}
