package com.retail.tienda.controller;

import com.retail.tienda.dto.TipoPrendaCreateDto;
import com.retail.tienda.entity.TipoPrenda;
import com.retail.tienda.service.interfaces.TipoPrendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipos-prenda")
@RequiredArgsConstructor
@Validated
public class TipoPrendaController {

    private final TipoPrendaService tipoPrendaService;

    @GetMapping
    public ResponseEntity<Page<TipoPrenda>> findAll(Pageable pageable) {
        return ResponseEntity.ok(tipoPrendaService.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        tipoPrendaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<TipoPrenda> create(@Valid @RequestBody TipoPrendaCreateDto tipoPrenda) {
        return ResponseEntity.ok(tipoPrendaService.create(tipoPrenda));
    }
}