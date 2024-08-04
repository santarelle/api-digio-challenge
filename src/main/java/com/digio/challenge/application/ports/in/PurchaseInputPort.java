package com.digio.challenge.application.ports.in;

import com.digio.challenge.application.domain.Purchase;

import java.util.List;

public interface PurchaseInputPort {
    List<Purchase> execute();
}
