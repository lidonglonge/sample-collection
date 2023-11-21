package com.dl.minio.utils;
import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 最小io客户端配置
 *
 * @author dl
 * @date 2023/11/11 04:22
 */
@Data
@Component
public class MinIoClientConfig {
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;

    /**
     * 注入minio 客户端
     *
     * @return {@link MinioClient}
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}