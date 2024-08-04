package com.digio.challenge.config;

import com.digio.challenge.adapter.out.feign.EcommerceClient;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MockBeanConfiguration {

    @Primary
    @Bean
    public EcommerceClient ecommerceClient() {
        return Mockito.mock(EcommerceClient.class);
    }

}
