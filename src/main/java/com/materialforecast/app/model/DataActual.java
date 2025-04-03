package com.materialforecast.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class DataActual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer year;
    private Integer month;
    private String rb;
    private String productClass;
    private String productCategory;
    private String itemID;
    private String cusItemId;
    private String location;
    private Double quantity;
    private Double sales;
    private String billToName;
    private String billToID;
    private String solution;
    private String uom;
    private String status;
    private String sm;
    private String se;
    private String itemStatus;
    private String elim;
    private String category;
    private String plt;
}
