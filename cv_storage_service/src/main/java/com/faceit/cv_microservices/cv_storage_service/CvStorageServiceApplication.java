package com.faceit.cv_microservices.cv_storage_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableCaching
@EnableEurekaClient
@SpringBootApplication
@EnableElasticsearchRepositories
public class CvStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CvStorageServiceApplication.class, args);
    }
}
