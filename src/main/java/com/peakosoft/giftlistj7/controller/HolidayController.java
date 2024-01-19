package com.peakosoft.giftlistj7.controller;


import com.peakosoft.giftlistj7.model.dto.HolidayRequest;
import com.peakosoft.giftlistj7.model.dto.HolidayResponse;
import com.peakosoft.giftlistj7.service.HolidayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("api/holiday")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Holiday Controller", description = "Operations related to holiday management")
public class HolidayController {
    private final HolidayService holidayService;

    @PostMapping("/save")
    @Operation(summary = "Create a new holiday",
            description = "Creates a new holiday based on the provided request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created holiday"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    public ResponseEntity<HolidayResponse> create(@RequestBody HolidayRequest holidayRequest, Principal principal) {
        HolidayResponse response = holidayService.create(holidayRequest, principal.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get holiday by ID",
            description = "Returns holiday details based on the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved holiday"),
            @ApiResponse(responseCode = "404", description = "Holiday not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public HolidayResponse findById(@PathVariable("id") Long id) {
        return holidayService.findById(id);
    }

    @GetMapping()
    @Operation(summary = "Get all holidays",
            description = "Returns a list of all holidays for the authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved holidays"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<HolidayResponse> findAll(Principal principal) {
        return holidayService.findAll(principal.getName());
    }

    @PutMapping("update/{id}")
    @Operation(summary = "Update a holiday",
            description = "Updates a holiday based on the provided ID and request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated holiday"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Holiday not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public HolidayResponse update(@PathVariable("id") Long holidayId, @RequestBody HolidayRequest request, Principal principal) {
        return holidayService.update(holidayId, request, principal);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a holiday",
            description = "Deletes a holiday based on the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted holiday"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Holiday not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public String delete(@PathVariable("id") Long id, Principal principal) {
        holidayService.removeById(id, principal);
        return "Holiday with id" + id + "successfully deleted";
    }
}
