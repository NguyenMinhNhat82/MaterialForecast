package com.materialforecast.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class SummaryFactoryAndRBByPLTDTO {
   private List<FactoryViewOfPLTDTO> factoryViewOfPLTDTO;
   private List<RBViewOfPLTDTO> rbViewOfPLTDTO;
}
