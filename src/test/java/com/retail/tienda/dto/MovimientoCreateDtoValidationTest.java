package com.retail.tienda.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MovimientoCreateDtoValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidationWhenMontoIsValid() {
        MovimientoCreateDto dto = new MovimientoCreateDto();
        dto.setMonto(new BigDecimal("100.50"));
        dto.setMovimiento("VENTA");

        var violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenMontoIsNull() {
        MovimientoCreateDto dto = new MovimientoCreateDto();
        dto.setMonto(null);

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("El monto no puede estar vacío")));
    }

    @Test
    void shouldFailValidationWhenMontoIsZero() {
        MovimientoCreateDto dto = new MovimientoCreateDto();
        dto.setMonto(BigDecimal.ZERO);

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("El monto debe ser mayor que cero")));
    }

    @Test
    void shouldFailValidationWhenMontoIsNegative() {
        MovimientoCreateDto dto = new MovimientoCreateDto();
        dto.setMonto(new BigDecimal("-50.00"));

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("El monto debe ser mayor que cero")));
    }

    @Test
    void shouldPassValidationWhenMovimientoIsVenta() {
        MovimientoCreateDto dto = new MovimientoCreateDto();
        dto.setMonto(new BigDecimal("100.50"));
        dto.setMovimiento("VENTA");

        var violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldPassValidationWhenMovimientoIsCompra() {
        MovimientoCreateDto dto = new MovimientoCreateDto();
        dto.setMonto(new BigDecimal("100.50"));
        dto.setMovimiento("COMPRA");

        var violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldPassValidationWhenMovimientoIsDevolucion() {
        MovimientoCreateDto dto = new MovimientoCreateDto();
        dto.setMonto(new BigDecimal("100.50"));
        dto.setMovimiento("DEVOLUCION");

        var violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidationWhenMovimientoIsNull() {
        MovimientoCreateDto dto = new MovimientoCreateDto();
        dto.setMonto(new BigDecimal("100.50"));
        dto.setMovimiento(null);

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("El movimiento no puede estar vacío")));
    }

    @Test
    void shouldFailValidationWhenMovimientoIsInvalid() {
        MovimientoCreateDto dto = new MovimientoCreateDto();
        dto.setMonto(new BigDecimal("100.50"));
        dto.setMovimiento("INVALIDO");

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("El movimiento debe ser VENTA, COMPRA o DEVOLUCION")));
    }
}