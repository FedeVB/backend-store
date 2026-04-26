package com.retail.tienda.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MovimientoUpdateDtoValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidationWhenMontoIsValid() {
        MovimientoUpdateDto dto = new MovimientoUpdateDto();
        dto.setMonto(new BigDecimal("100.50"));
        dto.setMovimiento("VENTA");

        var violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenMontoIsNull() {
        MovimientoUpdateDto dto = new MovimientoUpdateDto();
        dto.setMonto(null);

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("El monto no puede estar vacío")));
    }

    @Test
    void shouldFailValidationWhenMontoIsZero() {
        MovimientoUpdateDto dto = new MovimientoUpdateDto();
        dto.setMonto(BigDecimal.ZERO);

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("El monto debe ser mayor que cero")));
    }

    @Test
    void shouldFailValidationWhenMontoIsNegative() {
        MovimientoUpdateDto dto = new MovimientoUpdateDto();
        dto.setMonto(new BigDecimal("-50.00"));

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("El monto debe ser mayor que cero")));
    }

    @Test
    void shouldPassValidationWhenMovimientoIsVenta() {
        MovimientoUpdateDto dto = new MovimientoUpdateDto();
        dto.setMonto(new BigDecimal("100.50"));
        dto.setMovimiento("VENTA");

        var violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldPassValidationWhenMovimientoIsCompra() {
        MovimientoUpdateDto dto = new MovimientoUpdateDto();
        dto.setMonto(new BigDecimal("100.50"));
        dto.setMovimiento("COMPRA");

        var violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldPassValidationWhenMovimientoIsDevolucion() {
        MovimientoUpdateDto dto = new MovimientoUpdateDto();
        dto.setMonto(new BigDecimal("100.50"));
        dto.setMovimiento("DEVOLUCION");

        var violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenMovimientoIsNull() {
        MovimientoUpdateDto dto = new MovimientoUpdateDto();
        dto.setMonto(new BigDecimal("100.50"));
        dto.setMovimiento(null);

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("El movimiento no puede estar vacío")));
    }

    @Test
    void shouldFailValidationWhenMovimientoIsInvalid() {
        MovimientoUpdateDto dto = new MovimientoUpdateDto();
        dto.setMonto(new BigDecimal("100.50"));
        dto.setMovimiento("INVALIDO");

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("El movimiento debe ser VENTA, COMPRA o DEVOLUCION")));
    }
}