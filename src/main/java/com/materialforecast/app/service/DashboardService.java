package com.materialforecast.app.service;


import com.materialforecast.app.dto.DashboardForecastVsActualDTO;
import com.materialforecast.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    SourceForecastRepository sourceForecastRepository;

    @Autowired
    SourceMaterialForecastRepository1 sourceForecastRepository1;

    @Autowired
    SourceMaterialForecastRepository2 sourceForecastRepository2;

    @Autowired
    SourceMaterialForecastRepository3 sourceForecastRepository3;

    @Autowired
    SourceMaterialForecastRepository4 sourceForecastRepository4;

    @Autowired
    SourceMaterialForecastRepository5 sourceForecastRepository5;

    @Autowired
    SourceMaterialForecastRepository6 sourceForecastRepository6;

    public DashboardForecastVsActualDTO getDataDashboardActualVsForecast(){
        DashboardForecastVsActualDTO res = new DashboardForecastVsActualDTO();
        List<List<Double>> listActual = sourceForecastRepository.getDataActualForecast();
        res.setActual1(listActual.get(0).get(0));
        res.setActual2(listActual.get(0).get(1));
        res.setActual3(listActual.get(0).get(2));
        res.setActual4(listActual.get(0).get(3));
        res.setActual5(listActual.get(0).get(4));
        List<List<Double>> dataHistorical1 = sourceForecastRepository2.getDataForecastForecast();
        List<List<Double>> dataHistorical2 = sourceForecastRepository3.getDataForecastForecast();
        List<List<Double>> dataHistorical3 = sourceForecastRepository4.getDataForecastForecast();
        List<List<Double>> dataHistorical4 = sourceForecastRepository5.getDataForecastForecast();
        List<List<Double>> dataHistorical5 = sourceForecastRepository6.getDataForecastForecast();
        res.setForecast5R1(dataHistorical1.get(0).get(0));
        res.setForecast4R1(dataHistorical2.get(0).get(0));
        res.setForecast3R1(dataHistorical3.get(0).get(0));
        res.setForecast2R1(dataHistorical4.get(0).get(0));
        res.setForecast1R1(dataHistorical5.get(0).get(0));

        res.setForecast5R3(dataHistorical1.get(0).get(1));
        res.setForecast4R3(dataHistorical2.get(0).get(1));
        res.setForecast3R3(dataHistorical3.get(0).get(1));
        res.setForecast2R3(dataHistorical4.get(0).get(1));
        res.setForecast1R3(dataHistorical5.get(0).get(1));
        return  res;


    }
}
