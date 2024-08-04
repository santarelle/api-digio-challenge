package com.digio.challenge.application.usecase;

import com.digio.challenge.application.domain.Purchase;
import com.digio.challenge.application.exception.PurchaseNotFoundException;
import com.digio.challenge.application.ports.in.LargestPurchaseInputPort;
import com.digio.challenge.application.ports.out.PurchaseOutputPort;

public class LargestPurchaseYearUseCase implements LargestPurchaseInputPort {

    private final PurchaseOutputPort purchaseOutputPort;

    public LargestPurchaseYearUseCase(PurchaseOutputPort purchaseOutputPort) {
        this.purchaseOutputPort = purchaseOutputPort;
    }

    @Override
    public Purchase execute(Integer year) {
        var allPurchases = purchaseOutputPort.getPurchasesOrderedByTotalValueAsc();
        return allPurchases.stream().filter(purchase -> year.equals(purchase.product().purchaseYear()))
                .min((o1, o2) -> o2.totalValue().compareTo(o1.totalValue()))
                .orElseThrow(() -> new PurchaseNotFoundException("There is no purchase for year %d".formatted(year)));
    }
}
