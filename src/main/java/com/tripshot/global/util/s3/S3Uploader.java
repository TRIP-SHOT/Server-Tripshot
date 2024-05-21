package com.tripshot.global.util.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class S3Uploader {
    private final AmazonS3 amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /**
     * s3에 image를 업로드합니다.
     * @param multipartFile
     * @param dirName prefix-s3에 파일이 저장되어있는 폴더
     * @return s3에 업로드된 이미지의 url
     * @throws IOException
     */
    public String[] upload(MultipartFile multipartFile, String dirName) throws IOException {
        String fileName = generateFileName(multipartFile);
        //메타데이터 생성
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());
        //업로드
        amazonS3Client.putObject(new PutObjectRequest(bucket, dirName + "/" + fileName, multipartFile.getInputStream(), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        //키와 url을 동시에 반환해야함
        String key = dirName + "/"+fileName;
        String url = amazonS3Client.getUrl(bucket, key).toString();
        String[] keyAndUrl = {key,url};
        return keyAndUrl;
    }

    private String generateFileName(MultipartFile multipartFile) {
        return UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
    }

    /**
     * url을 사용하여 파일을 삭제하는 메서드
     * @param key
     */
    public void deleteFile(String key) {
        amazonS3Client.deleteObject(bucket, key);
    }
}