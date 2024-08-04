package com.digio.challenge.adapter.out.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @JsonProperty("codigo")
    private String productCode;

    @JsonProperty("quantidade")
    private Integer quantity;
}
