package com.digio.challenge.cucumber.stepdefs;

import com.digio.challenge.adapter.out.feign.EcommerceClient;
import com.digio.challenge.adapter.out.feign.dto.Customer;
import com.digio.challenge.adapter.out.feign.dto.Product;
import com.digio.challenge.cucumber.assertion.PurchaseAssertion;
import com.digio.challenge.cucumber.datatable.PurchaseAssertionDataTable;
import com.digio.challenge.cucumber.mapper.CustomerDataTableMapper;
import com.digio.challenge.cucumber.mapper.ProductDataTableMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class LargestPurchaseStepDefs extends StepDefs {

    private static final String URL_BASE = "/maior-compra/%s";

    @Autowired
    private PurchaseAssertion purchaseAssertion;

    @Autowired
    private EcommerceClient ecommerceClient;

    @Autowired
    private MockMvc mockMvc;

    @When("I request the largest purchase of the year {string}")
    public void iRequestTheLargestPurchaseOfTheYear(String year) throws Exception {
        List<Customer> mockCustomers = CustomerDataTableMapper.from(transactionDataTable.getCustomerDataTableList());
        List<Product> mockProducts = ProductDataTableMapper.from(transactionDataTable.getProductDataTableList());
        when(ecommerceClient.getCustomers()).thenReturn(mockCustomers);
        when(ecommerceClient.getProducts()).thenReturn(mockProducts);

        String url = URL_BASE.formatted(year);
        ResultActions resultActions = mockMvc.perform(get(url)
                .accept(APPLICATION_JSON_VALUE));
        transactionDataTable.setResultActions(resultActions);
    }

    @Then("should return the purchase with following data")
    public void shouldReturnThePurchaseWithFollowingData(List<PurchaseAssertionDataTable> expectedPurchaseDataTableList) throws Exception {
        purchaseAssertion.assertPurchase(expectedPurchaseDataTableList.get(0), transactionDataTable.getResultActions(), 200);
    }
}
