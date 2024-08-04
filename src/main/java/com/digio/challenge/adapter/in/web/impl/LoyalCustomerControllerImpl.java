package com.digio.challenge.adapter.in.web.impl;

import com.digio.challenge.adapter.in.web.LoyalCustomerController;
import com.digio.challenge.application.domain.Customer;
import com.digio.challenge.application.ports.in.LoyalCustomerInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/clientes-fieis")
@RequiredArgsConstructor
public class LoyalCustomerControllerImpl implements LoyalCustomerController {

    private final LoyalCustomerInputPort loyalCustomerInputPort;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getTopLoyalCustomers(@RequestParam(value = "size", defaultValue = "3") Integer size) {
        log.info("Request /clientes-fieis - size: {}", size);
        List<Customer> response = loyalCustomerInputPort.execute(size);
        log.info("Response /clientes-fieis - site: {} - {}", size, response);
        return ResponseEntity.ok(response);
    }
}
