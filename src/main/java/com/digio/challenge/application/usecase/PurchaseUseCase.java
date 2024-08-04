package com.digio.challenge.application.usecase;

import com.digio.challenge.application.domain.Purchase;
import com.digio.challenge.application.ports.in.PurchaseInputPort;
import com.digio.challenge.application.ports.out.PurchaseOutputPort;

import java.util.List;

public class PurchaseUseCase implements PurchaseInputPort {

    private final PurchaseOutputPort purchaseOutputPort;

    public PurchaseUseCase(PurchaseOutputPort purchaseOutputPort) {
        this.purchaseOutputPort = purchaseOutputPort;
    }

    @Override
    public List<Purchase> execute() {
        return purchaseOutputPort.getPurchasesOrderedByTotalValueAsc();
    }
}
