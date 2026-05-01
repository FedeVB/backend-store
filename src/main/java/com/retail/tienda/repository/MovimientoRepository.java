package com.retail.tienda.repository;

import com.retail.tienda.entity.Movimiento;
import com.retail.tienda.proyections.TotalProyection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface MovimientoRepository extends JpaRepository<Movimiento,Long>, JpaSpecificationExecutor<Movimiento> {

    @Query("SELECT " +
            "COALESCE(SUM(CASE WHEN m.movimiento = 'VENTA' THEN m.monto ELSE 0 END), 0) as ventas, " +
            "COALESCE(SUM(CASE WHEN m.movimiento = 'COMPRA' THEN m.monto ELSE 0 END), 0) as compras, " +
            "COALESCE(SUM(CASE WHEN m.movimiento = 'DEVOLUCION' THEN m.monto ELSE 0 END), 0) as devoluciones, " +
            "COALESCE(SUM(CASE WHEN m.movimiento = 'STOCK' THEN m.monto ELSE 0 END), 0) as stock " +
            "FROM Movimiento m")
    TotalProyection calcularTotales();

}
