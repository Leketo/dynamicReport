package com.leketo.dynamicReport.sales.controller;

import com.leketo.dynamicReport.sales.model.Sales;
import com.leketo.dynamicReport.sales.response.BeanSalesServicioResponse;
import com.leketo.dynamicReport.sales.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

        @GetMapping("/find_all")
    public ResponseEntity<BeanSalesServicioResponse> getAll() {
        return ResponseEntity.ok(salesService.getAll());
    }


}
