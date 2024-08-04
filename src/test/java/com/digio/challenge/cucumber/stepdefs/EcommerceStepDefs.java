package com.digio.challenge.cucumber.stepdefs;

import com.digio.challenge.cucumber.datatable.CustomerDataTable;
import com.digio.challenge.cucumber.datatable.ProductDataTable;
import com.digio.challenge.cucumber.datatable.PurchaseDataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.util.List;

public class EcommerceStepDefs extends StepDefs {

    @Given("the system contains clients with the following data")
    public void theSystemContainsClientsWithTheFollowingData(List<CustomerDataTable> customerDataTableList) {
        transactionDataTable.setCustomerDataTableList(customerDataTableList);
    }

    @And("client cpf {string} contains purchases with the following data")
    public void clientCpfContainsPurchasesWithTheFollowingData(String cpf, List<PurchaseDataTable> purchaseDataTables) {
        CustomerDataTable customerDataTable = transactionDataTable.getCustomerDataTableList().stream().filter(c -> cpf.equals(c.getCpf()))
                .findFirst()
                .orElseThrow();
        customerDataTable.setPurchases(purchaseDataTables);
    }

    @And("the system contains products with the following data")
    public void theSystemContainsProductsWithTheFollowingData(List<ProductDataTable> productDataTableList) {
        transactionDataTable.setProductDataTableList(productDataTableList);
    }
}
