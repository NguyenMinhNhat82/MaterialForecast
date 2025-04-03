package com.materialforecast.app.repository;

import com.materialforecast.app.model.SourceMaterialForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface SourceForecastRepository extends JpaRepository<SourceMaterialForecast,String> {

    @Query("SELECT rd.plt, \n" +
            "           SUM(rd.Round3Revenue1), \n" +
            "           SUM(rd.Round3Revenue2),sum(rd.actual11Revenue),sum(rd.actual10Revenue),sum(rd.actual9Revenue),sum(rd.actual8Revenue),sum(rd.actual1Revenue)," +
            " sum(rd.FCFinanceSales1),sum(rd.FCFinanceSales2) \n" +
            "    FROM SourceMaterialForecast rd where rd.elim != 'Brand Forecast' AND rd.elim != 'Elim' " +
            "    AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' " +
            "    GROUP BY rd.plt")
    List<Object[]> getDataSummaryByPLT();


    @Query("SELECT rd.RBOName, " +
            "       SUM(rd.Round3Revenue1), " +
            "       SUM(rd.Round3Revenue2), " +
            "       SUM(rd.actual11Revenue), " +
            "       SUM(rd.actual10Revenue), " +
            "       SUM(rd.actual9Revenue), " +
            "       SUM(rd.actual8Revenue), " +
            "       SUM(rd.actual7Revenue), " +
            "       SUM(rd.FCFinanceSales1), " +
            "       SUM(rd.FCFinanceSales2) " +
            "FROM SourceMaterialForecast rd " +
            "WHERE rd.plt IN :plt " +
            "  AND rd.RBOName IN :top20Rb " +
            "  AND rd.elim != 'Brand Forecast' AND rd.elim != 'Elim' " +
            "  AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' " +
            "GROUP BY rd.RBOName")
    List<Object[]> getDataSummaryByPLTAndRB(
            @Param("top20Rb") List<String> top20Rb,
            @Param("plt") List<String> plt
    );


    @Query("SELECT rd.RBOName, " +
            "       SUM(rd.Round3Revenue1), " +
            "       SUM(rd.Round3Revenue2), " +
            "       SUM(rd.actual11Revenue), " +
            "       SUM(rd.actual10Revenue), " +
            "       SUM(rd.actual9Revenue), " +
            "       SUM(rd.actual8Revenue), " +
            "       SUM(rd.actual7Revenue), " +
            "       SUM(rd.FCFinanceSales1), " +
            "       SUM(rd.FCFinanceSales2) " +
            "FROM SourceMaterialForecast rd " +
            "WHERE rd.RBOName IN :top20Rb " +
            "  AND rd.elim != 'Brand Forecast' AND rd.elim != 'Elim' " +
            "  AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' " +
            "GROUP BY rd.RBOName")
    List<Object[]> getDataSummaryByAllPLTAndRB(
            @Param("top20Rb") List<String> top20Rb
    );


    @Query("SELECT rd.billToCustomer, " +
            "       SUM(rd.Round3Revenue1), " +
            "       SUM(rd.Round3Revenue2), " +
            "       SUM(rd.actual11Revenue), " +
            "       SUM(rd.actual10Revenue), " +
            "       SUM(rd.actual9Revenue), " +
            "       SUM(rd.actual8Revenue), " +
            "       SUM(rd.actual7Revenue), " +
            "       SUM(rd.FCFinanceSales1), " +
            "       SUM(rd.FCFinanceSales2) " +
            "FROM SourceMaterialForecast rd " +
            "WHERE rd.plt IN :plt " +
            "  AND rd.billToCustomer IN :top20factory " +
            "  AND rd.elim != 'Brand Forecast'  AND rd.elim != 'Elim' " +
            "  AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' " +
            "GROUP BY rd.billToCustomer")
    List<Object[]> getDataSummaryByPLTAndFactory(
            @Param("top20factory") List<String> top20factory,
            @Param("plt") List<String> plt
    );


    @Query("SELECT rd.billToCustomer, " +
            "       SUM(rd.Round3Revenue1), " +
            "       SUM(rd.Round3Revenue2), " +
            "       SUM(rd.actual11Revenue), " +
            "       SUM(rd.actual10Revenue), " +
            "       SUM(rd.actual9Revenue), " +
            "       SUM(rd.actual8Revenue), " +
            "       SUM(rd.actual7Revenue), " +
            "       SUM(rd.FCFinanceSales1), " +
            "       SUM(rd.FCFinanceSales2) " +
            "FROM SourceMaterialForecast rd " +
            "WHERE rd.billToCustomer IN :top20factory " +
            "  AND rd.elim != 'Brand Forecast' AND rd.elim != 'Elim' " +
            "  AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' " +
            "GROUP BY rd.billToCustomer")
    List<Object[]> getDataSummaryByAllPLTAndFactory(
            @Param("top20factory") List<String> top20factory
    );



    @Query("Select rd.sm, TRIM(rd.pic), sum(rd.actual11Revenue), sum(rd.actual10Revenue), sum(rd.actual9Revenue), sum(rd.actual8Revenue), sum(rd.actual7Revenue) " +
            "FROM SourceMaterialForecast rd " +
            "WHERE rd.elim != 'Elim' " +
            "  AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran'  " +
            "GROUP BY rd.sm, rd.pic")
    List<Object[]> getDataActualRevenueSummaryBySMSE();


    @Query("Select rd.sm, TRIM(rd.pic), sum(rd.actual11Revenue), sum(rd.actual10Revenue), sum(rd.actual9Revenue), sum(rd.actual8Revenue), sum(rd.actual7Revenue) " +
            "FROM SourceMaterialForecast rd " +
            "WHERE rd.elim != 'Elim' " +
            "  AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' and pic = 'Open' " +
            "GROUP BY rd.sm, rd.pic")
    List<Object[]> getDataActualRevenueSummaryBySMSEOpen();


    @Query("Select rd.sm, TRIM(rd.pic), sum(rd.actual11), sum(rd.actual10), sum(rd.actual9), sum(rd.actual8), sum(rd.actual7) " +
            "FROM SourceMaterialForecast rd " +
            "WHERE rd.elim != 'Elim' " +
            "  AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran'" +
            "GROUP BY rd.sm, rd.pic")
    List<Object[]> getDataActualSummaryBySMSE();

    @Query("Select rd.sm, TRIM(rd.pic), sum(rd.actual11), sum(rd.actual10), sum(rd.actual9), sum(rd.actual8), sum(rd.actual7) " +
            "FROM SourceMaterialForecast rd " +
            "WHERE rd.elim != 'Elim' " +
            "  AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' and rd.pic = 'Open'" +
            "GROUP BY rd.sm, rd.pic")
    List<Object[]> getDataActualSummaryBySMSEOpen();

    @Query("Select TRIM(rd.plt), sum(rd.actual11), sum(rd.actual10), sum(rd.actual9), sum(rd.actual8), sum(rd.actual7) " +
            "FROM SourceMaterialForecast rd " +
            "WHERE rd.elim != 'Elim' " +
            "  AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran'  " +
            "GROUP BY rd.plt")
    List<Object[]> getDataActualSummaryByPLT();


    @Query("Select TRIM(rd.plt), sum(rd.actual11Revenue), sum(rd.actual10Revenue), sum(rd.actual9Revenue), sum(rd.actual8Revenue), sum(rd.actual7Revenue) " +
            "FROM SourceMaterialForecast rd " +
            "WHERE rd.elim != 'Elim' " +
            "  AND rd.sm != 'Rose Dang' AND rd.sm != 'Vickie Tran' " +
            "GROUP BY rd.plt")
    List<Object[]> getDataActualSummaryByPLTRevenue();


    @Query("SELECT " +
            "CASE WHEN :isViewByRB = true THEN f.RBOName ELSE null END, " +
            "CASE WHEN :isViewByBillToName = true THEN f.billToCustomer ELSE null END, " +
            "CASE WHEN :isViewByPLT = true THEN f.plt ELSE null END, " +
            "f.pic, f.sm, " +
            "SUM(f.actual11), SUM(f.actual10), SUM(f.actual9), SUM(f.actual8), SUM(f.actual7) " +
            "FROM SourceMaterialForecast f " +
            "WHERE (:pic IS NULL OR f.pic = :pic) " +
            "  AND (:sm IS NULL OR f.sm = :sm) " +
            "  AND f.elim != 'Elim' " +
            "  AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' " +
            "GROUP BY " +
            "CASE WHEN :isViewByRB = true THEN f.RBOName ELSE null END, " +
            "CASE WHEN :isViewByBillToName = true THEN f.billToCustomer ELSE null END, " +
            "CASE WHEN :isViewByPLT = true THEN f.plt ELSE null END, " +
            "f.pic, f.sm")
    List<Object[]> getViewSummaryBySMAndSEDeepDive(
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
            "SUM(f.actual11), SUM(f.actual10), SUM(f.actual9), SUM(f.actual8), SUM(f.actual7) " +
            "FROM SourceMaterialForecast f " +
            "WHERE (:plt IS NULL OR f.plt = :plt) " +
            "  AND f.elim != 'Elim' " +
            "  AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' " +
            "GROUP BY " +
            "CASE WHEN :isViewBySE = true THEN f.pic ELSE null END, " +
            "CASE WHEN :isViewByRB = true THEN f.RBOName ELSE null END, " +
            "CASE WHEN :isViewByBillToName = true THEN f.billToCustomer ELSE null END, " +

            "f.plt")
    List<Object[]> getViewSummaryByPLTDeepDive(
            @Param("isViewBySE") Boolean isViewBySE,
            @Param("isViewByRB") Boolean isViewByRB,
            @Param("isViewByBillToName") Boolean isViewByBillToName,
            @Param("plt") String plt
    );


    @Query("Select f.plt ,SUM(f.actual11) " +
            "FROM SourceMaterialForecast f " +
            "WHERE f.elim != 'Elim' " +
            "  AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' " +
            "GROUP BY f.plt")
    List<Object[]> compareR1vsR3ByPLT();


    @Query("Select f.plt ,SUM(f.actual11Revenue) " +
            "FROM SourceMaterialForecast f " +
            "WHERE f.elim != 'Elim' " +
            "  AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' " +
            "GROUP BY f.plt")
    List<Object[]> compareR1vsR3ByPLTRevenue();


    @Query("Select f.sm, f.pic ,SUM(f.actual11) " +
            "FROM SourceMaterialForecast f " +
            "WHERE f.elim != 'Elim' " +
            "  AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran'  " +
            "GROUP BY f.sm,f.pic")
    List<Object[]> compareR1vsR3BySMSE();


    @Query("Select f.sm, f.pic ,SUM(f.actual11Revenue) " +
            "FROM SourceMaterialForecast f " +
            "WHERE f.elim != 'Elim' " +
            "  AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' " +
            "GROUP BY f.sm,f.pic")
    List<Object[]> compareR1vsR3BySMSERevenue();


    @Query("Select SUM(f.actual8), SUM(f.actual9), SUM(f.actual10), SUM(f.actual11), SUM(f.actual12) " +
            "FROM SourceMaterialForecast f " +
            "WHERE f.elim != 'Elim' " +
            "  AND f.sm != 'Rose Dang' AND f.sm != 'Vickie Tran' ")
    List<List<Double>> getDataActualForecast();


}
