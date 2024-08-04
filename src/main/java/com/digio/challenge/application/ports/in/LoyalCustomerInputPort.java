package com.digio.challenge.application.ports.in;

import com.digio.challenge.application.domain.Customer;

import java.util.List;

public interface LoyalCustomerInputPort {
    List<Customer> execute(Integer size);
}
