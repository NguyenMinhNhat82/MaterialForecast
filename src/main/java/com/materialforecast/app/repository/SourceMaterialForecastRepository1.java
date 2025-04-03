package com.materialforecast.app.repository;


import com.materialforecast.app.model.SourceMaterialForecast;
import com.materialforecast.app.model.SourceMaterialForecast1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;

public interface SourceMaterialForecastRepository1 extends JpaRepository<SourceMaterialForecast1, String> {

    @Query("Select TRIM(rd.pic), sum(rd.FCFinanceQuantity1Final) from SourceMaterialForecast1 rd where TRIM(rd.pic) IN :se group by rd.pic")
    List<Object[]> getForecastMonth1R3BySE(@Param("se") List<String> se);
    @Query("Select TRIM(rd.pic), sum(rd.FCFinanceQuantity1Final) from SourceMaterialForecast2 rd where TRIM(rd.pic) = 'Open' group by rd.pic")
    List<Object[]> getForecastMonth1R3BySEOpen();

    @Query("Select TRIM(rd.pic), sum(rd.FCFinanceQuantity1) from SourceMaterialForecast1 rd where TRIM(rd.pic) IN :se group by rd.pic")
    List<Object[]> getForecastMonth1R1BySE(@Param("se") List<String> se);



    @Query("Select TRIM(rd.plt), sum(rd.FCFinanceQuantity1Final) from SourceMaterialForecast1 rd where TRIM(rd.plt) IN :plt group by rd.plt")
    List<Object[]> getForecastMonth1R3ByPLT(@Param("plt") List<String> plt);

    @Query("Select TRIM(rd.plt), sum(rd.FCFinanceQuantity1) from SourceMaterialForecast1 rd where TRIM(rd.plt) IN :plt group by rd.plt")
    List<Object[]> getForecastMonth1R1ByPLT(@Param("plt") List<String> plt);

    @Query("SELECT " +
            "CASE WHEN :isViewByRB = true THEN f.RBOName ELSE null END, " +
            "CASE WHEN :isViewByBillToName = true THEN f.billToCustomer ELSE null END, " +
            "CASE WHEN :isViewByPLT = true THEN f.plt ELSE null END, " +
            "f.pic, f.sm, " +
            "SUM(f.FCFinanceQuantity1Final) " +
            "FROM SourceMaterialForecast1 f " +
            "WHERE (:pic IS NULL OR f.pic = :pic) AND (:sm IS NULL OR f.sm = :sm) " +
            "GROUP BY " +
            "CASE WHEN :isViewByRB = true THEN f.RBOName ELSE null END, " +
            "CASE WHEN :isViewByBillToName = true THEN f.billToCustomer ELSE null END, " +
            "CASE WHEN :isViewByPLT = true THEN f.plt ELSE null END, " +
            "f.pic, f.sm")
    List<Object[]> getViewSummaryWithFCQuantityFinal(
            @Param("isViewByRB") Boolean isViewByRB,
            @Param("isViewByPLT") Boolean isViewByPLT,
            @Param("isViewByBillToName") Boolean isViewByBillToName,
            @Param("pic") String pic,
            @Param("sm") String sm
    );

    @Query("SELECT " +
            "CASE WHEN :isViewByRB = true THEN f.RBOName ELSE null END, " +
            "CASE WHEN :isViewByBillToName = true THEN f.billToCustomer ELSE null END, " +
            "CASE WHEN :isViewByPLT = true THEN f.plt ELSE null END, " +
            "f.pic, f.sm, " +
            "SUM(f.FCFinanceQuantity1) " +
            "FROM SourceMaterialForecast1 f " +
            "WHERE (:pic IS NULL OR f.pic = :pic) AND (:sm IS NULL OR f.sm = :sm) " +
            "GROUP BY " +
            "CASE WHEN :isViewByRB = true THEN f.RBOName ELSE null END, " +
            "CASE WHEN :isViewByBillToName = true THEN f.billToCustomer ELSE null END, " +
            "CASE WHEN :isViewByPLT = true THEN f.plt ELSE null END, " +
            "f.pic, f.sm")
    List<Object[]> getViewSummaryWithFCQuantityR1(
            @Param("isViewByRB") Boolean isViewByRB,
            @Param("isViewByPLT") Boolean isViewByPLT,
            @Param("isViewByBillToName") Boolean isViewByBillToName,
            @Param("pic") String pic,
            @Param("sm") String sm
    );

    @Query("SELECT f.plt ,SUM(f.FCFinanceQuantity2),SUM(f.FCFinanceQuantity2Final) FROM SourceMaterialForecast1 f" +
            " where TRIM(f.plt) IN :plts group by f.plt")
    List<Object[]>  compareR1vsR3ByPLT(@Param("plts") List<String> plts);

    @Query("SELECT f.pic ,SUM(f.FCFinanceQuantity2),SUM(f.FCFinanceQuantity2Final) FROM SourceMaterialForecast1 f" +
            " where TRIM(f.pic) IN :se group by f.pic")
    List<Object[]>  compareR1vsR3BySMSE(@Param("se") List<String> se);

    @Query("Select SUM(f.FCFinanceQuantity1),SUM(f.FCFinanceQuantity1Final)  FROM SourceMaterialForecast1 f")
    List<List<Double>> getDataForecastForecast();

}
