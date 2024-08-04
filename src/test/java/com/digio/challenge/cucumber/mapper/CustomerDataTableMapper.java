package com.digio.challenge.cucumber.mapper;

import com.digio.challenge.adapter.out.feign.dto.Customer;
import com.digio.challenge.adapter.out.feign.dto.Purchase;
import com.digio.challenge.cucumber.datatable.CustomerDataTable;
import com.digio.challenge.cucumber.datatable.PurchaseDataTable;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public final class CustomerDataTableMapper {

    private CustomerDataTableMapper() {
    }

    public static List<Customer> from(List<CustomerDataTable> dataTables) {
        return dataTables.stream()
                .map(customerDataTable -> new Customer(customerDataTable.getName(), customerDataTable.getCpf(),
                        defaultIfNull(customerDataTable.getPurchases(), new ArrayList<PurchaseDataTable>())
                                .stream()
                                .map(purchaseDataTable -> new Purchase(purchaseDataTable.getProductCode(), purchaseDataTable.getQuantity()))
                                .toList()))
                .toList();
    }
}
