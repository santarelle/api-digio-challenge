package com.digio.challenge.adapter.in.web;

import com.digio.challenge.application.domain.Purchase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Purchases")
public interface PurchaseController {

    @Operation(summary = "Retrieve a list of purchases sorted in ascending order by value")
    ResponseEntity<List<Purchase>> getPurchases();
}
