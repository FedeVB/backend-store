package com.retail.tienda.service.interfaces;

import com.retail.tienda.dto.MovimientoCreateDto;
import com.retail.tienda.dto.MovimientoUpdateDto;
import com.retail.tienda.dto.TotalMovimientoDto;
import com.retail.tienda.entity.Movimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovimientoService {

    Page<Movimiento> findAll(Pageable pageable);

    void deleteById(Long id);

    Movimiento update(Long id, MovimientoUpdateDto movimiento);

    Movimiento create(MovimientoCreateDto movimiento);

    TotalMovimientoDto getTotals();

    Page<Movimiento> search(String tipoPrenda, String movimiento, String descripcion, Pageable pageable);

    Page<Movimiento> findAllSorted(String tipoPrenda, String tipoMovimiento, String descripcion, Pageable pageable);

    List<Movimiento> duplicate(Long id, Integer cantidad);

    Movimiento findById(Long id);
}