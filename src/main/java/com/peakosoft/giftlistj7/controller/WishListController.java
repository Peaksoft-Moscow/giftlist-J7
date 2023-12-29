package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.WishListRequest;
import com.peakosoft.giftlistj7.model.dto.WishListResponse;
import com.peakosoft.giftlistj7.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/wish_lists")
@RequiredArgsConstructor
@Slf4j
public class WishListController {
    private final WishListService wishListService;
    @PostMapping("/save")
    public WishListResponse save(@RequestBody WishListRequest wishListRequest) {
        log.info(wishListRequest.getHolidayName());
        return wishListService.save(wishListRequest);
    }
    @GetMapping
    public List<WishListResponse> findAllByUserId(Long id) {
        return wishListService.findAll(id);
    }
    @GetMapping("/update/{id}")
    public WishListResponse update(@PathVariable("id") Long id, WishListRequest wishListRequest) {
        return wishListService.update(id, wishListRequest);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        wishListService.delete(id);
        return "Gift with id: "+id+" successfully deleted";
    }
}
