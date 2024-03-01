package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.WishListRequest;
import com.peakosoft.giftlistj7.model.dto.WishListResponse;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.service.WishListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/wish_lists")
@RequiredArgsConstructor
@Slf4j
@SecurityRequirement(name="Authorization")
public class WishListController {
    private final WishListService wishListService;

    @PostMapping("/save")
    public WishListResponse save(@RequestBody WishListRequest wishListRequest, Principal principal) {
        log.info(wishListRequest.getHolidayName());
        return wishListService.save(wishListRequest, principal);
    }

    @GetMapping
    public List<WishListResponse> findAllByUserId(Principal principal) {
        return wishListService.findAll(principal);
    }

    @PutMapping("/update/{id}")
    public WishListResponse update(@PathVariable("id") Long id, @RequestBody WishListRequest wishListRequest, Principal principal) {
        return wishListService.update(id, wishListRequest, principal);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Principal principal) {
        wishListService.delete(id, principal);
        return "Gift with id: " + id + " successfully deleted";
    }
    @GetMapping("/search")
    @Operation(summary = "search gifts by their  names")
    public List<WishListResponse> searchGiftByName(@RequestParam(name = "text",required = false)String text){
        return wishListService.searchGiftByName(text);
    }
}
