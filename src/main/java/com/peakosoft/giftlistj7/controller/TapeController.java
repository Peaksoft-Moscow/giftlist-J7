package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.WishListResponse;
import com.peakosoft.giftlistj7.service.WishListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/tape")
@RequiredArgsConstructor
@Tag(name = "Tape controller", description = "Tape")
public class TapeController {
    private final WishListService wishListService;

    @GetMapping("/feed")
    @Operation(summary = "Get tape feed")
    public List<WishListResponse> getFeed(Principal principal){
        List<WishListResponse> allWishList = wishListService.findAll(principal);
        Collections.sort(allWishList, Comparator.comparing(WishListResponse::getAddDate).reversed());
        return allWishList;
    }


}
