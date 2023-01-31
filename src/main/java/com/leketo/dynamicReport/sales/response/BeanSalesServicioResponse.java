package com.leketo.dynamicReport.sales.response;

import com.leketo.dynamicReport.sales.model.SaleBySeller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeanSalesServicioResponse {
    List<SaleBySeller> saleBySeller;
}
