package com.materialforecast.app.mapper;

import com.materialforecast.app.dto.FactoryViewOfPLTDTO;
import com.materialforecast.app.dto.PltViewDTO;
import com.materialforecast.app.dto.RBViewOfPLTDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PltViewMapper {
    public List<PltViewDTO> toPLTDTOView (List<Object[]> input){
        List<PltViewDTO> res = new ArrayList<>();
        for(Object[] os : input){
            PltViewDTO viewDTO = new PltViewDTO();
            viewDTO.setPlt(os[0].toString());
            viewDTO.setR3ForecastFirstMonth(Double.parseDouble(os[1].toString()));
            viewDTO.setR3ForecastSecondMonth(Double.parseDouble(os[2].toString()));

            viewDTO.setActual5(Double.parseDouble(os[3] == null?"0":os[3].toString()));
            viewDTO.setActual4(Double.parseDouble(os[4] == null?"0":os[4].toString()));
            viewDTO.setActual3(Double.parseDouble(os[5] == null?"0":os[5].toString()));
            viewDTO.setActual2(Double.parseDouble(os[6] == null?"0":os[6].toString()));
            viewDTO.setActual1(Double.parseDouble(os[7] == null?"0":os[7].toString()));

            viewDTO.setR3FnFirstMonth(Double.parseDouble(os[8] == null?"0":os[8].toString()));
            viewDTO.setR3FnSecondMonth(Double.parseDouble(os[9] == null?"0":os[9].toString()));
            res.add(viewDTO);
        }
        return res;
    }

    public List<RBViewOfPLTDTO> toRBDTOView (List<Object[]> input){
        List<RBViewOfPLTDTO> res = new ArrayList<>();
        for(Object[] os : input){
            RBViewOfPLTDTO viewDTO = new RBViewOfPLTDTO();
            viewDTO.setRb(os[0].toString());
            viewDTO.setR3ForecastFirstMonth(Double.parseDouble(os[1].toString()));
            viewDTO.setR3ForecastSecondMonth(Double.parseDouble(os[2].toString()));

            viewDTO.setActual5(Double.parseDouble(os[3] == null?"0":os[3].toString()));
            viewDTO.setActual4(Double.parseDouble(os[4] == null?"0":os[4].toString()));
            viewDTO.setActual3(Double.parseDouble(os[5] == null?"0":os[5].toString()));
            viewDTO.setActual2(Double.parseDouble(os[6] == null?"0":os[6].toString()));
            viewDTO.setActual1(Double.parseDouble(os[7] == null?"0":os[7].toString()));

            viewDTO.setR3FnFirstMonth(Double.parseDouble(os[8].toString()));
            viewDTO.setR3FnSecondMonth(Double.parseDouble(os[9].toString()));
            res.add(viewDTO);
        }
        return res;
    }


    public List<FactoryViewOfPLTDTO> toFactoryDTOView (List<Object[]> input){
        List<FactoryViewOfPLTDTO> res = new ArrayList<>();
        for(Object[] os : input){
            FactoryViewOfPLTDTO viewDTO = new FactoryViewOfPLTDTO();
            viewDTO.setFactory(os[0].toString());
            viewDTO.setR3ForecastFirstMonth(Double.parseDouble(os[1].toString()));
            viewDTO.setR3ForecastSecondMonth(Double.parseDouble(os[2].toString()));

            viewDTO.setActual5(Double.parseDouble(os[3] == null?"0":os[3].toString()));
            viewDTO.setActual4(Double.parseDouble(os[4] == null?"0":os[4].toString()));
            viewDTO.setActual3(Double.parseDouble(os[5] == null?"0":os[5].toString()));
            viewDTO.setActual2(Double.parseDouble(os[6] == null?"0":os[6].toString()));
            viewDTO.setActual1(Double.parseDouble(os[7] == null?"0":os[7].toString()));

            viewDTO.setR3FnFirstMonth(Double.parseDouble(os[8].toString()));
            viewDTO.setR3FnSecondMonth(Double.parseDouble(os[9].toString()));
            res.add(viewDTO);
        }
        return res;
    }
}
