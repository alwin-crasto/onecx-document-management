package org.tkit.document.management.minio;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import io.minio.MinioClient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApplicationScoped
public class MinioClientConfig {

    MinioConfig minioConfig;

    @Inject
    public MinioClientConfig(MinioConfig minioConfig) {
        this.minioConfig = minioConfig;
    }

    @Produces
    public MinioClient generateMinioClient() {
        try {
            return MinioClient.builder()
                    .endpoint(minioConfig.getUrl())
                    .region(minioConfig.getRegion())
                    .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
