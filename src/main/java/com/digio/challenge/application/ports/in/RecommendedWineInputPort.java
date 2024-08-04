package com.digio.challenge.application.ports.in;

import com.digio.challenge.application.domain.Product;

public interface RecommendedWineInputPort {
    Product execute(String customerCpf);
}
