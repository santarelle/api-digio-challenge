package com.digio.challenge.adapter.in.web.impl;

import com.digio.challenge.adapter.out.feign.EcommerceClient;
import com.digio.challenge.adapter.out.feign.dto.Product;
import com.digio.challenge.application.domain.Customer;
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

import java.util.Arrays;
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
public class LoyalCustomerControllerImplTest {

    private static final String URL_BASE = "/clientes-fieis";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EcommerceClient ecommerceClient;

    @Test
    public void shouldReturnTop3LoyalCustomersSuccess() throws Exception {

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
        List<Customer> result = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });

        List<Customer> expectedCustomers = Arrays.asList(
                new Customer("Andreia Emanuelly da Mata", "27737287426"),
                new Customer("Ian Joaquim Giovanni Santos", "96718391344"),
                new Customer("Geraldo Pedro Julio Nascimento", "05870189179"));

        assertNotNull(result);
        assertThat(result).isNotEmpty().hasSize(3)
                .containsExactlyInAnyOrderElementsOf(expectedCustomers);

        verify(ecommerceClient, times(1)).getProducts();
        verify(ecommerceClient, times(1)).getCustomers();
        verifyNoMoreInteractions(ecommerceClient);
    }

    @Test
    public void whenEcommerceThrowExceptionShouldReturnEcommerceError() throws Exception {

        when(ecommerceClient.getProducts()).thenThrow(FeignException.class);
        when(ecommerceClient.getCustomers()).thenThrow(FeignException.class);

        String contentAsString = mockMvc.perform(get(URL_BASE)
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