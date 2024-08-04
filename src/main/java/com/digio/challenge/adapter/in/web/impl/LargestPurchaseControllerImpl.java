package com.digio.challenge.adapter.in.web.impl;

import com.digio.challenge.adapter.in.web.LargestPurchaseController;
import com.digio.challenge.application.domain.Purchase;
import com.digio.challenge.application.ports.in.LargestPurchaseInputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/maior-compra")
@RequiredArgsConstructor
public class LargestPurchaseControllerImpl implements LargestPurchaseController {

    private final LargestPurchaseInputPort largestPurchaseInputPort;

    @Override
    @GetMapping(value = "/{ano}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Purchase> getLargestPurchaseYear(@PathVariable("ano") Integer year) {
        log.info("Request /maior-compra/{}", year);
        Purchase response = largestPurchaseInputPort.execute(year);
        log.info("Response /maior-compra/{} - {}", year, response);
        return ResponseEntity.ok(response);
    }
}
