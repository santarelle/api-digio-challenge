package com.digio.challenge.cucumber.assertion;

import com.digio.challenge.application.domain.Purchase;
import com.digio.challenge.cucumber.datatable.PurchaseAssertionDataTable;
import com.digio.challenge.utils.ObjectMapperUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class PurchaseAssertion {

    public void assertPurchaseList(List<PurchaseAssertionDataTable> expectedPurchases, ResultActions resultActions, Integer statusCode) throws Exception {
        resultActions.andExpect(status().is(statusCode));
        List<Purchase> actualPurchases = ObjectMapperUtils.map(new TypeReference<>() {
        }, resultActions);
        assertEquals(expectedPurchases.size(), actualPurchases.size());
        for (int i = 0; i < expectedPurchases.size(); i++) {
            PurchaseAssertionDataTable expected = expectedPurchases.get(i);
            Purchase actual = actualPurchases.get(i);
            makeAssertion(expected, actual);
        }
    }

    public void assertPurchase(PurchaseAssertionDataTable expectedPurchase, ResultActions resultActions, Integer statusCode) throws Exception {
        resultActions.andExpect(status().is(statusCode));
        Purchase actualPurchase = ObjectMapperUtils.map(new TypeReference<>() {
        }, resultActions);
        makeAssertion(expectedPurchase, actualPurchase);
    }

    private void makeAssertion(PurchaseAssertionDataTable expected, Purchase actual) {
        assertNotNull(actual);
        assertEquals(expected.getCustomerName(), actual.customer().name());
        assertEquals(expected.getCustomerCpf(), actual.customer().cpf());
        assertEquals(expected.getProductCode(), actual.product().code());
        assertEquals(expected.getProductWineType(), actual.product().wineType());
        assertEquals(expected.getProductPrice(), actual.product().price());
        assertEquals(expected.getProductVintage(), actual.product().vintage());
        assertEquals(expected.getProductPurchaseYear(), actual.product().purchaseYear());
        assertEquals(expected.getQuantity(), actual.quantity());
        assertEquals(expected.getTotalValue(), actual.totalValue());
    }
}
