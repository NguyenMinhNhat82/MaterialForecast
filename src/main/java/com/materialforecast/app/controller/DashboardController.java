package com.materialforecast.app.controller;


import com.materialforecast.app.dto.DashboardForecastVsActualDTO;
import com.materialforecast.app.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/dashboard-r1-r3")
    ResponseEntity<DashboardForecastVsActualDTO> getDataActualVsForecast(){
        return new ResponseEntity<>(dashboardService.getDataDashboardActualVsForecast(), HttpStatus.OK);
    }
}
