package com.digio.challenge.adapter.in.web;

import com.digio.challenge.application.domain.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Recommended Wine")
public interface RecommendedWineController {

    @Operation(summary = "Retrieve a recommend wine based on the customer's most purchased wine types", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(schema = @Schema(ref = "ErrorResponse")))
    })
    ResponseEntity<Product> getRecommendedWine(@Parameter(name = "cliente", example = "96718391344") String customerCpf);
}
