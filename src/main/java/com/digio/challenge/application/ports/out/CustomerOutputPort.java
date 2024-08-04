package com.digio.challenge.application.ports.out;

import com.digio.challenge.application.domain.Customer;

import java.util.List;

public interface CustomerOutputPort {

    List<Customer> getTopLoyalCustomers(Integer size);
}
