package com.digio.challenge.cucumber.assertion;

import com.digio.challenge.application.domain.Product;
import com.digio.challenge.cucumber.datatable.ProductDataTable;
import com.digio.challenge.utils.ObjectMapperUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Component
public class ProductAssertion {

    public void assertProduct(ProductDataTable expectedProduct, ResultActions resultActions) {
        Product actualProduct = ObjectMapperUtils.map(new TypeReference<>() {
        }, resultActions);

        assertNotNull(actualProduct);
        makeAssertion(expectedProduct, actualProduct);
    }

    private void makeAssertion(ProductDataTable expected, Product actual) {
        assertEquals(expected.getCode(), actual.code());
        assertEquals(expected.getWineType(), actual.wineType());
        assertEquals(expected.getPrice(), actual.price());
        assertEquals(expected.getVintage(), actual.vintage());
        assertEquals(expected.getPurchaseYear(), actual.purchaseYear());
    }
}
