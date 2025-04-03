package com.materialforecast.app.dto;

import lombok.Data;

@Data
public class R1vsR3ByPLTDTO {
    private String plt;
    private Double actual;
    private Double qtyR1;
    private Double qtyR3;
}
