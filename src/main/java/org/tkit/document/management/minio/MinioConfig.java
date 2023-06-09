package org.tkit.document.management.minio;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "minio")
public interface MinioConfig {
    @WithName("accesskey")
    String getAccessKey();

    @WithName("secretkey")
    String getSecretKey();

    @WithName("url")
    String getUrl();

    @WithName("bucket")
    String getBucket();

    @WithName("region")
    String getRegion();
}
