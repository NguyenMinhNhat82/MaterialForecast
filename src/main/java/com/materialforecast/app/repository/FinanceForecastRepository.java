package com.materialforecast.app.repository;

import com.materialforecast.app.model.SumValueForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FinanceForecastRepository extends JpaRepository<SumValueForecast,Long> {
//    @Query("SELECT sf.plt, \n" +
//            "           SUM(CASE WHEN sf.month = '3' THEN sf.value ELSE 0 END), \n" +
//            "           SUM(CASE WHEN sf.month = '4' THEN sf.value ELSE 0 END)\n" +
//            "    FROM SumValueForecast sf \n" +
//            "    GROUP BY sf.plt")
//    public List<Object[]> getDataSummaryByPLT();
//
//    @Query("SELECT sf.rb, \n" +
//            "           SUM(CASE WHEN sf.month = '3' THEN sf.value ELSE 0 END), \n" +
//            "           SUM(CASE WHEN sf.month = '4' THEN sf.value ELSE 0 END)\n" +
//            "    FROM SumValueForecast sf WHERE SF.rb IN :top20factory AND sf.plt IN plt\n" +
//            "    GROUP BY sf.rb")
//    public List<Object[]> getDataSummaryByPLT(@Param("top20factory") List<String> top20factory,
//                                              @Param("plt") List<String> plt);

}
