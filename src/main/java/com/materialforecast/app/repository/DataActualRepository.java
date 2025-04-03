package com.materialforecast.app.repository;

import com.materialforecast.app.model.DataActual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DataActualRepository extends JpaRepository<DataActual,Long> {

    @Query("SELECT da.rb " +
            "FROM DataActual da " +
            "WHERE (:plt IS NULL OR da.plt IN :plt) AND da.productClass != 'RFID' AND da.elim != 'Elim'" +
            "GROUP BY da.rb " +
            "ORDER BY SUM(da.sales) DESC LIMIT 20")
    List<String> getTop20RBByPLT(@Param("plt") List<String> plt, @Param("prevYear") int prevYear,
                                 @Param("prevMonth") int prevMonth);

    @Query("SELECT da.billToName " +
            "FROM DataActual da " +
            "WHERE (:plt IS NULL OR da.plt IN :plt) AND da.productClass != 'RFID' AND da.elim != 'Elim' " +
            "GROUP BY da.billToName " +
            "ORDER BY SUM(da.sales) DESC LIMIT 20")
    List<String> getTop20FactoryByPLT(@Param("plt") List<String> plt, @Param("prevYear") int prevYear,
                                      @Param("prevMonth") int prevMonth);


    @Query("SELECT da.rb " +
            "FROM DataActual da " +
            "WHERE da.productClass != 'RFID' AND da.elim != 'Elim' "+
            "GROUP BY da.rb " +
            "ORDER BY SUM(da.sales) DESC LIMIT 20")
    List<String> getTop20RBByAllPLT( @Param("prevYear") int prevYear,
                                     @Param("prevMonth") int prevMonth);

    @Query("SELECT da.billToName " +
            "FROM DataActual da " +
            "WHERE da.productClass != 'RFID' AND da.elim != 'Elim' "+
            "GROUP BY da.billToName " +
            "ORDER BY SUM(da.sales) DESC LIMIT 20")
    List<String> getTop20FactoryByAllPLT( @Param("prevYear") int prevYear,
                                          @Param("prevMonth") int prevMonth);


}
