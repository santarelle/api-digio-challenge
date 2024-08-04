package com.digio.challenge.adapter.in.web.impl;


import com.digio.challenge.adapter.out.feign.EcommerceClient;
import com.digio.challenge.adapter.out.feign.dto.Product;
import com.digio.challenge.application.domain.Purchase;
import com.digio.challenge.fixture.ResourceFixture;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseControllerImplTest {

    private static final String URL_BASE = "/compras";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EcommerceClient ecommerceClient;

    @Test
    public void shouldReturnPurchasesOrderAscendingByValue() throws Exception {

        String productsJson = ResourceFixture.loadJson("products.json");
        String customersJson = ResourceFixture.loadJson("customers.json");
        List<Product> mockProducts = objectMapper.readValue(productsJson, new TypeReference<>() {
        });
        List<com.digio.challenge.adapter.out.feign.dto.Customer> mockCustomers = objectMapper.readValue(customersJson, new TypeReference<>() {
        });

        when(ecommerceClient.getProducts()).thenReturn(mockProducts);
        when(ecommerceClient.getCustomers()).thenReturn(mockCustomers);

        String contentAsString = mockMvc.perform(get(URL_BASE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(UTF_8);
        List<Purchase> actualPurchases = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });

        assertNotNull(actualPurchases);
        assertThat(actualPurchases).isNotEmpty().hasSize(37);

        String expectedPurchasesJson = ResourceFixture.loadJson("expected-purchases.json");
        List<Purchase> expectedPurchases = objectMapper.readValue(expectedPurchasesJson, new TypeReference<>() {
        });
        assertPurchaseList(expectedPurchases, actualPurchases);

        verify(ecommerceClient, times(1)).getProducts();
        verify(ecommerceClient, times(1)).getCustomers();
        verifyNoMoreInteractions(ecommerceClient);
    }

    public void assertPurchaseList(List<Purchase> expectedPurchases, List<Purchase> actualPurchases) {
        assertEquals(expectedPurchases.size(), actualPurchases.size());
        for (int i = 0; i < expectedPurchases.size(); i++) {
            Purchase expected = expectedPurchases.get(i);
            Purchase actual = actualPurchases.get(i);
            makeAssertion(expected, actual);
        }
    }

    private void makeAssertion(Purchase expected, Purchase actual) {
        assertNotNull(actual);
        assertEquals(expected.customer().name(), actual.customer().name());
        assertEquals(expected.customer().cpf(), actual.customer().cpf());
        assertEquals(expected.product().code(), actual.product().code());
        assertEquals(expected.product().wineType(), actual.product().wineType());
        assertEquals(expected.product().price(), actual.product().price());
        assertEquals(expected.product().vintage(), actual.product().vintage());
        assertEquals(expected.product().purchaseYear(), actual.product().purchaseYear());
        assertEquals(expected.quantity(), actual.quantity());
        assertThat(actual.totalValue()).isEqualByComparingTo(expected.totalValue());
    }
}