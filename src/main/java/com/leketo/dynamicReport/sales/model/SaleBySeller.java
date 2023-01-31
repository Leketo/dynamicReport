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
public class SaleBySeller {
    private String vendedor;
    private BigDecimal subTotal;
    private BigDecimal target;
    private List<Family> families;
}
