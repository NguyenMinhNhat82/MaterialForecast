package com.materialforecast.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "source_forecast")
@NoArgsConstructor
@AllArgsConstructor

public class SourceMaterialForecast {
    @Id
    @Column(length = 750)
    private String id;
    private String elim;
    private String RBOName;
    private String productClass;
    private String itemId;
    private String customerItemID;
    private String billToCustomer;
    private String billToID;
    private String uom;
    private String solutionDetail;
    private String productCategory;
    private String location;
    private String status;
    private String unit;
    private String plt;
    private String pic;
    private String sm;
    private String rawCode;
    private String newItemID;
    private String typeChange;

    private Integer quantityPeriod;
    private Double salesPeriod;
    private Double salesReclassPeriod;
    private Double averageSellingPeriod;
    private Double percentContributionPeriod;

    private Integer quantityLast;
    private Double salesLast;
    private Double salesReclassLast;
    private Double averageSellingLast;
    private Double percentContributionLast;

    private Double FCFinanceSales1;
    private Double FCFinanceSales2;
    private Double FCFinanceSales3;
    private Double FCFinanceSales4;

    private Double FCFinanceQuantity1;
    private Double FCFinanceQuantity2;
    private Double FCFinanceQuantity3;
    private Double FCFinanceQuantity4;

    private Double FCFinanceQuantity1Formula;
    private Double FCFinanceQuantity2Formula;
    private Double FCFinanceQuantity3Formula;
    private Double FCFinanceQuantity4Formula;

    private Double FCFinanceQuantity1Previous;
    private Double FCFinanceQuantity2Previous;
    private Double FCFinanceQuantity3Previous;

    private Double actual1;
    private Double actual2;
    private Double actual3;
    private Double actual4;
    private Double actual5;
    private Double actual6;
    private Double actual7;
    private Double actual8;
    private Double actual9;
    private Double actual10;
    private Double actual11;
    private Double actual12;

    private Double actual1Revenue;
    private Double actual2Revenue;
    private Double actual3Revenue;
    private Double actual4Revenue;
    private Double actual5Revenue;
    private Double actual6Revenue;
    private Double actual7Revenue;
    private Double actual8Revenue;
    private Double actual9Revenue;
    private Double actual10Revenue;
    private Double actual11Revenue;
    private Double actual12Revenue;


    private Double FCFinanceQuantity1SMAdjustment;
    private Double FCFinanceQuantity2SMAdjustment;
    private Double FCFinanceQuantity3SMAdjustment;
    private Double FCFinanceQuantity4SMAdjustment;

    private Double FCFinanceQuantity1Final;
    private Double FCFinanceQuantity2Final;
    private Double FCFinanceQuantity3Final;
    private Double FCFinanceQuantity4Final;

    private Double Round3Revenue1;
    private Double Round3Revenue2;
    private Double Round3Revenue3;
    private Double Round3Revenue4;
    private Boolean isNew;

}
