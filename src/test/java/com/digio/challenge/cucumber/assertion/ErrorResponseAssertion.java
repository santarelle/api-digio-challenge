package com.digio.challenge.cucumber.assertion;

import com.digio.challenge.cucumber.datatable.ErrorResponseDataTable;
import com.digio.challenge.infrastructure.exceptionhandler.ErrorResponse;
import com.digio.challenge.utils.ObjectMapperUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Component
public class ErrorResponseAssertion {

    public void assertErrorResponse(ErrorResponseDataTable expectedErrorResponse, ResultActions resultActions, Integer statusCode) throws Exception {
        ErrorResponse actualErrorResponse = ObjectMapperUtils.map(new TypeReference<>() {
        }, resultActions);
        resultActions.andExpect(MockMvcResultMatchers.status().is(statusCode));

        assertNotNull(actualErrorResponse);
        assertEquals(expectedErrorResponse.getTitle(), actualErrorResponse.getTitle());
        assertEquals(expectedErrorResponse.getStatus(), actualErrorResponse.getStatus());
        assertEquals(expectedErrorResponse.getDetail(), actualErrorResponse.getDetail());
        assertNotNull(actualErrorResponse.getTimestamp());
    }
}
