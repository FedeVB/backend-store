package com.retail.tienda.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoUpdateDto {

    private LocalDate fecha;
    private String tipoPrenda;
    private String descripcion;

    @NotNull(message = "El monto no puede estar vacío")
    @Positive(message = "El monto debe ser mayor que cero")
    private BigDecimal monto;

    @NotNull(message = "El movimiento no puede estar vacío")
    @Pattern(regexp = "VENTA|COMPRA|DEVOLUCION", message = "El movimiento debe ser VENTA, COMPRA o DEVOLUCION")
    private String movimiento;

}