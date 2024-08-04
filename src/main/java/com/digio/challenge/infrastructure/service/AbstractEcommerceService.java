package com.digio.challenge.infrastructure.service;

import com.digio.challenge.adapter.out.feign.EcommerceClient;
import com.digio.challenge.adapter.out.feign.dto.Customer;
import com.digio.challenge.adapter.out.feign.dto.Product;
import com.digio.challenge.application.domain.Purchase;
import com.digio.challenge.application.exception.BusinessLogicException;
import com.digio.challenge.application.exception.CustomerNotFoundException;
import com.digio.challenge.application.exception.ProductNotFoundException;
import com.digio.challenge.infrastructure.mapper.ProductMapper;
import com.digio.challenge.infrastructure.mapper.PurchaseMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public abstract class AbstractEcommerceService {

    private final EcommerceClient ecommerceClient;

    protected AbstractEcommerceService(EcommerceClient ecommerceClient) {
        this.ecommerceClient = ecommerceClient;
    }

    protected Result fetchProductsAndCustomers() {
        try {
            var productsFuture = CompletableFuture.supplyAsync(ecommerceClient::getProducts);
            var customersFuture = CompletableFuture.supplyAsync(ecommerceClient::getCustomers);
            CompletableFuture.allOf(productsFuture, customersFuture).get();
            return new Result(productsFuture.get(), customersFuture.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new BusinessLogicException("Error fetching data from e-commerce. %s".formatted(e.getMessage()), e);
        }
    }

    protected record Result(List<Product> products, List<Customer> customers) {
    }

    protected Product getProductByCode(List<Product> products, String productCode) {
        return products.stream()
                .filter(product -> productCode.equals(product.getCode().toString()))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found by code: %s".formatted(productCode)));
    }

    protected Purchase getPurchase(com.digio.challenge.adapter.out.feign.dto.Purchase purchase, List<Product> products, com.digio.challenge.application.domain.Customer customer) {
        var product = getProductByCode(products, purchase.getProductCode());
        var totalValue = product.getPrice().multiply(new BigDecimal(purchase.getQuantity()));
        return PurchaseMapper.from(customer, ProductMapper.from(product), purchase.getQuantity(), totalValue);
    }

    protected Customer getCustomerByCpf(List<Customer> customers, String customerCpf) {
        return customers.stream().filter(customerDto -> customerCpf.equals(customerDto.getCpf()))
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found by cpf: %s".formatted(customerCpf)));
    }
}
