package com.digio.challenge.application.ports.out;

import com.digio.challenge.application.domain.Product;

public interface ProductOutputPort {

    Product getRecommendedWineByCustomer(String customerCpf);
}
