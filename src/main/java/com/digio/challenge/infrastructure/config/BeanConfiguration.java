package com.digio.challenge.infrastructure.config;

import com.digio.challenge.application.ports.out.CustomerOutputPort;
import com.digio.challenge.application.ports.out.ProductOutputPort;
import com.digio.challenge.application.ports.out.PurchaseOutputPort;
import com.digio.challenge.application.usecase.LargestPurchaseYearUseCase;
import com.digio.challenge.application.usecase.LoyalCustomerUseCase;
import com.digio.challenge.application.usecase.PurchaseUseCase;
import com.digio.challenge.application.usecase.RecommendedWineUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PurchaseUseCase purchaseUseCase(PurchaseOutputPort purchaseOutputPort) {
        return new PurchaseUseCase(purchaseOutputPort);
    }

    @Bean
    public LargestPurchaseYearUseCase largestPurchaseYearUseCase(PurchaseOutputPort purchaseOutputPort) {
        return new LargestPurchaseYearUseCase(purchaseOutputPort);
    }

    @Bean
    public LoyalCustomerUseCase loyalCustomerUseCase(CustomerOutputPort customerOutputPort) {
        return new LoyalCustomerUseCase(customerOutputPort);
    }

    @Bean
    public RecommendedWineUseCase recommendedWineUseCase(ProductOutputPort productOutputPort) {
        return new RecommendedWineUseCase(productOutputPort);
    }

}
