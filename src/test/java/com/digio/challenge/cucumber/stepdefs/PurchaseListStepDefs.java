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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PurchaseListStepDefs extends StepDefs {

    @Autowired
    private PurchaseAssertion purchaseAssertion;

    @Autowired
    private EcommerceClient ecommerceClient;

    @Autowired
    private MockMvc mockMvc;

    @When("I request the list of purchases")
    public void iRequestTheListOfPurchases() throws Exception {
        List<Customer> mockCustomers = CustomerDataTableMapper.from(transactionDataTable.getCustomerDataTableList());
        List<Product> mockProducts = ProductDataTableMapper.from(transactionDataTable.getProductDataTableList());
        when(ecommerceClient.getCustomers()).thenReturn(mockCustomers);
        when(ecommerceClient.getProducts()).thenReturn(mockProducts);

        String url = "/compras";
        ResultActions resultActions = mockMvc.perform(get(url)
                        .accept(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
        transactionDataTable.setResultActions(resultActions);
    }

    @Then("should receive the list of purchases sorted in ascending order by value")
    public void shouldReceiveTheListOfPurchasesSortedInAscendingOrderByValue(List<PurchaseAssertionDataTable> expectedPurchaseDataTableList) throws Exception {
        purchaseAssertion.assertPurchaseList(expectedPurchaseDataTableList, transactionDataTable.getResultActions(), 200);
    }
}
