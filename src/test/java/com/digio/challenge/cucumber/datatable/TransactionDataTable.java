package com.digio.challenge.cucumber.datatable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDataTable {

    private List<CustomerDataTable> customerDataTableList;
    private List<ProductDataTable> productDataTableList;
    private ResultActions resultActions;

}
