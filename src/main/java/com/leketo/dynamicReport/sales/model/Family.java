package com.leketo.dynamicReport.sales.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Family {
    private String nombre;
    private BigDecimal subTotal;
    private List<SubFamily> subFamilies;
}
