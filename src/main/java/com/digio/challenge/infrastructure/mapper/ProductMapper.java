package com.digio.challenge.infrastructure.mapper;

import com.digio.challenge.application.domain.Product;

public final class ProductMapper {

    private ProductMapper() {
    }

    public static Product from(com.digio.challenge.adapter.out.feign.dto.Product product) {
        return new Product(product.getCode(),
                product.getWineType(),
                product.getPrice(),
                product.getVintage(),
                product.getPurchaseYear());
    }
}
