package com.digio.challenge.cucumber.datatable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDataTable {
    private String title;
    private Integer status;
    private String detail;
}
