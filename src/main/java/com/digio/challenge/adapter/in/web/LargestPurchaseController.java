package com.digio.challenge.adapter.in.web;

import com.digio.challenge.application.domain.Purchase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Largest Purchase")
public interface LargestPurchaseController {

    @Operation(summary = "Retrieve the largest purchase of the year", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Purchase not found",
                    content = @Content(schema = @Schema(ref = "ErrorResponse")))
    })
    ResponseEntity<Purchase> getLargestPurchaseYear(@Parameter(name = "ano", example = "2020") Integer year);
}
