package com.digio.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApiDigioChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiDigioChallengeApplication.class, args);
    }

}
