package com.digio.challenge.cucumber.assertion;

import com.digio.challenge.application.domain.Customer;
import com.digio.challenge.cucumber.datatable.CustomerDataTable;
import com.digio.challenge.utils.ObjectMapperUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Component
public class CustomerAssertion {

    public void assertCustomerList(List<CustomerDataTable> expectedCustomers, ResultActions resultActions) {
        List<Customer> actualCustomers = ObjectMapperUtils.map(new TypeReference<>() {
        }, resultActions);

        assertNotNull(actualCustomers);
        assertEquals(expectedCustomers.size(), actualCustomers.size());

        for (int i = 0; i < expectedCustomers.size(); i++) {
            CustomerDataTable expected = expectedCustomers.get(i);
            Customer actual = actualCustomers.get(i);
            makeAssertion(expected, actual);
        }
    }

    private void makeAssertion(CustomerDataTable expected, Customer actual) {
        assertEquals(expected.getName(), actual.name());
        assertEquals(expected.getCpf(), actual.cpf());
    }

}
