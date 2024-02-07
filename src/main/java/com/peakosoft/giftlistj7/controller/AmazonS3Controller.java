package com.peakosoft.giftlistj7.controller;

import com.peakosoft.giftlistj7.service.AmazonS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/amazons3")
public class AmazonS3Controller {
    private final AmazonS3Service amazonS3Service;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(amazonS3Service.uploadFile(file), HttpStatus.OK);
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = amazonS3Service.downloadFile(fileName);
        ByteArrayResource byteArrayResource = new ByteArrayResource(data);
        return ResponseEntity.ok().contentLength(data.length).
                header("Content-type", "application/octet-stream").
                header("Content-disposition", "attachment; filename=\"" + fileName + "\"").body(byteArrayResource);
    }
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName){
        return new ResponseEntity<>(amazonS3Service.deleteFile(fileName),HttpStatus.OK);
    }
    }
