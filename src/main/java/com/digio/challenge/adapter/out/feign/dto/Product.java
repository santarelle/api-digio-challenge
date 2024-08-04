package com.digio.challenge.adapter.out.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @JsonProperty("codigo")
    private Integer code;

    @JsonProperty("tipo_vinho")
    private String wineType;

    @JsonProperty("preco")
    private BigDecimal price;

    @JsonProperty("safra")
    private String vintage;

    @JsonProperty("ano_compra")
    private Integer purchaseYear;
}
