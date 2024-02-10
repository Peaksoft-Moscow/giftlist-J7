package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.service.AmazonS3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/amazons3")
@RequiredArgsConstructor
@Tag(name = "amazons3 controller", description = "amazons3")
@SecurityRequirement(name = "Authorization")
public class AmazonS3Controller {
    @Autowired
    private AmazonS3Service amazonS3Service;

    @PostMapping("/upload")
    @Operation(summary = "upload a file from the bucket")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(amazonS3Service.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    @Operation(summary = "download a file from the bucket")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) throws IOException {
        byte[] data = amazonS3Service.downloadFile(fileName);
        ByteArrayResource byteArrayResource = new ByteArrayResource(data);
        return ResponseEntity.ok().contentLength(data.length).
                header("Content-type", "application/octet-stream").
                header("Content-disposition", "attachment; filename=\"" + fileName + "\"").body(byteArrayResource);
    }

    @DeleteMapping("/delete/{fileName}")
    @Operation(summary = "delete a file from the bucket")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(amazonS3Service.deleteFile(fileName), HttpStatus.OK);
    }
}
