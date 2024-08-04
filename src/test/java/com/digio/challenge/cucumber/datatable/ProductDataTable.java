package com.digio.challenge.cucumber.datatable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDataTable {
    private Integer code;
    private String wineType;
    private BigDecimal price;
    private String vintage;
    private Integer purchaseYear;
}
