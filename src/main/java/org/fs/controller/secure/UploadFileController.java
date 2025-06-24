package org.fs.controller.secure;

import lombok.RequiredArgsConstructor;
import org.fs.service.S3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/secure")
@RequiredArgsConstructor
public class UploadFileController {

    private final S3Service s3Service;

    @PostMapping("/api/upload")
    public ResponseEntity<String> upload(@RequestParam("image") MultipartFile file) throws IOException {
        return ResponseEntity.ok(s3Service.upload(file));
    }

}
