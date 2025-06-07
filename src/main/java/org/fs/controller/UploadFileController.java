package org.fs.controller;

import lombok.RequiredArgsConstructor;
import org.fs.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UploadFileController {

    private final S3Service s3Service;

    @PostMapping("/api/upload")
    public ResponseEntity<String> upload(@RequestParam("image") MultipartFile file ) throws IOException {
        return ResponseEntity.ok("File uploaded!!!");
//        return ResponseEntity.ok(s3Service.upload(file));
    }

}
