package org.fs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.region}")
    private String region;

    private final S3Client s3Client;

    public String upload(MultipartFile file) throws IOException {
        String key = UUID.randomUUID() + "-" + file.getOriginalFilename();

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
//                        .acl("public-read")
                        .build(),
                RequestBody.fromInputStream(file.getInputStream(), file.getSize())
        );

        return "https://" + bucket + ".s3.amazonaws.com/" + key;
    }

    public void deleteFile(String url) {
        String key = extractKeyFromUrl(url);

        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

        s3Client.deleteObject(deleteRequest);
    }

    public String extractKeyFromUrl(String url) {
        String prefix = "https://" + bucket + ".s3" + ".amazonaws.com/";
        if (url.startsWith(prefix)) {
            return url.substring(prefix.length());
        }
        throw new IllegalArgumentException("URL does not match bucket");
    }

}
