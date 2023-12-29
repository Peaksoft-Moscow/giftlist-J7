package com.peakosoft.giftlistj7.service;

import com.peakosoft.giftlistj7.model.dto.mapper.WishListMapper;
import com.peakosoft.giftlistj7.model.dto.WishListRequest;
import com.peakosoft.giftlistj7.model.dto.WishListResponse;
import com.peakosoft.giftlistj7.model.entities.Gift;
import com.peakosoft.giftlistj7.model.entities.Holiday;
import com.peakosoft.giftlistj7.repository.HolidayRepository;
import com.peakosoft.giftlistj7.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final WishListRepository wishListRepository;
    private final WishListMapper wishListMapper;
    private final HolidayRepository holidayRepository;
    public WishListResponse save(WishListRequest wishListRequest) {
        Holiday holiday = holidayRepository.findByName(wishListRequest.getHolidayName()).get();
        System.out.println(holiday.getName());
        Gift gift = wishListMapper.mapToEntity(wishListRequest);
        gift.setHoliday(holiday);
        wishListRepository.save(gift);
        return wishListMapper.mapToResponse(gift);
    }
    public List<WishListResponse> findAll(Long userId) {
        List<Gift> myGifts = wishListRepository
                .findAllByUserId(userId)
                .orElseThrow(()-> new RuntimeException("Not found user by id: " + userId));
        return myGifts.stream().map(wishListMapper::mapToResponse).toList();
    }
    public WishListResponse update(Long giftId, WishListRequest wishListRequest) {
        Gift oldGift = wishListRepository.findById(giftId).orElseThrow(()-> new RuntimeException("Not found gift by id: " + giftId));
        oldGift.setImage(wishListRequest.getImage());
        oldGift.setName(wishListRequest.getName());
        oldGift.setLink(wishListRequest.getLink());
        oldGift.setHoliday(holidayRepository.findByName(wishListRequest.getHolidayName()).get());
        oldGift.setDateOfHoliday(wishListRequest.getDateOfHoliday());
        oldGift.setDescription(wishListRequest.getDescription());
        wishListRepository.save(oldGift);
        return wishListMapper.mapToResponse(oldGift);
    }

    public void delete(Long giftId){
        Gift gift = wishListRepository.findById(giftId).orElseThrow(()-> new RuntimeException("Not found gift by id: " + giftId));
        wishListRepository.delete(gift);
    }
}
