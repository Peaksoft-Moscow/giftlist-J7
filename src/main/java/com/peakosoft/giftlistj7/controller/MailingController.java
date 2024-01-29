package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.model.dto.MailRequest;
import com.peakosoft.giftlistj7.model.dto.MailResponse;
import com.peakosoft.giftlistj7.service.MailingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mailings")
@Tag(name = "Mailing controller", description = "mailing")
@Slf4j
public class MailingController {

    private final MailingService mailingService;

    @PostMapping("/send")
    @Operation(summary = "send mailing to Users")
    public ResponseEntity<MailResponse> mail(@RequestBody MailRequest mailRequest) {
        MailResponse response = mailingService.createMailing(mailRequest);

        log.info("Mailing successfully created");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
