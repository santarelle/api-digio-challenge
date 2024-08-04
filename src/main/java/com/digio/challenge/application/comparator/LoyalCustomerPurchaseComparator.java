package com.digio.challenge.application.comparator;

import com.digio.challenge.application.domain.CustomerPurchase;

import java.util.Comparator;

public class LoyalCustomerPurchaseComparator implements Comparator<CustomerPurchase> {

    @Override
    public int compare(CustomerPurchase o1, CustomerPurchase o2) {
        int quantityComparison = Integer.compare(o2.quantity(), o1.quantity());
        if (quantityComparison != 0) {
            return quantityComparison;
        }
        return o2.totalValue().compareTo(o1.totalValue());
    }
}
