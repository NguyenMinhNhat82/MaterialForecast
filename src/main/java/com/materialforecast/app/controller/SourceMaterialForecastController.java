package com.materialforecast.app.controller;


import com.materialforecast.app.dto.*;
import com.materialforecast.app.service.SourceForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class SourceMaterialForecastController {

    @Autowired
    SourceForecastService sourceForecastService;
    @GetMapping("/test-fcst")
    String test(){
        return "test";
    }

    @GetMapping("/get-summary-by-plt")
    ResponseEntity<List<PltViewDTO>> getListSummaryByPLT(){
        return new ResponseEntity<>(sourceForecastService.getDataSummaryByPLT(), HttpStatus.OK);
    }

    @PostMapping("/get-data-detail-by-plt")
    ResponseEntity<SummaryFactoryAndRBByPLTDTO> getListDetailSummaryOFPlt(@RequestBody Map<String, Object> req) {

        // Extract the "body" field from the request
        Object body = req.get("body");

        // Safe casting to Map if the body is expected to be a Map
        if (body instanceof Map) {
            Map<String, List<String>> bodyMap = (Map<String, List<String>>) body;
            SummaryFactoryAndRBByPLTDTO  res= sourceForecastService.summaryFactoryAndRBByPLTDTO(bodyMap.get("pltList"));
            // Now, you can pass the bodyMap to your service or handle it as needed
            return new ResponseEntity<>(res,HttpStatus.OK);
        } else {
            // Handle the case where "body" is not a Map
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Or any appropriate error response
        }
    }

    @GetMapping("/get-data-forecast-sm-se-r3")
    ResponseEntity<List<SummaryBySMAndSEDTO>> getListSummaryBySEAndSMR3(){
        return new ResponseEntity<>(sourceForecastService.summaryBySMSER3(), HttpStatus.OK);
    }


    @GetMapping("/get-data-forecast-sm-se-r3-revenue")
    ResponseEntity<List<SummaryBySMAndSEDTO>> getListSummaryBySEAndSMR3Revenue(){
        return new ResponseEntity<>(sourceForecastService.summaryBySMSER3Revenue(), HttpStatus.OK);
    }

    @GetMapping("/get-data-forecast-plt-r3")
    ResponseEntity<List<SummaryByPLTDTO>> getListSummaryByPLTR3(){
        return new ResponseEntity<>(sourceForecastService.summaryByPLTR3(), HttpStatus.OK);
    }

    @GetMapping("/get-data-forecast-plt-r3-revenue")
    ResponseEntity<List<SummaryByPLTDTO>> getListSummaryByPLTR3Revenue(){
        return new ResponseEntity<>(sourceForecastService.summaryByPLTR3Revenue(), HttpStatus.OK);
    }


    @GetMapping("/compare-r1-r3-sm-se")
    ResponseEntity<List<R1vsR3BySMandSETO>> compareDataR1AndR3BySMSE(){
        return new ResponseEntity<>(sourceForecastService.compareDataR1AndR3BySMSE(), HttpStatus.OK);
    }

    @GetMapping("/compare-r1-r3-sm-se-revenue")
    ResponseEntity<List<R1vsR3BySMandSETO>> compareDataR1AndR3BySMSEByRevenue(){
        return new ResponseEntity<>(sourceForecastService.compareDataR1AndR3BySMSEByRevenue(), HttpStatus.OK);
    }

    @GetMapping("/compare-r1-r3-plt")
    ResponseEntity<List<R1vsR3ByPLTDTO>> compareDataR1AndR3ByPLT(){
        return new ResponseEntity<>(sourceForecastService.compareDataR1AndR3ByPLT(), HttpStatus.OK);
    }

    @GetMapping("/compare-r1-r3-plt-revenue")
    ResponseEntity<List<R1vsR3ByPLTDTO>> compareDataR1AndR3ByPLTRevenue(){
        return new ResponseEntity<>(sourceForecastService.compareDataR1AndR3ByPLTRevenue(), HttpStatus.OK);
    }

    @GetMapping("/get-data-forecast-plt-r1")
    ResponseEntity<List<SummaryByPLTDTO>> getListSummaryByPLTR1(){
        return new ResponseEntity<>(sourceForecastService.summaryByPLTR1(), HttpStatus.OK);
    }

    @GetMapping("/get-data-forecast-plt-r1-revenue")
    ResponseEntity<List<SummaryByPLTDTO>> getListSummaryByPLTR1Revenue(){
        return new ResponseEntity<>(sourceForecastService.summaryByPLTR1Revenue(), HttpStatus.OK);
    }

    @GetMapping("/get-data-forecast-sm-se-r1")
    ResponseEntity<List<SummaryBySMAndSEDTO>> getListSummaryBySEAndSMR1(){
        return new ResponseEntity<>(sourceForecastService.summaryBySMSER1(), HttpStatus.OK);
    }

    @GetMapping("/get-data-forecast-sm-se-r1-revenue")
    ResponseEntity<List<SummaryBySMAndSEDTO>> getListSummaryBySEAndSMR1Revenue(){
        return new ResponseEntity<>(sourceForecastService.summaryBySMSER1Revenue(), HttpStatus.OK);
    }

    @GetMapping("/summary-deep-dive")
    public ResponseEntity<List<SummaryDeepDiveBySMR3DTO>> getForecastSummary(
            @RequestParam(required = false) Boolean isViewByRB,
            @RequestParam(required = false) Boolean isViewByPLT,
            @RequestParam(required = false) Boolean isViewByBillToName,
            @RequestParam(required = false) String pic,
            @RequestParam(required = false) String sm
    ) {
        return new ResponseEntity<>(sourceForecastService.getForecastSummary(
                isViewByRB != null && isViewByRB,
                isViewByPLT != null && isViewByPLT,
                isViewByBillToName != null && isViewByBillToName,
                pic,
                sm
        ),HttpStatus.OK);
    }


    @GetMapping("/summary-deep-dive-plt")
    public ResponseEntity<List<SummaryDeepDiveByPLTR3DTO>> getForecastSummaryByPLT(
            @RequestParam(required = false) Boolean isViewByRB,
            @RequestParam(required = false) Boolean isViewBySE,
            @RequestParam(required = false) Boolean isViewByBillToName,
            @RequestParam(required = false) String plt

    ) {
        List<SummaryDeepDiveByPLTR3DTO> res =  sourceForecastService.getForecastSummaryByPLT(
                isViewByRB != null && isViewByRB,
                isViewBySE != null && isViewBySE,
                isViewByBillToName != null && isViewByBillToName,
                plt

        );
        return new ResponseEntity<>(
                res,HttpStatus.OK);
    }

    @GetMapping("/summary-deep-dive-r1")
    public ResponseEntity<List<SummaryDeepDiveBySMR3DTO>> getForecastSummaryR1(
            @RequestParam(required = false) Boolean isViewByRB,
            @RequestParam(required = false) Boolean isViewByPLT,
            @RequestParam(required = false) Boolean isViewByBillToName,
            @RequestParam(required = false) String pic,
            @RequestParam(required = false) String sm
    ) {
        List<SummaryDeepDiveBySMR3DTO> res =  sourceForecastService.getForecastSummaryR1(
                isViewByRB != null && isViewByRB,
                isViewByPLT != null && isViewByPLT,
                isViewByBillToName != null && isViewByBillToName,
                pic,
                sm
        );

        return new ResponseEntity<>(res,HttpStatus.OK);
    }


    @GetMapping("/summary-deep-dive-r1-plt")
    public ResponseEntity<List<SummaryDeepDiveByPLTR3DTO>> getForecastSummaryR1(
            @RequestParam(required = false) Boolean isViewByRB,
            @RequestParam(required = false) Boolean isViewBySE,
            @RequestParam(required = false) Boolean isViewByBillToName,
            @RequestParam(required = false) String plt

    ) {
        return new ResponseEntity<>(sourceForecastService.getForecastSummaryPlTR1(
                isViewByRB != null && isViewByRB,
                isViewBySE != null && isViewBySE,
                isViewByBillToName != null && isViewByBillToName,
                plt

        ),HttpStatus.OK);
    }



}
