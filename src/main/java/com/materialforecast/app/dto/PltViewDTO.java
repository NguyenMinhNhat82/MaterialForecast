package com.materialforecast.app.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PltViewDTO {
    private  String plt;
    private  Double actual1;
    private  Double actual2;
    private  Double actual3;
    private  Double actual4;
    private  Double actual5;
    private Double r3ForecastFirstMonth;
    private Double r3ForecastSecondMonth;
    private Double r3FnFirstMonth;
    private Double r3FnSecondMonth;

}
