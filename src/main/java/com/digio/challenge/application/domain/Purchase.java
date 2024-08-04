package com.digio.challenge.application.domain;

import java.math.BigDecimal;

public record Purchase(
        Customer customer,
        Product product,
        Integer quantity,
        BigDecimal totalValue) {
}
