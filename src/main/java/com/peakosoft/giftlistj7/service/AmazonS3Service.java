package com.peakosoft.giftlistj7.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AmazonS3Service {
    private final BasicAWSCredentials credentials = new BasicAWSCredentials("giftList", "java7");
    private final AmazonS3 amazonS3 = AmazonS3Client.builder().withRegion(Regions.US_EAST_1)
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .build();

    public void uploadObject(String bucketName, String key, String filePath) {
        amazonS3.putObject(bucketName, key, new java.io.File(filePath));
    }

    public S3Object downloadObject(String bucketName, String key) {
        return amazonS3.getObject(new GetObjectRequest(bucketName, key));
    }


    public void updateObject(String bucketName, String key, String filePath) {
        uploadObject(bucketName, key, filePath);
    }

    public void deleteObject(String bucketName, String key) {
        amazonS3.deleteObject(bucketName, key);
    }
}
