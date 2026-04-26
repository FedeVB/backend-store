package com.retail.tienda.repository;

import com.retail.tienda.entity.Movimiento;
import com.retail.tienda.proyections.TotalProyection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MovimientoRepositoryTest {

    @Autowired
    private MovimientoRepository movimientoRepository;

    private Movimiento movimiento;

    @BeforeEach
    void setUp() {
        movimiento = new Movimiento();
        movimiento.setFecha(LocalDate.now());
        movimiento.setTipoPrenda("Camisa");
        movimiento.setDescripcion("Venta de camisa");
        movimiento.setMonto(new BigDecimal("100.00"));
        movimiento.setMovimiento("ENTRADA");
    }

    @Test
    void save_shouldPersistMovimiento() {
        Movimiento saved = movimientoRepository.save(movimiento);

        assertNotNull(saved.getId());
        assertEquals("Camisa", saved.getTipoPrenda());
    }

    @Test
    void findById_shouldReturnMovimiento() {
        Movimiento saved = movimientoRepository.save(movimiento);

        Optional<Movimiento> found = movimientoRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals(saved.getId(), found.get().getId());
    }

    @Test
    void findById_shouldReturnEmptyWhenNotFound() {
        Optional<Movimiento> found = movimientoRepository.findById(999L);

        assertFalse(found.isPresent());
    }

    @Test
    void existsById_shouldReturnTrueWhenExists() {
        Movimiento saved = movimientoRepository.save(movimiento);

        boolean exists = movimientoRepository.existsById(saved.getId());

        assertTrue(exists);
    }

    @Test
    void existsById_shouldReturnFalseWhenNotExists() {
        boolean exists = movimientoRepository.existsById(999L);

        assertFalse(exists);
    }

    @Test
    void deleteById_shouldRemoveMovimiento() {
        Movimiento saved = movimientoRepository.save(movimiento);
        Long id = saved.getId();

        movimientoRepository.deleteById(id);

        assertFalse(movimientoRepository.existsById(id));
    }

    @Test
    void findAll_shouldReturnPageOfMovimientos() {
        movimientoRepository.save(movimiento);
        Pageable pageable = PageRequest.of(0, 10);

        Page<Movimiento> page = movimientoRepository.findAll(pageable);

        assertFalse(page.isEmpty());
    }

    @Test
    void save_allFieldsShouldBePersisted() {
        Movimiento saved = movimientoRepository.save(movimiento);

        assertNotNull(saved.getId());
        assertEquals("Camisa", saved.getTipoPrenda());
        assertEquals("Venta de camisa", saved.getDescripcion());
        assertEquals(new BigDecimal("100.00"), saved.getMonto());
        assertEquals("ENTRADA", saved.getMovimiento());
        assertNotNull(saved.getFecha());
    }

    @Test
    void calcularTotales_shouldReturnCorrectTotals() {
        Movimiento venta = new Movimiento();
        venta.setFecha(LocalDate.now());
        venta.setTipoPrenda("Camisa");
        venta.setMonto(new BigDecimal("100.00"));
        venta.setMovimiento("VENTA");
        movimientoRepository.save(venta);

        Movimiento compra = new Movimiento();
        compra.setFecha(LocalDate.now());
        compra.setTipoPrenda("Pantalon");
        compra.setMonto(new BigDecimal("50.00"));
        compra.setMovimiento("COMPRA");
        movimientoRepository.save(compra);

        Movimiento compra2 = new Movimiento();
        compra2.setFecha(LocalDate.now());
        compra2.setTipoPrenda("Remera");
        compra2.setMonto(new BigDecimal("150.00"));
        compra2.setMovimiento("COMPRA");
        movimientoRepository.save(compra2);

        Movimiento devolucion = new Movimiento();
        devolucion.setFecha(LocalDate.now());
        devolucion.setTipoPrenda("Camisa");
        devolucion.setMonto(new BigDecimal("25.00"));
        devolucion.setMovimiento("DEVOLUCION");
        movimientoRepository.save(devolucion);


        // When (Acción)
        TotalProyection totales = movimientoRepository.calcularTotales();

        // Then (Verificación)
        // Usamos compareTo() == 0 para ignorar diferencias de escala (ej. 100.00 vs 100)
        assertTrue(new BigDecimal("100.00").compareTo(totales.getVentas()) == 0, "Las ventas deberían ser 100.00");
        assertTrue(new BigDecimal("200.00").compareTo(totales.getCompras()) == 0, "Las compras deberían ser 200.00");
        assertTrue(new BigDecimal("25.00").compareTo(totales.getDevoluciones()) == 0, "Las devoluciones deberían ser 25.00");
    }

    @Test
    void calcularTotales_shouldReturnZerosWhenEmpty() {
        TotalProyection totales = movimientoRepository.calcularTotales();

        assertEquals(BigDecimal.ZERO, totales.getVentas());
        assertEquals(BigDecimal.ZERO, totales.getCompras());
        assertEquals(BigDecimal.ZERO, totales.getDevoluciones());
    }
}