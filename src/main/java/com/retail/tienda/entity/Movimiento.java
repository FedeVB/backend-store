package com.retail.tienda.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "tipo_prenda", length = 50, nullable = false)
    private String tipoPrenda;

    private String descripcion;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column(name = "tipo", length = 20, nullable = false)
    private String movimiento;

    @CreationTimestamp
    @Column(name = "creado_en", updatable = false)
    private LocalDateTime creadoEn;


    public Movimiento(Movimiento otro) {
        this.fecha = otro.getFecha();
        this.tipoPrenda = otro.getTipoPrenda();
        this.descripcion = otro.getDescripcion();
        this.monto = otro.getMonto();
        this.movimiento = otro.getMovimiento();
    }
}
