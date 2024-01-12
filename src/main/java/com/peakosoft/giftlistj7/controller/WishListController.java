package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.WishListRequest;
import com.peakosoft.giftlistj7.model.dto.WishListResponse;
import com.peakosoft.giftlistj7.service.WishListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
@RequestMapping("api/wish_lists")
@Tag(name = "Wish_list controller",description ="Used by everyone")
@RequiredArgsConstructor
@Slf4j
public class WishListController {
    private final WishListService wishListService;
    @PostMapping("/save")
    @Operation(summary = "Post wish_list controller",description = "Getting save by user and admin")
    public WishListResponse save(@RequestBody WishListRequest wishListRequest, Principal principal) {
        log.info(wishListRequest.getHolidayName());
        return wishListService.save(wishListRequest, principal);
    }
    @GetMapping
    @Operation(summary = "Get with_list controller",description = "Getting All from wish_list")
    public List<WishListResponse> findAllByUserId(Principal principal) {
        return wishListService.findAll(principal);
    }
    @PutMapping("/update/{id}")
    @Operation(summary = "Put wish_list controller",description = "Putting update by user and admin ")
    public WishListResponse update(@PathVariable("id") Long id,@RequestBody WishListRequest wishListRequest, Principal principal) {
        return wishListService.update(id, wishListRequest, principal);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete wish_list controller",description = "")
    public String delete(@PathVariable("id") Long id, Principal principal){
        wishListService.delete(id, principal);
        return "Gift with id: "+id+" successfully deleted";
    }
}
