package org.tkit.document.management.test;

import org.junit.jupiter.api.BeforeEach;
import org.tkit.quarkus.test.docker.DockerComposeService;
import org.tkit.quarkus.test.docker.DockerComposeTestResource;
import org.tkit.quarkus.test.docker.DockerService;
import org.tkit.quarkus.test.docker.QuarkusTestcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.quarkus.test.common.QuarkusTestResource;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;

/**
 * AbstractTest
 */
@QuarkusTestcontainers
@QuarkusTestResource(DockerComposeTestResource.class)
public abstract class AbstractTest {
    public static final String USER = "onecx";

    static {
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory(
                        (cls, charset) -> {
                            ObjectMapper objectMapper = new ObjectMapper();
                            objectMapper.registerModule(new JavaTimeModule());
                            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                            return objectMapper;
                        }));

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @DockerService("tkit-document-management")
    protected DockerComposeService service;

    // Configure the containers for the test
    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeEach
    void init() {
        // update the rest assured port for the integration test
        if (service != null) {
            RestAssured.port = service.getPort(8080);
            RestAssured.baseURI = "http://" + service.getHost();
        }
    }
}
