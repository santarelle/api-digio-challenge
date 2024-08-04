package com.digio.challenge.cucumber.stepdefs;

import com.digio.challenge.cucumber.datatable.TransactionDataTable;

public class StepDefs {

    protected static TransactionDataTable transactionDataTable;

    public void initializeContext() {
        transactionDataTable = TransactionDataTable.builder().build();
    }
}
