package com.materialforecast.app.repository;


import com.materialforecast.app.model.SourceMaterialForecast5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SourceMaterialForecastRepository5 extends JpaRepository<SourceMaterialForecast5, String> {
    @Query("Select TRIM(rd.pic), sum(rd.FCFinanceQuantity1Final) from SourceMaterialForecast5 rd where TRIM(rd.pic) IN :se AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' AND TRIM(rd.plt) != 'Freight' group by rd.pic")
    List<Object[]> getForecastMonth1R3BySE(@Param("se") List<String> se);

    @Query("Select TRIM(rd.pic), sum(rd.Round3Revenue1) from SourceMaterialForecast5 rd where TRIM(rd.pic) IN :se AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' AND TRIM(rd.plt) != 'Freight' group by rd.pic")
    List<Object[]> getForecastMonth1R3BySERevenue(@Param("se") List<String> se);

    @Query("Select TRIM(rd.pic), sum(rd.Round3Revenue1) from SourceMaterialForecast5 rd where TRIM(rd.pic) = 'Open' AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' AND TRIM(rd.plt) != 'Freight' group by rd.pic")
    List<Object[]> getForecastMonth1R3BySEOpenRevenue();

    @Query("Select TRIM(rd.pic), sum(rd.Round1Revenue1) from SourceMaterialForecast5 rd where TRIM(rd.pic) IN :se AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' AND TRIM(rd.plt) != 'Freight' group by rd.pic")
    List<Object[]> getForecastMonth1R1BySERevenue(@Param("se") List<String> se);

    @Query("Select TRIM(rd.pic), sum(rd.Round1Revenue1) from SourceMaterialForecast5 rd where TRIM(rd.pic) = 'Open' AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' AND TRIM(rd.plt) != 'Freight' group by rd.pic")
    List<Object[]> getForecastMonth1R1BySEOpenRevenue();

    @Query("Select TRIM(rd.pic), sum(rd.FCFinanceQuantity1Final) from SourceMaterialForecast5 rd where TRIM(rd.pic) = 'Open' AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' AND TRIM(rd.plt) != 'Freight' group by rd.pic")
    List<Object[]> getForecastMonth1R3BySEOpen();

    @Query("Select TRIM(rd.pic), sum(rd.FCFinanceQuantity1) from SourceMaterialForecast5 rd where TRIM(rd.pic) IN :se AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' AND TRIM(rd.plt) != 'Freight' group by rd.pic")
    List<Object[]> getForecastMonth1R1BySE(@Param("se") List<String> se);

    @Query("Select TRIM(rd.pic), sum(rd.FCFinanceQuantity1) from SourceMaterialForecast5 rd where TRIM(rd.pic) = 'Open' AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' AND TRIM(rd.plt) != 'Freight' group by rd.pic")
    List<Object[]> getForecastMonth1R1BySEOpen();

    // Note: The TRIM(rd.plt) != 'Freight' condition is technically redundant if 'Freight' is never in the :plt list,
    // but adding it ensures 'Freight' is excluded even if passed in the list.
    @Query("Select TRIM(rd.plt), sum(rd.FCFinanceQuantity1Final) from SourceMaterialForecast5 rd where TRIM(rd.plt) IN :plt AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' AND TRIM(rd.plt) != 'Freight' group by rd.plt")
    List<Object[]> getForecastMonth1R3ByPLT(@Param("plt") List<String> plt);

    @Query("Select TRIM(rd.plt), sum(rd.FCFinanceQuantity1) from SourceMaterialForecast5 rd where TRIM(rd.plt) IN :plt AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' AND TRIM(rd.plt) != 'Freight' group by rd.plt")
    List<Object[]> getForecastMonth1R1ByPLT(@Param("plt") List<String> plt);

    @Query("Select TRIM(rd.plt), sum(rd.Round3Revenue1) from SourceMaterialForecast5 rd where TRIM(rd.plt) IN :plt AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' AND TRIM(rd.plt) != 'Freight' group by rd.plt")
    List<Object[]> getForecastMonth1R3ByPLTRevenue(@Param("plt") List<String> plt);

    @Query("Select TRIM(rd.plt), sum(rd.Round1Revenue1) from SourceMaterialForecast5 rd where TRIM(rd.plt) IN :plt AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' AND TRIM(rd.plt) != 'Freight' group by rd.plt")
    List<Object[]> getForecastMonth1R1ByPLTRevenue(@Param("plt") List<String> plt);

    @Query("SELECT " +
            "CASE WHEN :isViewByRB = true THEN f.RBOName ELSE null END, " +
            "CASE WHEN :isViewByBillToName = true THEN f.billToCustomer ELSE null END, " +
            "CASE WHEN :isViewByPLT = true THEN f.plt ELSE null END, " +
            "f.pic, f.sm, " +
            "SUM(f.FCFinanceQuantity1Final) " +
            "FROM SourceMaterialForecast5 f " +
            "WHERE (:pic IS NULL OR f.pic = :pic) AND (:sm IS NULL OR f.sm = :sm) " +
            "AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' AND TRIM(f.plt) != 'Freight' " + // Added condition
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
            "CASE WHEN :isViewBySE = true THEN f.pic ELSE null END, " +
            "CASE WHEN :isViewByRB = true THEN f.RBOName ELSE null END, " +
            "CASE WHEN :isViewByBillToName = true THEN f.billToCustomer ELSE null END, " +
            "f.plt," +
            "SUM(f.FCFinanceQuantity1Final) " +
            "FROM SourceMaterialForecast5 f " +
            "WHERE (:plt IS NULL OR f.plt = :plt) " +
            "AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' AND TRIM(f.plt) != 'Freight' " + // Added condition
            "GROUP BY " +
            "CASE WHEN :isViewBySE = true THEN f.pic ELSE null END, " +
            "CASE WHEN :isViewByRB = true THEN f.RBOName ELSE null END, " +
            "CASE WHEN :isViewByBillToName = true THEN f.billToCustomer ELSE null END, " +
            "f.plt")
    List<Object[]> getViewSummaryWithFCQuantityFinalByPLT(
            @Param("isViewByRB") Boolean isViewByRB,
            @Param("isViewBySE") Boolean isViewByPLT, // Check param name, maybe should be isViewBySE?
            @Param("isViewByBillToName") Boolean isViewByBillToName,
            @Param("plt") String plt
    );

    @Query("SELECT " +
            "CASE WHEN :isViewByRB = true THEN f.RBOName ELSE null END, " +
            "CASE WHEN :isViewByBillToName = true THEN f.billToCustomer ELSE null END, " +
            "CASE WHEN :isViewByPLT = true THEN f.plt ELSE null END, " +
            "f.pic, f.sm, " +
            "SUM(f.FCFinanceQuantity1) " +
            "FROM SourceMaterialForecast5 f " +
            "WHERE (:pic IS NULL OR f.pic = :pic) AND (:sm IS NULL OR f.sm = :sm) " +
            "AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' AND TRIM(f.plt) != 'Freight' " + // Added condition
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


    @Query("SELECT " +
            "CASE WHEN :isViewBySE = true THEN f.pic ELSE null END, " +
            "CASE WHEN :isViewByRB = true THEN f.RBOName ELSE null END, " +
            "CASE WHEN :isViewByBillToName = true THEN f.billToCustomer ELSE null END, " +
            "f.plt, " +
            "SUM(f.FCFinanceQuantity1) " +
            "FROM SourceMaterialForecast5 f " +
            "WHERE (:plt IS NULL OR f.plt = :plt) " +
            "AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' AND TRIM(f.plt) != 'Freight' " + // Added condition
            "GROUP BY " +
            "CASE WHEN :isViewBySE = true THEN f.pic ELSE null END, " +
            "CASE WHEN :isViewByRB = true THEN f.RBOName ELSE null END, " +
            "CASE WHEN :isViewByBillToName = true THEN f.billToCustomer ELSE null END, " +
            "f.plt")
    List<Object[]> getViewSummaryWithFCQuantityByPLTR1(
            @Param("isViewByRB") Boolean isViewByRB,
            @Param("isViewBySE") Boolean isViewBySE,
            @Param("isViewByBillToName") Boolean isViewByBillToName,
            @Param("plt") String plt
    );

    @Query("SELECT f.plt ,SUM(f.FCFinanceQuantity1),SUM(f.FCFinanceQuantity1Final) FROM SourceMaterialForecast5 f" +
            " where TRIM(f.plt) IN :plts AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' AND TRIM(f.plt) != 'Freight' group by f.plt") // Added condition
    List<Object[]>  compareR1vsR3ByPLT(@Param("plts") List<String> plts);

    @Query("SELECT f.plt ,SUM(f.Round1Revenue1),SUM(f.Round3Revenue1) FROM SourceMaterialForecast5 f" +
            " where TRIM(f.plt) IN :plts AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' AND TRIM(f.plt) != 'Freight' group by f.plt") // Added condition
    List<Object[]>  compareR1vsR3ByPLTRevenue(@Param("plts") List<String> plts);

    @Query("SELECT f.pic ,SUM(f.FCFinanceQuantity1),SUM(f.FCFinanceQuantity1Final) FROM SourceMaterialForecast5 f" +
            " where TRIM(f.pic) IN :se AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' AND TRIM(f.plt) != 'Freight' group by f.pic") // Added condition
    List<Object[]>  compareR1vsR3BySMSE(@Param("se") List<String> se);

    @Query("SELECT f.pic ,SUM(f.Round1Revenue1),SUM(f.Round3Revenue1) FROM SourceMaterialForecast5 f" +
            " where TRIM(f.pic) IN :se AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' AND TRIM(f.plt) != 'Freight' group by f.pic") // Added condition
    List<Object[]>  compareR1vsR3BySMSEByRevenue(@Param("se") List<String> se);

    @Query("SELECT f.pic ,SUM(f.Round1Revenue1),SUM(f.Round3Revenue1) FROM SourceMaterialForecast5 f" +
            " where TRIM(f.pic) = 'Open' AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' AND TRIM(f.plt) != 'Freight' group by f.pic") // Added condition
    List<Object[]>  compareR1vsR3BySMSEOpenByRevenue();

    @Query("SELECT f.pic ,SUM(f.FCFinanceQuantity1),SUM(f.FCFinanceQuantity1Final) FROM SourceMaterialForecast5 f" +
            " where TRIM(f.pic) = 'Open' AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' AND TRIM(f.plt) != 'Freight' group by f.pic") // Added condition
    List<Object[]>  compareR1vsR3BySMSEOpen();

    @Query("Select SUM(f.FCFinanceQuantity1),SUM(f.FCFinanceQuantity1Final)  FROM SourceMaterialForecast5 f where f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' AND TRIM(f.plt) != 'Freight'") // Added condition
    List<List<Double>> getDataForecastForecast();

}


