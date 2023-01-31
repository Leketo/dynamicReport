package com.leketo.dynamicReport.sales.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubFamily {
    private String nombre;
    private BigDecimal subTotal;
    private BigDecimal diferenciaObjetivo;
}
