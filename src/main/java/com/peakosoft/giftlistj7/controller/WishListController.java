package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.WishListRequest;
import com.peakosoft.giftlistj7.model.dto.WishListResponse;
import com.peakosoft.giftlistj7.service.WishListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@Tag(name = "Wish List Controller", description = "Operations related to managing a wishlist")
@RequiredArgsConstructor
@Slf4j
public class WishListController {
    private final WishListService wishListService;

    @PostMapping("/save")
    @Operation(summary = "Save a wishlist item", description = "Saves a wishlist item based on the provided request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully saved wishlist item"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public WishListResponse save(@RequestBody WishListRequest wishListRequest, Principal principal) {
        log.info(wishListRequest.getHolidayName());
        return wishListService.save(wishListRequest, principal);
    }

    @GetMapping
    @Operation(summary = "Find all wishlist items by user ID",
            description = "Returns a list of wishlist items based on the user ID in the Principal")
    public List<WishListResponse> findAllByUserId(Principal principal) {
        return wishListService.findAll(principal);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a wishlist item",
            description = "Updates a wishlist item based on the provided ID and request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted wishlist item"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public WishListResponse update(@PathVariable("id") Long id, @RequestBody WishListRequest wishListRequest, Principal principal) {
        return wishListService.update(id, wishListRequest, principal);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a wishlist item",
            description = "Deletes a wishlist item based on the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted wishlist item"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public String delete(@PathVariable("id") Long id, Principal principal) {
        wishListService.delete(id, principal);
        return "Gift with id: " + id + " successfully deleted";
    }
}
