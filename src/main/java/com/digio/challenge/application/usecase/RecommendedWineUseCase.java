package com.digio.challenge.application.usecase;

import com.digio.challenge.application.domain.Product;
import com.digio.challenge.application.ports.in.RecommendedWineInputPort;
import com.digio.challenge.application.ports.out.ProductOutputPort;

public class RecommendedWineUseCase implements RecommendedWineInputPort {

    private final ProductOutputPort productOutputPort;

    public RecommendedWineUseCase(ProductOutputPort productOutputPort) {
        this.productOutputPort = productOutputPort;
    }

    @Override
    public Product execute(String customerCpf) {
        return productOutputPort.getRecommendedWineByCustomer(customerCpf);
    }
}
