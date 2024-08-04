package com.digio.challenge.application.usecase;

import com.digio.challenge.application.domain.Customer;
import com.digio.challenge.application.ports.in.LoyalCustomerInputPort;
import com.digio.challenge.application.ports.out.CustomerOutputPort;

import java.util.List;

public class LoyalCustomerUseCase implements LoyalCustomerInputPort {

    private final CustomerOutputPort customerOutputPort;

    public LoyalCustomerUseCase(CustomerOutputPort customerOutputPort) {
        this.customerOutputPort = customerOutputPort;
    }

    @Override
    public List<Customer> execute(Integer size) {
        return customerOutputPort.getTopLoyalCustomers(size);
    }
}
