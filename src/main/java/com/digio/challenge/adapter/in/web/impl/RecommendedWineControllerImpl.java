package com.digio.challenge.adapter.in.web.impl;

import com.digio.challenge.adapter.in.web.RecommendedWineController;
import com.digio.challenge.application.domain.Product;
import com.digio.challenge.application.ports.in.RecommendedWineInputPort;
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
@RequestMapping("/recomendacao")
@RequiredArgsConstructor
public class RecommendedWineControllerImpl implements RecommendedWineController {

    private final RecommendedWineInputPort recommendedWineInputPort;

    @Override
    @GetMapping(value = "/{cliente}/tipo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getRecommendedWine(@PathVariable("cliente") String customerCpf) {
        log.info("Request /recomendacao/{}/tipo", customerCpf);
        Product response = recommendedWineInputPort.execute(customerCpf);
        log.info("Response /recomendacao/{}/tipo - {}", customerCpf, response);
        return ResponseEntity.ok(response);
    }

}
