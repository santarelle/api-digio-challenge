package com.digio.challenge.adapter.out.feign;

import com.digio.challenge.adapter.out.feign.dto.Customer;
import com.digio.challenge.adapter.out.feign.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "product", url = "${clients.ecommerce.url}", primary = false)
public interface EcommerceClient {

    @GetMapping(value = "/produtos-mnboX5IPl6VgG390FECTKqHsD9SkLS.json", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Product> getProducts();

    @GetMapping(value = "/clientes-Vz1U6aR3GTsjb3W8BRJhcNKmA81pVh.json", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Customer> getCustomers();
}
