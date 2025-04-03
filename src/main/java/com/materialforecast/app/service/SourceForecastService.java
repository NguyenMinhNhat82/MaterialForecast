package com.materialforecast.app.service;


import com.materialforecast.app.dto.*;
import com.materialforecast.app.mapper.PltViewMapper;
import com.materialforecast.app.repository.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SourceForecastService {

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



    @Autowired
    PltViewMapper pltViewMapper;

    @Autowired
    DataActualRepository dataActualRepository;



    public List<PltViewDTO> getDataSummaryByPLT(){
        List<Object[]> listDataFcst =  sourceForecastRepository.getDataSummaryByPLT();
        return pltViewMapper.toPLTDTOView(listDataFcst);
    }


    public List<SummaryByPLTDTO> summaryByPLTR3() {
        List<Object[]> currentFileForecastSmSE = sourceForecastRepository.getDataActualSummaryByPLT();
        List<SummaryByPLTDTO> res = new ArrayList<>();

        // Use a HashSet for faster lookups

        List<String> plts = currentFileForecastSmSE.stream()
                .map(data -> data[0].toString()).distinct().collect(Collectors.toList());
        // Store all forecasts in a single map (Key: SE, Value: Double[5])
        Map<String, Double[]> forecastMap = new HashMap<>();

        // Fetch forecasts for all five months
        List<List<Object[]>> forecastDataLists = List.of(
                sourceForecastRepository2.getForecastMonth1R3ByPLT(plts),
                sourceForecastRepository3.getForecastMonth1R3ByPLT(plts),
                sourceForecastRepository4.getForecastMonth1R3ByPLT(plts),
                sourceForecastRepository5.getForecastMonth1R3ByPLT(plts),
                sourceForecastRepository6.getForecastMonth1R3ByPLT(plts)
        );

        // Populate the forecastMap in one pass
        for (int i = 0; i < forecastDataLists.size(); i++) {
            for (Object[] row : forecastDataLists.get(i)) {
                if (row.length > 0) {
                    String key = row[0].toString();
                    double value = parseDouble(row[1]);
                    forecastMap.computeIfAbsent(key, k -> new Double[]{0.0, 0.0, 0.0, 0.0, 0.0})[i] = value;
                }
            }
        }

        // Populate result list
        for (Object[] data : currentFileForecastSmSE) {
            SummaryByPLTDTO obj = new SummaryByPLTDTO();
            String plt = data[0].toString();


            obj.setPlt(plt);
            obj.setActual1(parseDouble(data[1]));
            obj.setActual2(parseDouble(data[2]));
            obj.setActual3(parseDouble(data[3]));
            obj.setActual4(parseDouble(data[4]));
            obj.setActual5(parseDouble(data[5]));

            // Fetch forecast values from the map
            Double[] forecasts = forecastMap.getOrDefault(plt, new Double[]{0.0, 0.0, 0.0, 0.0, 0.0});
            obj.setForecast1(forecasts[0]);
            obj.setForecast2(forecasts[1]);
            obj.setForecast3(forecasts[2]);
            obj.setForecast4(forecasts[3]);
            obj.setForecast5(forecasts[4]);

            res.add(obj);
        }
        return res;
    }


    public List<SummaryByPLTDTO> summaryByPLTR3Revenue() {
        List<Object[]> currentFileForecastSmSE = sourceForecastRepository.getDataActualSummaryByPLTRevenue();
        List<SummaryByPLTDTO> res = new ArrayList<>();

        // Use a HashSet for faster lookups

        List<String> plts = currentFileForecastSmSE.stream()
                .map(data -> data[0].toString()).distinct().collect(Collectors.toList());
        // Store all forecasts in a single map (Key: SE, Value: Double[5])
        Map<String, Double[]> forecastMap = new HashMap<>();

        // Fetch forecasts for all five months
        List<List<Object[]>> forecastDataLists = List.of(
                sourceForecastRepository2.getForecastMonth1R3ByPLTRevenue(plts),
                sourceForecastRepository3.getForecastMonth1R3ByPLTRevenue(plts),
                sourceForecastRepository4.getForecastMonth1R3ByPLTRevenue(plts),
                sourceForecastRepository5.getForecastMonth1R3ByPLTRevenue(plts),
                sourceForecastRepository6.getForecastMonth1R3ByPLTRevenue(plts)
        );

        // Populate the forecastMap in one pass
        for (int i = 0; i < forecastDataLists.size(); i++) {
            for (Object[] row : forecastDataLists.get(i)) {
                if (row.length > 0) {
                    String key = row[0].toString();
                    double value = parseDouble(row[1]);
                    forecastMap.computeIfAbsent(key, k -> new Double[]{0.0, 0.0, 0.0, 0.0, 0.0})[i] = value;
                }
            }
        }

        // Populate result list
        for (Object[] data : currentFileForecastSmSE) {
            SummaryByPLTDTO obj = new SummaryByPLTDTO();
            String plt = data[0].toString();


            obj.setPlt(plt);
            obj.setActual1(parseDouble(data[1]));
            obj.setActual2(parseDouble(data[2]));
            obj.setActual3(parseDouble(data[3]));
            obj.setActual4(parseDouble(data[4]));
            obj.setActual5(parseDouble(data[5]));

            // Fetch forecast values from the map
            Double[] forecasts = forecastMap.getOrDefault(plt, new Double[]{0.0, 0.0, 0.0, 0.0, 0.0});
            obj.setForecast1(forecasts[0]);
            obj.setForecast2(forecasts[1]);
            obj.setForecast3(forecasts[2]);
            obj.setForecast4(forecasts[3]);
            obj.setForecast5(forecasts[4]);

            res.add(obj);
        }
        return res;
    }

    public List<SummaryByPLTDTO> summaryByPLTR1() {
        List<Object[]> currentFileForecastSmSE = sourceForecastRepository.getDataActualSummaryByPLT();
        List<SummaryByPLTDTO> res = new ArrayList<>();

        // Use a HashSet for faster lookups

        List<String> plts = currentFileForecastSmSE.stream()
                .map(data -> data[0].toString()).distinct().collect(Collectors.toList());
        // Store all forecasts in a single map (Key: SE, Value: Double[5])
        Map<String, Double[]> forecastMap = new HashMap<>();

        // Fetch forecasts for all five months
        List<List<Object[]>> forecastDataLists = List.of(
                sourceForecastRepository2.getForecastMonth1R1ByPLT(plts),
                sourceForecastRepository3.getForecastMonth1R1ByPLT(plts),
                sourceForecastRepository4.getForecastMonth1R1ByPLT(plts),
                sourceForecastRepository5.getForecastMonth1R1ByPLT(plts),
                sourceForecastRepository6.getForecastMonth1R1ByPLT(plts)
        );

        // Populate the forecastMap in one pass
        for (int i = 0; i < forecastDataLists.size(); i++) {
            for (Object[] row : forecastDataLists.get(i)) {
                if (row.length > 0) {
                    String key = row[0].toString();
                    double value = parseDouble(row[1]);
                    forecastMap.computeIfAbsent(key, k -> new Double[]{0.0, 0.0, 0.0, 0.0, 0.0})[i] = value;
                }
            }
        }

        // Populate result list
        for (Object[] data : currentFileForecastSmSE) {
            SummaryByPLTDTO obj = new SummaryByPLTDTO();
            String plt = data[0].toString();


            obj.setPlt(plt);
            obj.setActual1(parseDouble(data[1]));
            obj.setActual2(parseDouble(data[2]));
            obj.setActual3(parseDouble(data[3]));
            obj.setActual4(parseDouble(data[4]));
            obj.setActual5(parseDouble(data[5]));

            // Fetch forecast values from the map
            Double[] forecasts = forecastMap.getOrDefault(plt, new Double[]{0.0, 0.0, 0.0, 0.0, 0.0});
            obj.setForecast1(forecasts[0]);
            obj.setForecast2(forecasts[1]);
            obj.setForecast3(forecasts[2]);
            obj.setForecast4(forecasts[3]);
            obj.setForecast5(forecasts[4]);

            res.add(obj);
        }
        return res;
    }


    public List<SummaryByPLTDTO> summaryByPLTR1Revenue() {
        List<Object[]> currentFileForecastSmSE = sourceForecastRepository.getDataActualSummaryByPLTRevenue();
        List<SummaryByPLTDTO> res = new ArrayList<>();

        // Use a HashSet for faster lookups

        List<String> plts = currentFileForecastSmSE.stream()
                .map(data -> data[0].toString()).distinct().collect(Collectors.toList());
        // Store all forecasts in a single map (Key: SE, Value: Double[5])
        Map<String, Double[]> forecastMap = new HashMap<>();

        // Fetch forecasts for all five months
        List<List<Object[]>> forecastDataLists = List.of(
                sourceForecastRepository2.getForecastMonth1R1ByPLTRevenue(plts),
                sourceForecastRepository3.getForecastMonth1R1ByPLTRevenue(plts),
                sourceForecastRepository4.getForecastMonth1R1ByPLTRevenue(plts),
                sourceForecastRepository5.getForecastMonth1R1ByPLTRevenue(plts),
                sourceForecastRepository6.getForecastMonth1R1ByPLTRevenue(plts)
        );

        // Populate the forecastMap in one pass
        for (int i = 0; i < forecastDataLists.size(); i++) {
            for (Object[] row : forecastDataLists.get(i)) {
                if (row.length > 0) {
                    String key = row[0].toString();
                    double value = parseDouble(row[1]);
                    forecastMap.computeIfAbsent(key, k -> new Double[]{0.0, 0.0, 0.0, 0.0, 0.0})[i] = value;
                }
            }
        }

        // Populate result list
        for (Object[] data : currentFileForecastSmSE) {
            SummaryByPLTDTO obj = new SummaryByPLTDTO();
            String plt = data[0].toString();


            obj.setPlt(plt);
            obj.setActual1(parseDouble(data[1]));
            obj.setActual2(parseDouble(data[2]));
            obj.setActual3(parseDouble(data[3]));
            obj.setActual4(parseDouble(data[4]));
            obj.setActual5(parseDouble(data[5]));

            // Fetch forecast values from the map
            Double[] forecasts = forecastMap.getOrDefault(plt, new Double[]{0.0, 0.0, 0.0, 0.0, 0.0});
            obj.setForecast1(forecasts[0]);
            obj.setForecast2(forecasts[1]);
            obj.setForecast3(forecasts[2]);
            obj.setForecast4(forecasts[3]);
            obj.setForecast5(forecasts[4]);

            res.add(obj);
        }
        return res;
    }




    public List<SummaryBySMAndSEDTO> summaryBySMSER3() {
        List<Object[]> currentFileForecastSmSE = sourceForecastRepository.getDataActualSummaryBySMSE();
        List<Object[]> currentFileForecastSmSEOpen = sourceForecastRepository.getDataActualSummaryBySMSEOpen();
        List<SummaryBySMAndSEDTO> res = new ArrayList<>();

        // Use a HashSet for faster lookups

        List<String> pics = currentFileForecastSmSE.stream()
                .map(data -> data[1].toString()).distinct().collect(Collectors.toList());
        // Store all forecasts in a single map (Key: SE, Value: Double[5])
        Map<String, Double[]> forecastMap = new HashMap<>();
        pics.remove("Open");
        // Fetch forecasts for all five months
        List<List<Object[]>> forecastDataLists = List.of(
                sourceForecastRepository2.getForecastMonth1R3BySE(pics),
                sourceForecastRepository3.getForecastMonth1R3BySE(pics),
                sourceForecastRepository4.getForecastMonth1R3BySE(pics),
                sourceForecastRepository5.getForecastMonth1R3BySE(pics),
                sourceForecastRepository6.getForecastMonth1R3BySE(pics)
        );

        // Populate the forecastMap in one pass
        for (int i = 0; i < forecastDataLists.size(); i++) {
            for (Object[] row : forecastDataLists.get(i)) {
                if (row.length > 0) {
                    String key = row[0].toString();
                    double value = parseDouble(row[1]);
                    forecastMap.computeIfAbsent(key, k -> new Double[]{0.0, 0.0, 0.0, 0.0, 0.0})[i] = value;
                }
            }
        }

        // Populate result list
        for (Object[] data : currentFileForecastSmSE) {
            SummaryBySMAndSEDTO obj = new SummaryBySMAndSEDTO();
            String sm = data[0].toString();
            String pic = data[1].toString();

            obj.setSm(sm);
            obj.setSe(pic);

            if(sm.trim().equals("Open")){
                obj.setActual1(parseDouble(currentFileForecastSmSEOpen.get(0)[2]));
                obj.setActual2(parseDouble(currentFileForecastSmSEOpen.get(0)[3]));
                obj.setActual3(parseDouble(currentFileForecastSmSEOpen.get(0)[4]));
                obj.setActual4(parseDouble(currentFileForecastSmSEOpen.get(0)[5]));
                obj.setActual5(parseDouble(currentFileForecastSmSEOpen.get(0)[6]));
                obj.setForecast1(Double.parseDouble(sourceForecastRepository2.getForecastMonth1R3BySEOpen().get(0)[1].toString()));
                obj.setForecast2(Double.parseDouble(sourceForecastRepository3.getForecastMonth1R3BySEOpen().get(0)[1].toString()));
                obj.setForecast3(Double.parseDouble(sourceForecastRepository4.getForecastMonth1R3BySEOpen().get(0)[1].toString()));
                obj.setForecast4(Double.parseDouble(sourceForecastRepository5.getForecastMonth1R3BySEOpen().get(0)[1].toString()));
                obj.setForecast5(Double.parseDouble(sourceForecastRepository6.getForecastMonth1R3BySEOpen().get(0)[1].toString()));
            }
            else {
                Double[] forecasts = forecastMap.getOrDefault(pic, new Double[]{0.0, 0.0, 0.0, 0.0, 0.0});
                obj.setActual1(parseDouble(data[2]));
                obj.setActual2(parseDouble(data[3]));
                obj.setActual3(parseDouble(data[4]));
                obj.setActual4(parseDouble(data[5]));
                obj.setActual5(parseDouble(data[6]));
                obj.setForecast1(forecasts[0]);
                obj.setForecast2(forecasts[1]);
                obj.setForecast3(forecasts[2]);
                obj.setForecast4(forecasts[3]);
                obj.setForecast5(forecasts[4]);
            }
            // Fetch forecast values from the map


            res.add(obj);
        }
        return res;
    }

    public List<SummaryBySMAndSEDTO> summaryBySMSER3Revenue() {
        List<Object[]> currentFileForecastSmSE = sourceForecastRepository.getDataActualRevenueSummaryBySMSE();
        List<Object[]> currentFileForecastSmSEOpen = sourceForecastRepository.getDataActualRevenueSummaryBySMSEOpen();
        List<SummaryBySMAndSEDTO> res = new ArrayList<>();

        // Use a HashSet for faster lookups

        List<String> pics = currentFileForecastSmSE.stream()
                .map(data -> data[1].toString()).distinct().collect(Collectors.toList());
        // Store all forecasts in a single map (Key: SE, Value: Double[5])
        Map<String, Double[]> forecastMap = new HashMap<>();
        pics.remove("Open");
        // Fetch forecasts for all five months
        List<List<Object[]>> forecastDataLists = List.of(
                sourceForecastRepository2.getForecastMonth1R3BySERevenue(pics),
                sourceForecastRepository3.getForecastMonth1R3BySERevenue(pics),
                sourceForecastRepository4.getForecastMonth1R3BySERevenue(pics),
                sourceForecastRepository5.getForecastMonth1R3BySERevenue(pics),
                sourceForecastRepository6.getForecastMonth1R3BySERevenue(pics)
        );

        // Populate the forecastMap in one pass
        for (int i = 0; i < forecastDataLists.size(); i++) {
            for (Object[] row : forecastDataLists.get(i)) {
                if (row.length > 0) {
                    String key = row[0].toString();
                    double value = parseDouble(row[1]);
                    forecastMap.computeIfAbsent(key, k -> new Double[]{0.0, 0.0, 0.0, 0.0, 0.0})[i] = value;
                }
            }
        }

        // Populate result list
        for (Object[] data : currentFileForecastSmSE) {
            SummaryBySMAndSEDTO obj = new SummaryBySMAndSEDTO();
            String sm = data[0].toString();
            String pic = data[1].toString();

            obj.setSm(sm);
            obj.setSe(pic);

            if(sm.trim().equals("Open")){
                obj.setForecast1(Double.parseDouble(sourceForecastRepository2.getForecastMonth1R3BySEOpenRevenue().get(0)[1].toString()));
                obj.setForecast2(Double.parseDouble(sourceForecastRepository3.getForecastMonth1R3BySEOpenRevenue().get(0)[1].toString()));
                obj.setForecast3(Double.parseDouble(sourceForecastRepository4.getForecastMonth1R3BySEOpenRevenue().get(0)[1].toString()));
                obj.setForecast4(Double.parseDouble(sourceForecastRepository5.getForecastMonth1R3BySEOpenRevenue().get(0)[1].toString()));
                obj.setForecast5(Double.parseDouble(sourceForecastRepository6.getForecastMonth1R3BySEOpenRevenue().get(0)[1].toString()));
                obj.setActual1(parseDouble(currentFileForecastSmSEOpen.get(0)[2]));
                obj.setActual2(parseDouble(currentFileForecastSmSEOpen.get(0)[3]));
                obj.setActual3(parseDouble(currentFileForecastSmSEOpen.get(0)[4]));
                obj.setActual4(parseDouble(currentFileForecastSmSEOpen.get(0)[5]));
                obj.setActual5(parseDouble(currentFileForecastSmSEOpen.get(0)[6]));
            }
            else {
                Double[] forecasts = forecastMap.getOrDefault(pic, new Double[]{0.0, 0.0, 0.0, 0.0, 0.0});
                obj.setForecast1(forecasts[0]);
                obj.setForecast2(forecasts[1]);
                obj.setForecast3(forecasts[2]);
                obj.setForecast4(forecasts[3]);
                obj.setForecast5(forecasts[4]);
                obj.setActual1(parseDouble(data[2]));
                obj.setActual2(parseDouble(data[3]));
                obj.setActual3(parseDouble(data[4]));
                obj.setActual4(parseDouble(data[5]));
                obj.setActual5(parseDouble(data[6]));
            }
            // Fetch forecast values from the map


            res.add(obj);
        }
        return res;
    }

    public List<R1vsR3BySMandSETO> compareDataR1AndR3BySMSE() {
        List<Object[]> currentFileForecastSmSE = sourceForecastRepository.compareR1vsR3BySMSE();
        List<R1vsR3BySMandSETO> res = new ArrayList<>();

        // Use a HashSet for faster lookups

        List<String> pics = currentFileForecastSmSE.stream()
                .map(data -> data[1].toString()).distinct().collect(Collectors.toList());
        pics.remove("Open");
        // Store all forecasts in a single map (Key: SE, Value: Double[5])
        Map<String, Double[]> forecastMap = new HashMap<>();

        // Fetch forecasts for all five months
        List<Object[]> forecastDataList = sourceForecastRepository2.compareR1vsR3BySMSE(pics);

        // Populate the forecastMap with data from the list
        for (Object[] row : forecastDataList) {
            if (row.length >= 3) {
                String pic = row[0].toString();
                double quantity1 = parseDouble(row[1]);
                double quantity1Final = parseDouble(row[2]);

                // Store the values in a Double array and put them in the map
                forecastMap.put(pic, new Double[]{quantity1, quantity1Final});
            }
        }

        // Populate result list
        for (Object[] data : currentFileForecastSmSE) {
            R1vsR3BySMandSETO obj = new R1vsR3BySMandSETO();
            String sm = data[0].toString();
            String pic = data[1].toString();

            obj.setSm(sm);
            obj.setSe(pic);
            obj.setActual(parseDouble(data[2]));
            if(sm.trim().equals("Open")){
                obj.setQtyR1(Double.parseDouble(sourceForecastRepository2.compareR1vsR3BySMSEOpen().get(0)[1].toString()));
                obj.setQtyR3(Double.parseDouble(sourceForecastRepository2.compareR1vsR3BySMSEOpen().get(0)[2].toString()));
            }
            else {
                // Fetch forecast values from the map
                Double[] forecasts = forecastMap.getOrDefault(pic, new Double[]{0.0, 0.0});
                obj.setQtyR1(forecasts[0]);
                obj.setQtyR3(forecasts[1]);
            }





            res.add(obj);
        }
        return res;
    }
    public List<R1vsR3BySMandSETO> compareDataR1AndR3BySMSEByRevenue() {
        List<Object[]> currentFileForecastSmSE = sourceForecastRepository.compareR1vsR3BySMSERevenue();
        List<R1vsR3BySMandSETO> res = new ArrayList<>();

        // Use a HashSet for faster lookups

        List<String> pics = currentFileForecastSmSE.stream()
                .map(data -> data[1].toString()).distinct().collect(Collectors.toList());
        pics.remove("Open");
        // Store all forecasts in a single map (Key: SE, Value: Double[5])
        Map<String, Double[]> forecastMap = new HashMap<>();

        // Fetch forecasts for all five months
        List<Object[]> forecastDataList = sourceForecastRepository2.compareR1vsR3BySMSEByRevenue(pics);

        // Populate the forecastMap with data from the list
        for (Object[] row : forecastDataList) {
            if (row.length >= 3) {
                String pic = row[0].toString();
                double quantity1 = parseDouble(row[1]);
                double quantity1Final = parseDouble(row[2]);

                // Store the values in a Double array and put them in the map
                forecastMap.put(pic, new Double[]{quantity1, quantity1Final});
            }
        }

        // Populate result list
        for (Object[] data : currentFileForecastSmSE) {
            R1vsR3BySMandSETO obj = new R1vsR3BySMandSETO();
            String sm = data[0].toString();
            String pic = data[1].toString();

            obj.setSm(sm);
            obj.setSe(pic);
            obj.setActual(parseDouble(data[2]));
            if(sm.trim().equals("Open")){
                List<Object[]> dataOpen = sourceForecastRepository2.compareR1vsR3BySMSEOpenByRevenue();
                obj.setQtyR1(Double.parseDouble(dataOpen.get(0)[1].toString()));
                obj.setQtyR3(Double.parseDouble(dataOpen.get(0)[2].toString()));
            }
            else {
                // Fetch forecast values from the map
                Double[] forecasts = forecastMap.getOrDefault(pic, new Double[]{0.0, 0.0});
                obj.setQtyR1(forecasts[0]);
                obj.setQtyR3(forecasts[1]);
            }





            res.add(obj);
        }
        return res;
    }



    public List<R1vsR3ByPLTDTO> compareDataR1AndR3ByPLT() {
        List<Object[]> currentFileForecastSmSE = sourceForecastRepository.compareR1vsR3ByPLT();
        List<R1vsR3ByPLTDTO> res = new ArrayList<>();

        // Use a HashSet for faster lookups

        List<String> plts = currentFileForecastSmSE.stream()
                .map(data -> data[0].toString()).distinct().collect(Collectors.toList());
        // Store all forecasts in a single map (Key: SE, Value: Double[5])
        Map<String, Double[]> forecastMap = new HashMap<>();

        // Fetch forecasts for all five months
        List<Object[]> forecastDataList = sourceForecastRepository2.compareR1vsR3ByPLT(plts);

        // Populate the forecastMap with data from the list
        for (Object[] row : forecastDataList) {
            if (row.length >= 3) {
                String plt = row[0].toString();
                double quantity1 = parseDouble(row[1]);
                double quantity1Final = parseDouble(row[2]);

                // Store the values in a Double array and put them in the map
                forecastMap.put(plt, new Double[]{quantity1, quantity1Final});
            }
        }

        // Populate result list
        for (Object[] data : currentFileForecastSmSE) {
            R1vsR3ByPLTDTO obj = new R1vsR3ByPLTDTO();
            String plt = data[0].toString();

            obj.setPlt(plt);
            obj.setActual(parseDouble(data[1]));


            // Fetch forecast values from the map
            Double[] forecasts = forecastMap.getOrDefault(plt, new Double[]{0.0, 0.0});
            obj.setQtyR1(forecasts[0]);
            obj.setQtyR3(forecasts[1]);


            res.add(obj);
        }
        return res;
    }

    public List<R1vsR3ByPLTDTO> compareDataR1AndR3ByPLTRevenue() {
        List<Object[]> currentFileForecastSmSE = sourceForecastRepository.compareR1vsR3ByPLTRevenue();
        List<R1vsR3ByPLTDTO> res = new ArrayList<>();

        // Use a HashSet for faster lookups

        List<String> plts = currentFileForecastSmSE.stream()
                .map(data -> data[0].toString()).distinct().collect(Collectors.toList());
        // Store all forecasts in a single map (Key: SE, Value: Double[5])
        Map<String, Double[]> forecastMap = new HashMap<>();

        // Fetch forecasts for all five months
        List<Object[]> forecastDataList = sourceForecastRepository2.compareR1vsR3ByPLTRevenue(plts);

        // Populate the forecastMap with data from the list
        for (Object[] row : forecastDataList) {
            if (row.length >= 3) {
                String plt = row[0].toString();
                double quantity1 = parseDouble(row[1]);
                double quantity1Final = parseDouble(row[2]);

                // Store the values in a Double array and put them in the map
                forecastMap.put(plt, new Double[]{quantity1, quantity1Final});
            }
        }

        // Populate result list
        for (Object[] data : currentFileForecastSmSE) {
            R1vsR3ByPLTDTO obj = new R1vsR3ByPLTDTO();
            String plt = data[0].toString();

            obj.setPlt(plt);
            obj.setActual(parseDouble(data[1]));


            // Fetch forecast values from the map
            Double[] forecasts = forecastMap.getOrDefault(plt, new Double[]{0.0, 0.0});
            obj.setQtyR1(forecasts[0]);
            obj.setQtyR3(forecasts[1]);


            res.add(obj);
        }
        return res;
    }

    // Helper method to parse Double safely
    private double parseDouble(Object value) {
        return (value == null) ? 0.0 : Double.parseDouble(value.toString());
    }

    public List<SummaryBySMAndSEDTO> summaryBySMSER1() {
        List<Object[]> currentFileForecastSmSE = sourceForecastRepository.getDataActualSummaryBySMSE();
        List<Object[]> currentFileForecastSmSEOpen = sourceForecastRepository.getDataActualSummaryBySMSEOpen();
        List<SummaryBySMAndSEDTO> res = new ArrayList<>();

        // Use a HashSet for faster lookups

        List<String> pics = currentFileForecastSmSE.stream()
                .map(data -> data[1].toString()).distinct().collect(Collectors.toList());
        // Store all forecasts in a single map (Key: SE, Value: Double[5])
        Map<String, Double[]> forecastMap = new HashMap<>();
        pics.remove("Open");

        // Fetch forecasts for all five months
        List<List<Object[]>> forecastDataLists = List.of(
                sourceForecastRepository2.getForecastMonth1R1BySE(pics),
                sourceForecastRepository3.getForecastMonth1R1BySE(pics),
                sourceForecastRepository4.getForecastMonth1R1BySE(pics),
                sourceForecastRepository5.getForecastMonth1R1BySE(pics),
                sourceForecastRepository6.getForecastMonth1R1BySE(pics)
        );



        // Populate the forecastMap in one pass
        for (int i = 0; i < forecastDataLists.size(); i++) {
            for (Object[] row : forecastDataLists.get(i)) {
                if (row.length > 0) {
                    String key = row[0].toString();
                    double value = parseDouble(row[1]);
                    forecastMap.computeIfAbsent(key, k -> new Double[]{0.0, 0.0, 0.0, 0.0, 0.0})[i] = value;
                }
            }
        }



        // Populate result list
        for (Object[] data : currentFileForecastSmSE) {
            SummaryBySMAndSEDTO obj = new SummaryBySMAndSEDTO();
            String sm = data[0].toString();

            String pic = data[1].toString();

            obj.setSm(sm);
            obj.setSe(pic);

            if(sm.trim().equals("Open")){
                obj.setForecast1(Double.parseDouble(sourceForecastRepository2.getForecastMonth1R1BySEOpen().get(0)[1].toString()));
                obj.setForecast2(Double.parseDouble(sourceForecastRepository3.getForecastMonth1R1BySEOpen().get(0)[1].toString()));
                obj.setForecast3(Double.parseDouble(sourceForecastRepository4.getForecastMonth1R1BySEOpen().get(0)[1].toString()));
                obj.setForecast4(Double.parseDouble(sourceForecastRepository5.getForecastMonth1R1BySEOpen().get(0)[1].toString()));
                obj.setForecast5(Double.parseDouble(sourceForecastRepository6.getForecastMonth1R1BySEOpen().get(0)[1].toString()));
                obj.setActual1(parseDouble(currentFileForecastSmSEOpen.get(0)[2]));
                obj.setActual2(parseDouble(currentFileForecastSmSEOpen.get(0)[3]));
                obj.setActual3(parseDouble(currentFileForecastSmSEOpen.get(0)[4]));
                obj.setActual4(parseDouble(currentFileForecastSmSEOpen.get(0)[5]));
                obj.setActual5(parseDouble(currentFileForecastSmSEOpen.get(0)[6]));
            }
            else {
                Double[] forecasts = forecastMap.getOrDefault(pic, new Double[]{0.0, 0.0, 0.0, 0.0, 0.0});
                obj.setForecast1(forecasts[0]);
                obj.setForecast2(forecasts[1]);
                obj.setForecast3(forecasts[2]);
                obj.setForecast4(forecasts[3]);
                obj.setForecast5(forecasts[4]);
                obj.setActual1(parseDouble(data[2]));
                obj.setActual2(parseDouble(data[3]));
                obj.setActual3(parseDouble(data[4]));
                obj.setActual4(parseDouble(data[5]));
                obj.setActual5(parseDouble(data[6]));
            }
            // Fetch forecast values from the map


            res.add(obj);
        }
        return res;
    }

    public List<SummaryBySMAndSEDTO> summaryBySMSER1Revenue() {
        List<Object[]> currentFileForecastSmSE = sourceForecastRepository.getDataActualRevenueSummaryBySMSE();
        List<Object[]> currentFileForecastSmSEOpen = sourceForecastRepository.getDataActualRevenueSummaryBySMSEOpen();
        List<SummaryBySMAndSEDTO> res = new ArrayList<>();

        // Use a HashSet for faster lookups

        List<String> pics = currentFileForecastSmSE.stream()
                .map(data -> data[1].toString()).distinct().collect(Collectors.toList());
        // Store all forecasts in a single map (Key: SE, Value: Double[5])
        Map<String, Double[]> forecastMap = new HashMap<>();
        pics.remove("Open");

        // Fetch forecasts for all five months
        List<List<Object[]>> forecastDataLists = List.of(
                sourceForecastRepository2.getForecastMonth1R1BySERevenue(pics),
                sourceForecastRepository3.getForecastMonth1R1BySERevenue(pics),
                sourceForecastRepository4.getForecastMonth1R1BySERevenue(pics),
                sourceForecastRepository5.getForecastMonth1R1BySERevenue(pics),
                sourceForecastRepository6.getForecastMonth1R1BySERevenue(pics)
        );



        // Populate the forecastMap in one pass
        for (int i = 0; i < forecastDataLists.size(); i++) {
            for (Object[] row : forecastDataLists.get(i)) {
                if (row.length > 0) {
                    String key = row[0].toString();
                    double value = parseDouble(row[1]);
                    forecastMap.computeIfAbsent(key, k -> new Double[]{0.0, 0.0, 0.0, 0.0, 0.0})[i] = value;
                }
            }
        }



        // Populate result list
        for (Object[] data : currentFileForecastSmSE) {
            SummaryBySMAndSEDTO obj = new SummaryBySMAndSEDTO();
            String sm = data[0].toString();

            String pic = data[1].toString();

            obj.setSm(sm);
            obj.setSe(pic);

            if(sm.trim().equals("Open")){
                obj.setForecast1(Double.parseDouble(sourceForecastRepository2.getForecastMonth1R1BySEOpenRevenue().get(0)[1].toString()));
                obj.setForecast2(Double.parseDouble(sourceForecastRepository3.getForecastMonth1R1BySEOpenRevenue().get(0)[1].toString()));
                obj.setForecast3(Double.parseDouble(sourceForecastRepository4.getForecastMonth1R1BySEOpenRevenue().get(0)[1].toString()));
                obj.setForecast4(Double.parseDouble(sourceForecastRepository5.getForecastMonth1R1BySEOpenRevenue().get(0)[1].toString()));
                obj.setForecast5(Double.parseDouble(sourceForecastRepository6.getForecastMonth1R1BySEOpenRevenue().get(0)[1].toString()));
                obj.setActual1(parseDouble(currentFileForecastSmSEOpen.get(0)[2]));
                obj.setActual2(parseDouble(currentFileForecastSmSEOpen.get(0)[3]));
                obj.setActual3(parseDouble(currentFileForecastSmSEOpen.get(0)[4]));
                obj.setActual4(parseDouble(currentFileForecastSmSEOpen.get(0)[5]));
                obj.setActual5(parseDouble(currentFileForecastSmSEOpen.get(0)[6]));
            }
            else {
                Double[] forecasts = forecastMap.getOrDefault(pic, new Double[]{0.0, 0.0, 0.0, 0.0, 0.0});
                obj.setForecast1(forecasts[0]);
                obj.setForecast2(forecasts[1]);
                obj.setForecast3(forecasts[2]);
                obj.setForecast4(forecasts[3]);
                obj.setForecast5(forecasts[4]);
                obj.setActual1(parseDouble(data[2]));
                obj.setActual2(parseDouble(data[3]));
                obj.setActual3(parseDouble(data[4]));
                obj.setActual4(parseDouble(data[5]));
                obj.setActual5(parseDouble(data[6]));
            }
            // Fetch forecast values from the map


            res.add(obj);
        }
        return res;
    }



    public SummaryFactoryAndRBByPLTDTO summaryFactoryAndRBByPLTDTO(List<String> listPLt){
        if (listPLt == null || listPLt.isEmpty()) {
            listPLt = null; // Or return an empty DTO
        }
        List<String> top20Rb;
        List<String> top20Factory;
        List<Object[]> dataSummaryRB;
        List<Object[]> dataSummaryFactory;
        LocalDate now = LocalDate.now();

        // Lấy tháng trước
        YearMonth previousMonth = YearMonth.from(now).minusMonths(1);
        int prevYear = previousMonth.getYear();
        int prevMonth = previousMonth.getMonthValue();
        if(listPLt != null){
            top20Rb = dataActualRepository.getTop20RBByPLT(listPLt, prevYear, prevMonth);
            top20Factory = dataActualRepository.getTop20FactoryByPLT(listPLt, prevYear, prevMonth);
            dataSummaryRB = sourceForecastRepository.getDataSummaryByPLTAndRB(top20Rb,listPLt);
            dataSummaryFactory = sourceForecastRepository.getDataSummaryByPLTAndFactory(top20Factory,listPLt);
        }
        else {
            top20Rb = dataActualRepository.getTop20RBByAllPLT(prevYear, prevMonth);
            top20Factory = dataActualRepository.getTop20FactoryByAllPLT(prevYear, prevMonth);
            dataSummaryRB = sourceForecastRepository.getDataSummaryByAllPLTAndRB(top20Rb);
            dataSummaryFactory = sourceForecastRepository.getDataSummaryByAllPLTAndFactory(top20Factory);
        }

        SummaryFactoryAndRBByPLTDTO res = new SummaryFactoryAndRBByPLTDTO();
        List< FactoryViewOfPLTDTO> viewMapperFactoryDTOView = pltViewMapper.toFactoryDTOView(dataSummaryFactory);
        List<RBViewOfPLTDTO> viewMapperRbDTOView = pltViewMapper.toRBDTOView(dataSummaryRB);
        res.setFactoryViewOfPLTDTO(viewMapperFactoryDTOView);
        res.setRbViewOfPLTDTO(viewMapperRbDTOView);
        return res;
    }
    public static List<SummaryDeepDiveByPLTR3DTO> processList(List<SummaryDeepDiveByPLTR3DTO> list) {
        return list.stream()
                // Cập nhật giá trị của 'se'
                .map(dto -> {
                    if (dto.getSe() != null && (dto.getSe().equals("CS") || dto.getSe().equals("CSR") || dto.getSe().startsWith("CS - "))) {
                        dto.setSe("CS Department");
                    }
                    return dto;
                })
                // Nhóm theo 'se', 'rb', 'billToCustomer', 'plt' và tính tổng các cột số
                .collect(Collectors.groupingBy(
                        dto -> String.join("|",
                                Optional.ofNullable(dto.getSe()).orElse(""),
                                Optional.ofNullable(dto.getRb()).orElse(""),
                                Optional.ofNullable(dto.getBillToCustomer()).orElse(""),
                                Optional.ofNullable(dto.getPlt()).orElse("")
                        ),
                        Collectors.reducing(new SummaryDeepDiveByPLTR3DTO(null, null, null, null,
                                        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
                                (dto1, dto2) -> new SummaryDeepDiveByPLTR3DTO(
                                        dto1.getSe() != null ? dto1.getSe() : dto2.getSe(),  // Lấy 'se' từ một trong hai đối tượng
                                        dto1.getRb() != null ? dto1.getRb() : dto2.getRb(),
                                        dto1.getBillToCustomer() != null ? dto1.getBillToCustomer() : dto2.getBillToCustomer(),
                                        dto1.getPlt() != null ? dto1.getPlt() : dto2.getPlt(),
                                        safeSum(dto1.getActual1(), dto2.getActual1()),
                                        safeSum(dto1.getActual2(), dto2.getActual2()),
                                        safeSum(dto1.getActual3(), dto2.getActual3()),
                                        safeSum(dto1.getActual4(), dto2.getActual4()),
                                        safeSum(dto1.getActual5(), dto2.getActual5()),
                                        safeSum(dto1.getForecast1(), dto2.getForecast1()),
                                        safeSum(dto1.getForecast2(), dto2.getForecast2()),
                                        safeSum(dto1.getForecast3(), dto2.getForecast3()),
                                        safeSum(dto1.getForecast4(), dto2.getForecast4()),
                                        safeSum(dto1.getForecast5(), dto2.getForecast5())
                                )
                        )
                ))
                // Chuyển Map thành List
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    // Hàm kiểm tra null trước khi cộng
    private static Double safeSum(Double a, Double b) {
        return (a == null ? 0.0 : a) + (b == null ? 0.0 : b);
    }

    private String buildKey(String rb,String plt, String billToCustomer, String pic, String sm) {
        return String.join("|",
                rb != null ? rb : "",
                plt != null ? plt : "",
                billToCustomer != null ? billToCustomer : "",
                pic != null ? pic : "",
                sm != null ? sm : ""
        );
    }
    private String buildKeyPlt(String rb,String se, String billToCustomer, String plt) {
        return String.join("|",
                rb != null ? rb : "",
                se != null ? se : "",
                billToCustomer != null ? billToCustomer : "",
                plt != null ? plt : ""

        );
    }



    public List<SummaryDeepDiveByPLTR3DTO> getForecastSummaryPlTR1(Boolean isViewByRB,Boolean isViewBySE, Boolean isViewByBillToName, String plt) {
        List<Object[]> results = sourceForecastRepository.getViewSummaryByPLTDeepDive(isViewBySE,isViewByRB, isViewByBillToName,plt);
        List<Object[]> results1 = sourceForecastRepository2.getViewSummaryWithFCQuantityByPLTR1(isViewByRB,isViewBySE, isViewByBillToName, plt);
        List<Object[]> results2 = sourceForecastRepository3.getViewSummaryWithFCQuantityByPLTR1(isViewByRB,isViewBySE, isViewByBillToName, plt);
        List<Object[]> results3 = sourceForecastRepository4.getViewSummaryWithFCQuantityByPLTR1(isViewByRB,isViewBySE, isViewByBillToName, plt);
        List<Object[]> results4 = sourceForecastRepository5.getViewSummaryWithFCQuantityByPLTR1(isViewByRB,isViewBySE, isViewByBillToName, plt);
        List<Object[]> results5 = sourceForecastRepository6.getViewSummaryWithFCQuantityByPLTR1(isViewByRB,isViewBySE, isViewByBillToName, plt);

        // Create a map for fast lookup
        Map<String, Double> forecastMap1 = results1.stream()
                .collect(Collectors.toMap(
                        obj -> buildKeyPlt((String) obj[1],(String) obj[0], (String) obj[2], (String) obj[3]),
                        obj -> (Double) obj[4],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        Map<String, Double> forecastMap2 = results2.stream()
                .collect(Collectors.toMap(
                        obj -> buildKeyPlt((String) obj[1],(String) obj[0], (String) obj[2], (String) obj[3]),
                        obj -> (Double) obj[4],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        Map<String, Double> forecastMap3 = results3.stream()
                .collect(Collectors.toMap(
                        obj -> buildKeyPlt((String) obj[1],(String) obj[0], (String) obj[2], (String) obj[3]),
                        obj -> (Double) obj[4],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));

        Map<String, Double> forecastMap4 = results4.stream()
                .collect(Collectors.toMap(
                        obj -> buildKeyPlt((String) obj[1],(String) obj[0], (String) obj[2], (String) obj[3]),
                        obj -> (Double) obj[4],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        Map<String, Double> forecastMap5 = results5.stream()
                .collect(Collectors.toMap(
                        obj -> buildKeyPlt((String) obj[1],(String) obj[0], (String) obj[2], (String) obj[3]),
                        obj -> (Double) obj[4],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        // Map results and merge with forecast1
        return processList(results.stream().map(obj -> {
            String key = buildKeyPlt((String) obj[1],(String) obj[0], (String) obj[2], (String) obj[3]);
            Double forecast1 = forecastMap1.getOrDefault(key, null);
            Double forecast2 = forecastMap2.getOrDefault(key, null);
            Double forecast3 = forecastMap3.getOrDefault(key, null);
            Double forecast4 = forecastMap4.getOrDefault(key, null);
            Double forecast5 = forecastMap5.getOrDefault(key, null);

            return new SummaryDeepDiveByPLTR3DTO(
                    (String) obj[0],   //SE
                    (String) obj[1],   // RB
                    (String) obj[2],   // billToCustomer
                    (String) obj[3],   // plt
                    (Double) obj[4],   // sumActual12
                    (Double) obj[5],   // sumActual11
                    (Double) obj[6],   // sumActual10
                    (Double) obj[7],   // sumActual9
                    (Double) obj[8],   // sumActual8
                    forecast1,
                    forecast2,
                    forecast3,
                    forecast4,
                    forecast5
            );
        }).collect(Collectors.toList()));
    }


    public List<SummaryDeepDiveBySMR3DTO> getForecastSummaryR1(Boolean isViewByRB,Boolean isViewByPLT, Boolean isViewByBillToName, String pic, String sm) {
        List<Object[]> results = sourceForecastRepository.getViewSummaryBySMAndSEDeepDive(isViewByRB,isViewByPLT, isViewByBillToName, pic, sm);
        List<Object[]> results1 = sourceForecastRepository2.getViewSummaryWithFCQuantityR1(isViewByRB,isViewByPLT, isViewByBillToName, pic, sm);
        List<Object[]> results2 = sourceForecastRepository3.getViewSummaryWithFCQuantityR1(isViewByRB,isViewByPLT, isViewByBillToName, pic, sm);
        List<Object[]> results3 = sourceForecastRepository4.getViewSummaryWithFCQuantityR1(isViewByRB,isViewByPLT, isViewByBillToName, pic, sm);
        List<Object[]> results4 = sourceForecastRepository5.getViewSummaryWithFCQuantityR1(isViewByRB,isViewByPLT, isViewByBillToName, pic, sm);
        List<Object[]> results5 = sourceForecastRepository6.getViewSummaryWithFCQuantityR1(isViewByRB,isViewByPLT, isViewByBillToName, pic, sm);

        // Create a map for fast lookup
        Map<String, Double> forecastMap1 = results1.stream()
                .collect(Collectors.toMap(
                        obj -> buildKey((String) obj[0],(String) obj[2], (String) obj[1], (String) obj[3], (String) obj[4]),
                        obj -> (Double) obj[5],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        Map<String, Double> forecastMap2 = results2.stream()
                .collect(Collectors.toMap(
                        obj -> buildKey((String) obj[0],(String) obj[2], (String) obj[1], (String) obj[3], (String) obj[4]),
                        obj -> (Double) obj[5],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        Map<String, Double> forecastMap3 = results3.stream()
                .collect(Collectors.toMap(
                        obj -> buildKey((String) obj[0],(String) obj[2], (String) obj[1], (String) obj[3], (String) obj[4]),
                        obj -> (Double) obj[5],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));

        Map<String, Double> forecastMap4 = results4.stream()
                .collect(Collectors.toMap(
                        obj -> buildKey((String) obj[0],(String) obj[2], (String) obj[1], (String) obj[3], (String) obj[4]),
                        obj -> (Double) obj[5],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        Map<String, Double> forecastMap5 = results5.stream()
                .collect(Collectors.toMap(
                        obj -> buildKey((String) obj[0],(String) obj[2], (String) obj[1], (String) obj[3], (String) obj[4]),
                        obj -> (Double) obj[5],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        // Map results and merge with forecast1
        return results.stream().map(obj -> {
            String key = buildKey((String) obj[0],(String) obj[2], (String) obj[1], (String) obj[3], (String) obj[4]);
            Double forecast1 = forecastMap1.getOrDefault(key, null);
            Double forecast2 = forecastMap2.getOrDefault(key, null);
            Double forecast3 = forecastMap3.getOrDefault(key, null);
            Double forecast4 = forecastMap4.getOrDefault(key, null);
            Double forecast5 = forecastMap5.getOrDefault(key, null);

            return new SummaryDeepDiveBySMR3DTO(
                    (String) obj[0],   //RB
                    (String) obj[2],   // plt
                    (String) obj[1],   // billToCustomer
                    (String) obj[3],   // pic
                    (String) obj[4],   // sm
                    (Double) obj[5],   // sumActual12
                    (Double) obj[6],   // sumActual11
                    (Double) obj[7],   // sumActual10
                    (Double) obj[8],   // sumActual9
                    (Double) obj[9],   // sumActual8
                    forecast1,
                    forecast2,
                    forecast3,
                    forecast4,
                    forecast5
            );
        }).collect(Collectors.toList());
    }


    public List<SummaryDeepDiveBySMR3DTO> getForecastSummary(Boolean isViewByRB,Boolean isViewByPLT, Boolean isViewByBillToName, String pic, String sm) {
        List<Object[]> results = sourceForecastRepository.getViewSummaryBySMAndSEDeepDive(isViewByRB,isViewByPLT, isViewByBillToName, pic, sm);
        List<Object[]> results1 = sourceForecastRepository2.getViewSummaryWithFCQuantityFinal(isViewByRB,isViewByPLT, isViewByBillToName, pic, sm);
        List<Object[]> results2 = sourceForecastRepository3.getViewSummaryWithFCQuantityFinal(isViewByRB,isViewByPLT, isViewByBillToName, pic, sm);
        List<Object[]> results3 = sourceForecastRepository4.getViewSummaryWithFCQuantityFinal(isViewByRB,isViewByPLT, isViewByBillToName, pic, sm);
        List<Object[]> results4 = sourceForecastRepository5.getViewSummaryWithFCQuantityFinal(isViewByRB,isViewByPLT, isViewByBillToName, pic, sm);
        List<Object[]> results5 = sourceForecastRepository6.getViewSummaryWithFCQuantityFinal(isViewByRB,isViewByPLT, isViewByBillToName, pic, sm);

        // Create a map for fast lookup
        Map<String, Double> forecastMap1 = results1.stream()
                .collect(Collectors.toMap(
                        obj -> buildKey((String) obj[0],(String) obj[2], (String) obj[1], (String) obj[3], (String) obj[4]),
                        obj -> (Double) obj[5],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        Map<String, Double> forecastMap2 = results2.stream()
                .collect(Collectors.toMap(
                        obj -> buildKey((String) obj[0],(String) obj[2], (String) obj[1], (String) obj[3], (String) obj[4]),
                        obj -> (Double) obj[5],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        Map<String, Double> forecastMap3 = results3.stream()
                .collect(Collectors.toMap(
                        obj -> buildKey((String) obj[0],(String) obj[2], (String) obj[1], (String) obj[3], (String) obj[4]),
                        obj -> (Double) obj[5],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));

        Map<String, Double> forecastMap4 = results4.stream()
                .collect(Collectors.toMap(
                        obj -> buildKey((String) obj[0],(String) obj[2], (String) obj[1], (String) obj[3], (String) obj[4]),
                        obj -> (Double) obj[5],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        Map<String, Double> forecastMap5 = results5.stream()
                .collect(Collectors.toMap(
                        obj -> buildKey((String) obj[0],(String) obj[2], (String) obj[1], (String) obj[3], (String) obj[4]),
                        obj -> (Double) obj[5],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        // Map results and merge with forecast1
        return results.stream().map(obj -> {
            String key = buildKey((String) obj[0],(String) obj[2], (String) obj[1], (String) obj[3], (String) obj[4]);
            Double forecast1 = forecastMap1.getOrDefault(key, null);
            Double forecast2 = forecastMap2.getOrDefault(key, null);
            Double forecast3 = forecastMap3.getOrDefault(key, null);
            Double forecast4 = forecastMap4.getOrDefault(key, null);
            Double forecast5 = forecastMap5.getOrDefault(key, null);

            return new SummaryDeepDiveBySMR3DTO(
                    (String) obj[0],   //RB
                    (String) obj[2],   // plt
                    (String) obj[1],   // billToCustomer
                    (String) obj[3],   // pic
                    (String) obj[4],   // sm
                    (Double) obj[5],   // sumActual12
                    (Double) obj[6],   // sumActual11
                    (Double) obj[7],   // sumActual10
                    (Double) obj[8],   // sumActual9
                    (Double) obj[9],   // sumActual8
                    forecast1,
                    forecast2,
                    forecast3,
                    forecast4,
                    forecast5
            );
        }).collect(Collectors.toList());
    }


    public List<SummaryDeepDiveByPLTR3DTO> getForecastSummaryByPLT(Boolean isViewByRB,Boolean isViewBySE, Boolean isViewByBillToName, String plt) {
        List<Object[]> results = sourceForecastRepository.getViewSummaryByPLTDeepDive(isViewBySE,isViewByRB, isViewByBillToName,plt);
        List<Object[]> results1 = sourceForecastRepository2.getViewSummaryWithFCQuantityFinalByPLT(isViewByRB,isViewBySE, isViewByBillToName, plt);
        List<Object[]> results2 = sourceForecastRepository3.getViewSummaryWithFCQuantityFinalByPLT(isViewByRB,isViewBySE, isViewByBillToName, plt);
        List<Object[]> results3 = sourceForecastRepository4.getViewSummaryWithFCQuantityFinalByPLT(isViewByRB,isViewBySE, isViewByBillToName,plt);
        List<Object[]> results4 = sourceForecastRepository5.getViewSummaryWithFCQuantityFinalByPLT(isViewByRB,isViewBySE, isViewByBillToName,plt);
        List<Object[]> results5 = sourceForecastRepository6.getViewSummaryWithFCQuantityFinalByPLT(isViewByRB,isViewBySE, isViewByBillToName,plt);

        // Create a map for fast lookup
        Map<String, Double> forecastMap1 = results1.stream()
                .collect(Collectors.toMap(
                        obj -> buildKeyPlt((String) obj[1],(String) obj[0], (String) obj[2], (String) obj[3]),
                        obj -> (Double) obj[4],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        Map<String, Double> forecastMap2 = results2.stream()
                .collect(Collectors.toMap(
                        obj -> buildKeyPlt((String) obj[1],(String) obj[0], (String) obj[2], (String) obj[3]),
                        obj -> (Double) obj[4],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        Map<String, Double> forecastMap3 = results3.stream()
                .collect(Collectors.toMap(
                        obj -> buildKeyPlt((String) obj[1],(String) obj[0], (String) obj[2], (String) obj[3]),
                        obj -> (Double) obj[4],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));

        Map<String, Double> forecastMap4 = results4.stream()
                .collect(Collectors.toMap(
                        obj -> buildKeyPlt((String) obj[1],(String) obj[0], (String) obj[2], (String) obj[3]),
                        obj -> (Double) obj[4],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        Map<String, Double> forecastMap5 = results5.stream()
                .collect(Collectors.toMap(
                        obj -> buildKeyPlt((String) obj[1],(String) obj[0], (String) obj[2], (String) obj[3]),
                        obj -> (Double) obj[4],
                        (existing, replacement) -> existing // handle duplicates if needed
                ));
        // Map results and merge with forecast1
        return processList(results.stream().map(obj -> {
            String key = buildKeyPlt((String) obj[1],(String) obj[0], (String) obj[2], (String) obj[3]);
            Double forecast1 = forecastMap1.getOrDefault(key, null);
            Double forecast2 = forecastMap2.getOrDefault(key, null);
            Double forecast3 = forecastMap3.getOrDefault(key, null);
            Double forecast4 = forecastMap4.getOrDefault(key, null);
            Double forecast5 = forecastMap5.getOrDefault(key, null);

            return new SummaryDeepDiveByPLTR3DTO(
                    (String) obj[0],   //SE
                    (String) obj[1],   // RB
                    (String) obj[2],   // billToCustomer
                    (String) obj[3],   // plt
                    (Double) obj[4],   // sumActual12
                    (Double) obj[5],   // sumActual11
                    (Double) obj[6],   // sumActual10
                    (Double) obj[7],   // sumActual9
                    (Double) obj[8],   // sumActual8
                    forecast1,
                    forecast2,
                    forecast3,
                    forecast4,
                    forecast5
            );
        }).collect(Collectors.toList()));
    }
}
