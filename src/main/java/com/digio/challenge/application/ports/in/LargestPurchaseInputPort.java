package com.digio.challenge.application.ports.in;

import com.digio.challenge.application.domain.Purchase;

public interface LargestPurchaseInputPort {
    Purchase execute(Integer year);
}
