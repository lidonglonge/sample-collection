package com.dl.minio.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 小型控制器
 *
 * @author dl
 * @date 2023/11/21 07:28
 */
@RestController
@Slf4j
public class MinioController {

    @Autowired
    private MinioUtil minioUtil;
    @Value("${minio.endpoint}")
    private String address;
    @Value("${minio.bucket-name}")
    private String bucketName;

    @PostMapping("/upload")
    public String upload(MultipartFile file, String filePath) {
        List<String> upload = minioUtil.upload(new MultipartFile[]{file}, filePath);
        return address + "/" + bucketName + "/" + filePath + upload.get(0);
    }

    @PostMapping("/uploadList")
    public List<String> uploadList(MultipartFile[] file) {
        List<String> upload = minioUtil.upload(file);
        upload = upload.stream().map(it -> address + "/" + bucketName + "/" + it).collect(Collectors.toList());

        return upload;
    }

    @GetMapping("/getUrl")
    public String get(String objectName) {
        return minioUtil.getUploadObjectUrl(objectName);

    }

    @GetMapping("/down")
    public ResponseEntity<byte[]> down(String objectName) {
        return minioUtil.download(objectName);
    }


}