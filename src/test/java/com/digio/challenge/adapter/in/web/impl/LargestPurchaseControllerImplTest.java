package com.digio.challenge.adapter.in.web.impl;

import com.digio.challenge.adapter.out.feign.EcommerceClient;
import com.digio.challenge.adapter.out.feign.dto.Customer;
import com.digio.challenge.adapter.out.feign.dto.Product;
import com.digio.challenge.application.domain.Purchase;
import com.digio.challenge.fixture.ResourceFixture;
import com.digio.challenge.infrastructure.exceptionhandler.ErrorResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
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
public class LargestPurchaseControllerImplTest {

    private static final String URL_BASE = "/maior-compra/%s";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EcommerceClient ecommerceClient;

    @Test
    public void givenExistentYearShouldReturnTheBiggestPurchaseSuccess() throws Exception {

        String givenYear = "2019";
        String url = URL_BASE.formatted(givenYear);

        String productsJson = ResourceFixture.loadJson("products.json");
        String customersJson = ResourceFixture.loadJson("customers.json");
        List<Product> mockProducts = objectMapper.readValue(productsJson, new TypeReference<>() {
        });
        List<Customer> mockCustomers = objectMapper.readValue(customersJson, new TypeReference<>() {
        });

        when(ecommerceClient.getProducts()).thenReturn(mockProducts);
        when(ecommerceClient.getCustomers()).thenReturn(mockCustomers);

        String contentAsString = mockMvc.perform(get(url)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(UTF_8);
        Purchase result = objectMapper.readValue(contentAsString, Purchase.class);

        assertNotNull(result);
        assertEquals(15, result.quantity());
        assertThat(result.totalValue()).isEqualByComparingTo("1897.5");

        assertNotNull(result.customer());
        assertEquals("Ian Joaquim Giovanni Santos", result.customer().name());
        assertEquals("96718391344", result.customer().cpf());

        assertNotNull(result.product());
        assertEquals(2, result.product().code());
        assertEquals("Branco", result.product().wineType());
        assertThat(result.product().price()).isEqualByComparingTo("126.5");
        assertEquals("2018", result.product().vintage());
        assertEquals(2019, result.product().purchaseYear());

        verify(ecommerceClient, times(1)).getProducts();
        verify(ecommerceClient, times(1)).getCustomers();
        verifyNoMoreInteractions(ecommerceClient);
    }

    @Test
    public void givenNonExistentYearShouldReturnPurchaseNotFoundError() throws Exception {

        String givenYear = "1900";
        String url = URL_BASE.formatted(givenYear);

        String productsJson = ResourceFixture.loadJson("products.json");
        String customersJson = ResourceFixture.loadJson("customers.json");
        List<Product> mockProducts = objectMapper.readValue(productsJson, new TypeReference<>() {
        });
        List<Customer> mockCustomers = objectMapper.readValue(customersJson, new TypeReference<>() {
        });

        when(ecommerceClient.getProducts()).thenReturn(mockProducts);
        when(ecommerceClient.getCustomers()).thenReturn(mockCustomers);

        String contentAsString = mockMvc.perform(get(url)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString(UTF_8);
        ErrorResponse errorResponse = objectMapper.readValue(contentAsString, ErrorResponse.class);

        assertNotNull(errorResponse);
        assertEquals("404 NOT_FOUND", errorResponse.getTitle());
        assertEquals(404, errorResponse.getStatus());
        assertEquals("There is no purchase for year 1900", errorResponse.getDetail());
        assertNotNull(errorResponse.getTimestamp());

        verify(ecommerceClient, times(1)).getProducts();
        verify(ecommerceClient, times(1)).getCustomers();
        verifyNoMoreInteractions(ecommerceClient);
    }

    @Test
    public void givenYearWhenEcommerceThrowExceptionShouldReturnEcommerceError() throws Exception {

        String givenYear = "1900";
        String url = URL_BASE.formatted(givenYear);

        when(ecommerceClient.getProducts()).thenThrow(FeignException.class);
        when(ecommerceClient.getCustomers()).thenThrow(FeignException.class);

        String contentAsString = mockMvc.perform(get(url)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isInternalServerError())
                .andReturn()
                .getResponse()
                .getContentAsString(UTF_8);
        ErrorResponse errorResponse = objectMapper.readValue(contentAsString, ErrorResponse.class);

        assertNotNull(errorResponse);
        assertEquals("500 INTERNAL_SERVER_ERROR", errorResponse.getTitle());
        assertEquals(500, errorResponse.getStatus());
        assertEquals("Error fetching data from e-commerce. feign.FeignException", errorResponse.getDetail());
        assertNotNull(errorResponse.getTimestamp());

        verify(ecommerceClient, times(1)).getProducts();
        verify(ecommerceClient, times(1)).getCustomers();
        verifyNoMoreInteractions(ecommerceClient);
    }
}