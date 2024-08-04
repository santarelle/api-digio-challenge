package com.digio.challenge.infrastructure.config;

import com.digio.challenge.application.ports.out.PurchaseOutputPort;
import com.digio.challenge.application.usecase.PurchaseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PurchaseUseCase purchaseUseCase(PurchaseOutputPort purchaseOutputPort) {
        return new PurchaseUseCase(purchaseOutputPort);
    }

}