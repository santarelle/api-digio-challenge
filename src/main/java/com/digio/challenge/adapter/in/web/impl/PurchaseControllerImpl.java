package com.digio.challenge.adapter.in.web.impl;

import com.digio.challenge.adapter.in.web.PurchaseController;
import com.digio.challenge.application.domain.Purchase;
import com.digio.challenge.application.ports.in.PurchaseInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class PurchaseControllerImpl implements PurchaseController {

    private final PurchaseInputPort purchaseInputPort;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Purchase>> getPurchases() {
        log.info("Request /compras");
        List<Purchase> response = purchaseInputPort.execute();
        log.info("Response /compras - {}", response);
        return ResponseEntity.ok(response);
    }
}
