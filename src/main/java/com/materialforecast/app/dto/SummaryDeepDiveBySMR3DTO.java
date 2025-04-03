package com.materialforecast.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryDeepDiveBySMR3DTO {
    private String rb;
    private String plt;
    private String billToCustomer;
    private String se;
    private String sm;
    private Double actual1;
    private Double actual2;
    private Double actual3;
    private Double actual4;
    private Double actual5;
    private Double forecast1;
    private Double forecast2;
    private Double forecast3;
    private Double forecast4;
    private Double forecast5;

}
