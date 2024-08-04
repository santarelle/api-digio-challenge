package com.digio.challenge.infrastructure.service;

import com.digio.challenge.adapter.out.feign.EcommerceClient;
import com.digio.challenge.application.comparator.LoyalCustomerPurchaseComparator;
import com.digio.challenge.application.domain.Customer;
import com.digio.challenge.application.domain.CustomerPurchase;
import com.digio.challenge.application.domain.Purchase;
import com.digio.challenge.application.ports.out.CustomerOutputPort;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Log4j2
@Service
public class CustomerService extends AbstractEcommerceService implements CustomerOutputPort {

    public CustomerService(EcommerceClient ecommerceClient) {
        super(ecommerceClient);
    }

    public List<Customer> getTopLoyalCustomers(Integer size) {
        Result result = super.fetchProductsAndCustomers();

        var products = result.products();
        var customers = result.customers();

        return customers.stream().map(customerDto -> {
                    Customer customer = new Customer(customerDto.getName(), customerDto.getCpf());
                    List<Purchase> purchases = customerDto.getPurchases().stream()
                            .map(purchaseDto -> getPurchase(purchaseDto, products, customer))
                            .toList();
                    var totalValue = purchases.stream().map(Purchase::totalValue).reduce(BigDecimal.ZERO, BigDecimal::add);
                    return new CustomerPurchase(customer, purchases, purchases.size(), totalValue);
                })
                .sorted(new LoyalCustomerPurchaseComparator())
                .distinct()
                .limit(size)
                .map(customerPurchase -> {
                    log.debug("{} quantity={} totalValue={}", customerPurchase.customer(), customerPurchase.quantity(), customerPurchase.totalValue());
                    return customerPurchase.customer();
                })
                .toList();
    }

}
