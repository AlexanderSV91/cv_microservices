package com.faceit.cv_microservices.parser_cv_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class ParserCvServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParserCvServiceApplication.class, args);
    }
}
