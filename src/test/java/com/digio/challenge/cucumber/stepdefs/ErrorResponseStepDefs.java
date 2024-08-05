package com.digio.challenge.cucumber.stepdefs;

import com.digio.challenge.cucumber.assertion.ErrorResponseAssertion;
import com.digio.challenge.cucumber.datatable.ErrorResponseDataTable;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ErrorResponseStepDefs extends StepDefs {

    @Autowired
    private ErrorResponseAssertion errorResponseAssertion;

    @Then("should return the following error data")
    public void shouldReturnTheFollowingErrorData(List<ErrorResponseDataTable> errorResponseDataTableList) throws Exception {
        errorResponseAssertion.assertErrorResponse(errorResponseDataTableList.get(0), transactionDataTable.getResultActions(), 404);
    }
}
