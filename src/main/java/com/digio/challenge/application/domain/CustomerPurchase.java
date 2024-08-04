package com.digio.challenge.application.domain;

import java.math.BigDecimal;
import java.util.List;

public record CustomerPurchase(Customer customer, List<Purchase> purchases, Integer quantity,
                               BigDecimal totalValue) {
}