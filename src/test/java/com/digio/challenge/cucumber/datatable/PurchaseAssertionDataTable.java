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
public class PurchaseAssertionDataTable {

    private String customerName;
    private String customerCpf;
    private Integer productCode;
    private String productWineType;
    private BigDecimal productPrice;
    private String productVintage;
    private Integer productPurchaseYear;
    private Integer quantity;
    private BigDecimal totalValue;
}
