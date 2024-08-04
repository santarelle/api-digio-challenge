package com.digio.challenge.cucumber.mapper;

import com.digio.challenge.adapter.out.feign.dto.Product;
import com.digio.challenge.cucumber.datatable.ProductDataTable;

import java.util.List;

public final class ProductDataTableMapper {

    private ProductDataTableMapper() {
    }

    public static List<Product> from(List<ProductDataTable> dataTableList) {
        return dataTableList.stream().map(productDataTable -> new Product(
                productDataTable.getCode(),
                productDataTable.getWineType(),
                productDataTable.getPrice(),
                productDataTable.getVintage(),
                productDataTable.getPurchaseYear()
        )).toList();
    }
}
