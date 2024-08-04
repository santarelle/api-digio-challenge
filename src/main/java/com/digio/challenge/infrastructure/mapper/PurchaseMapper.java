package com.digio.challenge.infrastructure.mapper;

import com.digio.challenge.application.domain.Customer;
import com.digio.challenge.application.domain.Product;
import com.digio.challenge.application.domain.Purchase;

import java.math.BigDecimal;

public final class PurchaseMapper {

    private PurchaseMapper() {
    }

    public static Purchase from(Customer customer, Product product, Integer quantity, BigDecimal totalValue) {
        return new Purchase(
                customer,
                product,
                quantity,
                totalValue);
    }
}
