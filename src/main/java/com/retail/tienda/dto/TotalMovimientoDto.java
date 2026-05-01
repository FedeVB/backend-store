package com.retail.tienda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalMovimientoDto {

    private BigDecimal totalVenta;
    private BigDecimal totalCompra;
    private BigDecimal totalDevolucion;
    private BigDecimal totalStock;
}