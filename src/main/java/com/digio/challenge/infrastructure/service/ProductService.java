package com.digio.challenge.infrastructure.service;

import com.digio.challenge.adapter.out.feign.EcommerceClient;
import com.digio.challenge.application.domain.Customer;
import com.digio.challenge.application.domain.Product;
import com.digio.challenge.application.domain.Purchase;
import com.digio.challenge.application.exception.RecommendedWineNotFoundException;
import com.digio.challenge.application.ports.out.ProductOutputPort;
import com.digio.challenge.infrastructure.mapper.ProductMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Log4j2
@Service
public class ProductService extends AbstractEcommerceService implements ProductOutputPort {

    public ProductService(EcommerceClient ecommerceClient) {
        super(ecommerceClient);
    }

    @Override
    public Product getRecommendedWineByCustomer(String customerCpf) {
        var result = fetchProductsAndCustomers();

        var products = result.products();
        var customers = result.customers();

        var customerDto = getCustomerByCpf(customers, customerCpf);

        List<Purchase> purchases = customerDto.getPurchases().stream().map(purchaseDto -> {
            Purchase purchase = getPurchase(purchaseDto, products, new Customer(customerDto.getName(), customerDto.getCpf()));
            log.debug("{} {} quantity={}", purchase.customer(), purchase.product(), purchase.quantity());
            return purchase;
        }).toList();

        String mostPurchasedWine = getMostPurchasedWine(purchases);
        List<Integer> purchasedProductCode = purchases.stream().map(purchase -> purchase.product().code()).toList();

        var recommendedWine = products.stream()
                .filter(product -> mostPurchasedWine.equals(product.getWineType()))
                .filter(product -> !purchasedProductCode.contains(product.getCode()))
                .findAny()
                .orElseThrow(() -> new RecommendedWineNotFoundException("Recommended wine not found by customer cpf: %s".formatted(customerCpf)));

        return ProductMapper.from(recommendedWine);
    }

    private String getMostPurchasedWine(List<Purchase> purchases) {
        Map.Entry<String, Integer> wineByQty = purchases.stream()
                .collect(groupingBy(purchase -> purchase.product().wineType(), summingInt(Purchase::quantity))).entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow();
        log.debug("getMostPurchasedWine {}", wineByQty);
        return wineByQty.getKey();
    }

}
