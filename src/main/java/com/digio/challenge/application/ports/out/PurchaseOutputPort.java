package com.digio.challenge.application.ports.out;

import com.digio.challenge.application.domain.Purchase;

import java.util.List;

public interface PurchaseOutputPort {

    List<Purchase> getPurchasesOrderedByTotalValueAsc();
}
