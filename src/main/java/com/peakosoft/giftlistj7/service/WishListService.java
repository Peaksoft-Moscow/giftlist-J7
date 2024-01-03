package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.dto.mapper.WishListMapper;
import com.peakosoft.giftlistj7.model.dto.WishListRequest;
import com.peakosoft.giftlistj7.model.dto.WishListResponse;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.Holiday;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.model.enums.BookingStatus;
import com.peakosoft.giftlistj7.model.enums.GiftStatus;
import com.peakosoft.giftlistj7.repository.HolidayRepository;
import com.peakosoft.giftlistj7.repository.UserRepository;
import com.peakosoft.giftlistj7.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final WishListRepository wishListRepository;
    private final WishListMapper wishListMapper;
    private final HolidayRepository holidayRepository;
    private final UserRepository userRepository;

    public WishListResponse save(WishListRequest wishListRequest, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(()-> new RuntimeException("Not found user with email: " + principal.getName()));
        Holiday holiday = holidayRepository.findByName(wishListRequest.getHolidayName()).orElseThrow(() -> new RuntimeException("Holiday not found by name: " + wishListRequest.getHolidayName()));
        Gift gift = wishListMapper.mapToEntity(wishListRequest);
        gift.setHoliday(holiday);
        gift.setGiftStatus(GiftStatus.WISHLIST);
        gift.setUser(user);
        wishListRepository.save(gift);
        return wishListMapper.mapToResponse(gift);
    }

    public List<WishListResponse> findAll(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("Not found user with email: " + principal.getName()));
        List<Gift> myGifts = wishListRepository
                .findAllByUserId(user.getId());
        System.out.println(myGifts.isEmpty());
        System.out.println("my gift: ");
        for (Gift myGift : myGifts) {
            System.out.println(myGift.getName());
        }
        return myGifts.stream().map(wishListMapper::mapToResponse).toList();
    }

    public WishListResponse update(Long giftId, WishListRequest wishListRequest) {
        Gift oldGift = wishListRepository.findById(giftId).orElseThrow(() -> new RuntimeException("Not found gift by id: " + giftId));
        oldGift.setImage(wishListRequest.getImage());
        oldGift.setName(wishListRequest.getName());
        oldGift.setLink(wishListRequest.getLink());
        oldGift.setHoliday(holidayRepository.findByName(wishListRequest.getHolidayName())
                .orElseThrow(() -> new RuntimeException("Not found holiday by name: " + wishListRequest.getHolidayName())));
        oldGift.setDateOfHoliday(wishListRequest.getDateOfHoliday());
        oldGift.setDescription(wishListRequest.getDescription());
        oldGift.setBookingStatus(BookingStatus.EXPECTATION);
        wishListRepository.save(oldGift);
        return wishListMapper.mapToResponse(oldGift);
    }

    public void delete(Long giftId) {
        Gift gift = wishListRepository.findById(giftId).orElseThrow(() -> new RuntimeException("Not found gift by id: " + giftId));
        wishListRepository.delete(gift);
    }
}
