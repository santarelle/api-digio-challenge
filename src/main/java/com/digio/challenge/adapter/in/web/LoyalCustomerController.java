package com.digio.challenge.adapter.in.web;

import com.digio.challenge.application.domain.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Loyal Customers")
public interface LoyalCustomerController {

    @Operation(summary = "Retrieve a list of the top most loyal customers based on recurring purchases with higher values")
    ResponseEntity<List<Customer>> getTopLoyalCustomers(@Parameter(name = "size", example = "3") Integer size);
}
