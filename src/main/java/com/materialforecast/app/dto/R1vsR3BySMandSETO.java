package com.materialforecast.app.dto;


import lombok.Data;

@Data
public class R1vsR3BySMandSETO {
    private String sm;
    private String se;
    private Double actual;
    private Double qtyR1;
    private Double qtyR3;
}
