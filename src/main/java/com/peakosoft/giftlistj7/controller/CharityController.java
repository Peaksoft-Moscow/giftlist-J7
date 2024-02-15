package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.CharityRequest;
import com.peakosoft.giftlistj7.model.dto.CharityResponse;
import com.peakosoft.giftlistj7.service.CharityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/charity")
@Tag(name = "Charity controller", description = "charity")
@SecurityRequirement(name="Authorization")
public class CharityController {
    public final CharityService charityService;

    @PostMapping("/save")
    @Operation(summary = "save requests to charity")
    public CharityResponse save(@RequestBody CharityRequest charityRequest, Principal principal) {
        System.out.println(charityRequest.getGiftName());
        return charityService.save(charityRequest, principal);
    }

    @GetMapping
    @Operation(summary = "find all  by User")
    public List<CharityResponse> findAllByUser(Principal principal) {
        return charityService.findAll(principal);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "update requests by id")
    public CharityResponse update(@PathVariable("id") Long id, @RequestBody CharityRequest charityRequest, Principal principal) {
        return charityService.update(id, charityRequest, principal);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete gift by id")
    public String delete(@PathVariable("id") Long id, Principal principal) {
        charityService.delete(id, principal);
        return "Gift with id: " + id + " successfully deleted";
    }
}
