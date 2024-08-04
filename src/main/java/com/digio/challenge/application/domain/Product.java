package com.digio.challenge.application.domain;

import java.math.BigDecimal;

public record Product(
        Integer code,
        String wineType,
        BigDecimal price,
        String vintage,
        Integer purchaseYear) {
}
