package com.digio.challenge.cucumber.stepdefs;

import com.digio.challenge.adapter.out.feign.EcommerceClient;
import com.digio.challenge.adapter.out.feign.dto.Customer;
import com.digio.challenge.adapter.out.feign.dto.Product;
import com.digio.challenge.cucumber.assertion.ProductAssertion;
import com.digio.challenge.cucumber.datatable.ProductDataTable;
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

public class RecommendedWineStepDefs extends StepDefs {

    private static final String URL_BASE = "/recomendacao/%s/tipo";

    @Autowired
    private ProductAssertion productAssertion;

    @Autowired
    private EcommerceClient ecommerceClient;

    @Autowired
    private MockMvc mockMvc;

    @When("I request a wine recommendation for the customer cpf {string}")
    public void iRequestAWineRecommendationForTheCustomerCpf(String cpf) throws Exception {
        List<Customer> mockCustomers = CustomerDataTableMapper.from(transactionDataTable.getCustomerDataTableList());
        List<Product> mockProducts = ProductDataTableMapper.from(transactionDataTable.getProductDataTableList());
        when(ecommerceClient.getCustomers()).thenReturn(mockCustomers);
        when(ecommerceClient.getProducts()).thenReturn(mockProducts);

        String url = URL_BASE.formatted(cpf);
        ResultActions resultActions = mockMvc.perform(get(url)
                        .accept(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
        transactionDataTable.setResultActions(resultActions);
    }

    @Then("should receive a recommendation for a wine based on most purchase wine type")
    public void shouldReceiveARecommendationForAWineBasedOnMostPurchaseWineType(List<ProductDataTable> expectedProductList) {
        productAssertion.assertProduct(expectedProductList.get(0), transactionDataTable.getResultActions());
    }
}
