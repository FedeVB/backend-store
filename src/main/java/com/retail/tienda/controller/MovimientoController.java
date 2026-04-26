package com.retail.tienda.controller;

import com.retail.tienda.dto.MovimientoCreateDto;
import com.retail.tienda.dto.MovimientoUpdateDto;
import com.retail.tienda.dto.TotalMovimientoDto;
import com.retail.tienda.entity.Movimiento;
import com.retail.tienda.service.interfaces.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
@Validated
//@CrossOrigin(origins = "http://localhost:4200")
public class MovimientoController {

    private final MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<Page<Movimiento>> findAll(Pageable pageable) {
        return ResponseEntity.ok(movimientoService.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        movimientoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> update(@PathVariable Long id, @Valid @RequestBody MovimientoUpdateDto movimiento) {
        return ResponseEntity.ok(movimientoService.update(id, movimiento));
    }

    @PostMapping
    public ResponseEntity<Movimiento> create(@Valid @RequestBody MovimientoCreateDto movimiento) {
        return ResponseEntity.ok(movimientoService.create(movimiento));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Movimiento>> search(
            @RequestParam(required = false) String tipoPrenda,
            @RequestParam(required = false) String movimiento,
            @RequestParam(required = false) String descripcion,
            Pageable pageable) {
        return ResponseEntity.ok(movimientoService.search(tipoPrenda, movimiento, descripcion, pageable));
    }

    @GetMapping("/totals")
    public ResponseEntity<TotalMovimientoDto> getTotals() {
        return ResponseEntity.ok(movimientoService.getTotals());
    }

    @GetMapping(value = "/duplicate/id/{id}/cantidad/{cantidad}")
    public ResponseEntity<List<Movimiento>> duplicate(@PathVariable(value = "id") Long id,
                                                      @PathVariable(value = "cantidad") Integer cantidad) {
//        Map<String, Object> map = new HashMap<>();

//        map.put("message", "Movimientos cargados con exito");

        return ResponseEntity.ok(movimientoService.duplicate(id, cantidad));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Movimiento> getById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(movimientoService.findById(id));
    }

    @GetMapping("/sorted")
    public ResponseEntity<Page<Movimiento>> findAllSorted(
            @RequestParam(required = false) String tipoPrenda,
            @RequestParam(required = false) String tipoMovimiento,
            @RequestParam(required = false) String descripcion,
            Pageable pageable) {
        return ResponseEntity.ok(movimientoService.findAllSorted(tipoPrenda, tipoMovimiento, descripcion, pageable));
    }
}