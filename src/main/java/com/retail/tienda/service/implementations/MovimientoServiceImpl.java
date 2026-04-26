package com.retail.tienda.service.implementations;

import com.retail.tienda.dto.MovimientoCreateDto;
import com.retail.tienda.dto.MovimientoUpdateDto;
import com.retail.tienda.dto.TotalMovimientoDto;
import com.retail.tienda.entity.Movimiento;
import com.retail.tienda.exception.MovimientoNotFoundException;
import com.retail.tienda.proyections.TotalProyection;
import com.retail.tienda.repository.MovimientoRepository;
import com.retail.tienda.repository.MovimientoSpecification;
import com.retail.tienda.service.interfaces.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;

    @Override
    public Page<Movimiento> findAll(Pageable pageable) {
        return movimientoRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        if (!movimientoRepository.existsById(id)) {
            throw new MovimientoNotFoundException("Movimiento no encontrado con id: " + id);
        }
        movimientoRepository.deleteById(id);
    }

    @Override
    public Movimiento update(Long id, MovimientoUpdateDto movimiento) {
        return movimientoRepository.findById(id)
                .map(existing -> {
                    existing.setFecha(movimiento.getFecha());
                    existing.setTipoPrenda(movimiento.getTipoPrenda());
                    existing.setDescripcion(movimiento.getDescripcion());
                    existing.setMonto(movimiento.getMonto());
                    existing.setMovimiento(movimiento.getMovimiento());
                    return movimientoRepository.save(existing);
                })
                .orElseThrow(() -> new MovimientoNotFoundException("Movimiento no encontrado con id: " + id));
    }

    @Override
    public Page<Movimiento> search(String tipoPrenda, String movimiento, String descripcion, Pageable pageable) {
        Specification<Movimiento> spec = MovimientoSpecification.search(tipoPrenda, movimiento, descripcion);
        return movimientoRepository.findAll(spec, pageable);
    }

    @Override
    public Page<Movimiento> findAllSorted(String tipoPrenda, String tipoMovimiento, String descripcion, Pageable pageable) {
        Specification<Movimiento> spec = MovimientoSpecification.search(tipoPrenda, tipoMovimiento, descripcion);
        return movimientoRepository.findAll(spec, pageable);
    }

    @Override
    public List<Movimiento> duplicate(Long id, Integer cantidad) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new MovimientoNotFoundException("Movimiento con id : " + id + " no encontrado"));

        List<Movimiento> movimientos = IntStream.range(0, cantidad)
                .mapToObj(i -> new Movimiento(movimiento))
                .toList();

        return movimientoRepository.saveAll(movimientos);
    }

    @Override
    public Movimiento findById(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new MovimientoNotFoundException("Movimiento con id : " + id + " no encontrado"));
    }

    @Override
    public Movimiento create(MovimientoCreateDto movimiento) {
        Movimiento entity = new Movimiento();
        entity.setFecha(movimiento.getFecha());
        entity.setTipoPrenda(movimiento.getTipoPrenda());
        entity.setDescripcion(movimiento.getDescripcion());
        entity.setMonto(movimiento.getMonto());
        entity.setMovimiento(movimiento.getMovimiento().toUpperCase());
        return movimientoRepository.save(entity);
    }

    @Override
    public TotalMovimientoDto getTotals() {
        TotalProyection res = movimientoRepository.calcularTotales();
        return new TotalMovimientoDto(res.getVentas(), res.getCompras(), res.getDevoluciones());
    }


}