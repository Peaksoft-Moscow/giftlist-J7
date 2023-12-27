package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.WishListRequest;
import com.peakosoft.giftlistj7.model.dto.WishListResponse;
import com.peakosoft.giftlistj7.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/WishLists")
@RequiredArgsConstructor
public class WishListController {
    private final WishListService wishListService;
    @PostMapping("/save")
    public WishListResponse save(WishListRequest wishListRequest) {
        return wishListService.save(wishListRequest);
    }
    @GetMapping()
    public List<WishListResponse> findAllByUserId(Long id) {
        return wishListService.findAll(id);
    }
    @GetMapping("/update/{id}")
    public WishListResponse update(@PathVariable("id") Long id, WishListRequest wishListRequest) {
        return wishListService.update(id, wishListRequest);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        wishListService.delete(id);
    }
}