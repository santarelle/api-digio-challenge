package com.digio.challenge.cucumber.datatable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDataTable {
    private String name;
    private String cpf;
    private List<PurchaseDataTable> purchases;
}