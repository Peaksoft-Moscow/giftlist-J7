package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.WishListResponse;
import com.peakosoft.giftlistj7.model.entities.User;
import com.peakosoft.giftlistj7.service.UserService;
import com.peakosoft.giftlistj7.service.WishListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final UserService userService;
    @GetMapping("/feed")
    @Operation(summary = "Get tape feed")
    public List<WishListResponse> getFeed(Principal principal){
        List<WishListResponse> allWishList = wishListService.findAll(principal);
        Collections.sort(allWishList, Comparator.comparing(WishListResponse::getAddDate).reversed());
        return allWishList;
    }

    @GetMapping("/search")
    @Operation(summary = "Search users or gifts by their names")
    public List<?> searchByName(@RequestParam(name = "text", required = false) String text,
                                @RequestParam(name = "type", required = false) String type) {
        if ("user".equalsIgnoreCase(type)) {
            return userService.searchUserByName(text);
        } else if ("gift".equalsIgnoreCase(type)) {
            return wishListService.searchGiftByName(text);
        } else {
            return Collections.emptyList();
        }
    }
}
