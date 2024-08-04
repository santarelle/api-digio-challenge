package com.digio.challenge.infrastructure.service;

import com.digio.challenge.adapter.out.feign.EcommerceClient;
import com.digio.challenge.application.domain.Customer;
import com.digio.challenge.application.domain.Purchase;
import com.digio.challenge.application.ports.out.PurchaseOutputPort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PurchaseService extends AbstractEcommerceService implements PurchaseOutputPort {

    public PurchaseService(EcommerceClient ecommerceClient) {
        super(ecommerceClient);
    }

    @Override
    public List<Purchase> getPurchasesOrderedByTotalValueAsc() {
        Result result = super.fetchProductsAndCustomers();

        var products = result.products();
        var customers = result.customers();

        return customers.stream().flatMap(customerDto -> {
                    Customer customer = new Customer(customerDto.getName(), customerDto.getCpf());
                    return customerDto.getPurchases().stream()
                            .map(purchaseDto -> getPurchase(purchaseDto, products, customer));
                })
                .sorted(Comparator.comparing(Purchase::totalValue))
                .toList();
    }
}
