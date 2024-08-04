package com.digio.challenge.adapter.in.web.impl;

import com.digio.challenge.adapter.out.feign.EcommerceClient;
import com.digio.challenge.adapter.out.feign.dto.Product;
import com.digio.challenge.fixture.ResourceFixture;
import com.digio.challenge.infrastructure.exceptionhandler.ErrorResponse;
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
public class RecommendedWineControllerImplTest {

    private static final String URL_BASE = "/recomendacao/%s/tipo";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EcommerceClient ecommerceClient;

    @Test
    public void shouldReturnProductWineRecommended() throws Exception {

        String productsJson = ResourceFixture.loadJson("products.json");
        String customersJson = ResourceFixture.loadJson("customers.json");
        List<Product> mockProducts = objectMapper.readValue(productsJson, new TypeReference<>() {
        });
        List<com.digio.challenge.adapter.out.feign.dto.Customer> mockCustomers = objectMapper.readValue(customersJson, new TypeReference<>() {
        });

        when(ecommerceClient.getProducts()).thenReturn(mockProducts);
        when(ecommerceClient.getCustomers()).thenReturn(mockCustomers);

        String url = URL_BASE.formatted("05870189179");
        String contentAsString = mockMvc.perform(get(url)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(UTF_8);
        com.digio.challenge.application.domain.Product result = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });

        assertNotNull(result);
        assertEquals(20, result.code());
        assertEquals("Chardonnay", result.wineType());
        assertThat(result.price()).isEqualByComparingTo("130.75");
        assertEquals("2020", result.vintage());
        assertEquals(2021, result.purchaseYear());

        verify(ecommerceClient, times(1)).getProducts();
        verify(ecommerceClient, times(1)).getCustomers();
        verifyNoMoreInteractions(ecommerceClient);
    }

    @Test
    public void shouldReturnCustomerNotFoundError() throws Exception {

        String productsJson = ResourceFixture.loadJson("products.json");
        String customersJson = ResourceFixture.loadJson("customers.json");
        List<Product> mockProducts = objectMapper.readValue(productsJson, new TypeReference<>() {
        });
        List<com.digio.challenge.adapter.out.feign.dto.Customer> mockCustomers = objectMapper.readValue(customersJson, new TypeReference<>() {
        });

        when(ecommerceClient.getProducts()).thenReturn(mockProducts);
        when(ecommerceClient.getCustomers()).thenReturn(mockCustomers);

        String url = URL_BASE.formatted("00000000000");
        String contentAsString = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString(UTF_8);
        ErrorResponse errorResponse = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });

        assertNotNull(errorResponse);
        assertEquals("404 NOT_FOUND", errorResponse.getTitle());
        assertEquals(404, errorResponse.getStatus());
        assertEquals("Customer not found by cpf: 00000000000", errorResponse.getDetail());
        assertNotNull(errorResponse.getTimestamp());

        verify(ecommerceClient, times(1)).getProducts();
        verify(ecommerceClient, times(1)).getCustomers();
        verifyNoMoreInteractions(ecommerceClient);
    }
}