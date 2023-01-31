package com.leketo.dynamicReport.sales.service;

import com.leketo.dynamicReport.sales.model.Family;
import com.leketo.dynamicReport.sales.model.SaleBySeller;
import com.leketo.dynamicReport.sales.model.Sales;
import com.leketo.dynamicReport.sales.model.SubFamily;
import com.leketo.dynamicReport.sales.repository.SalesRepository;
import com.leketo.dynamicReport.sales.response.BeanSalesServicioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository repository;

    public BeanSalesServicioResponse getAll(){
        List<Sales> sales = repository.getAll();
        List<SaleBySeller> saleBySellers = new ArrayList<>();
        Map<String, List<Sales>> salesPerVendedor = sales.stream()
                .collect(Collectors.groupingBy(Sales::getVendedor));
        //VENDEDOR
        for (Map.Entry<String, List<Sales>> sellers : salesPerVendedor.entrySet()) {
            SaleBySeller saleBySellerAux = new SaleBySeller();
            List<Family> familiesListAux = new ArrayList<>();
            List<Sales> salesBySeller = sellers.getValue();
            saleBySellerAux.setVendedor(sellers.getKey());

            List<BigDecimal> subTotals = salesBySeller.stream().map(Sales::getSubTotal).collect(Collectors.toList());
            BigDecimal subTotal = subTotals.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            saleBySellerAux.setSubTotal(subTotal);

            List<BigDecimal> targets = salesBySeller.stream().map(Sales::getObjetivoDelVendedor).collect(Collectors.toList());
            BigDecimal target = targets.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            saleBySellerAux.setTarget(target);

            Map<String, List<Sales>> family = salesBySeller.stream()
                    .collect(Collectors.groupingBy(Sales::getFamilia));

            //FAMILIA
            for (Map.Entry<String, List<Sales>> families : family.entrySet()) {
                List<SubFamily> subFamilies = new ArrayList<>();
                Family familyAux = new Family();

                List<Sales> familes = families.getValue();
                familyAux.setNombre(families.getKey());

                List<BigDecimal> targetsFamilia = familes.stream().map(Sales::getSubTotal).collect(Collectors.toList());
                BigDecimal targetFamilia = targetsFamilia.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                familyAux.setSubTotal(targetFamilia);

                Map<String, List<Sales>> subfamily = familes.stream()
                        .collect(Collectors.groupingBy(Sales::getSubFamilia));

                //SUB-FAMILIA
                for (Map.Entry<String, List<Sales>> subFa : subfamily.entrySet()) {
                    SubFamily subFamily = new SubFamily();
                    subFamily.setNombre(subFa.getKey());

                    List<Sales> subFamilia = subFa.getValue();

                    List<BigDecimal> subTotalsSubFamilia = subFamilia.stream().map(Sales::getSubTotal).collect(Collectors.toList());
                    BigDecimal subTotalSubFamilia = subTotalsSubFamilia.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
                    subFamily.setSubTotal(subTotalSubFamilia);
                    subFamilies.add(subFamily);

                }
                familyAux.setSubFamilies(subFamilies);
                familiesListAux.add(familyAux);
                saleBySellerAux.setFamilies(familiesListAux);
          }

            saleBySellers.add(saleBySellerAux);
        }

        return BeanSalesServicioResponse.builder().saleBySeller(saleBySellers).build();
    }

}
