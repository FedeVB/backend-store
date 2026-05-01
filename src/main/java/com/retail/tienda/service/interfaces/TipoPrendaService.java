package com.retail.tienda.service.interfaces;

import com.retail.tienda.dto.TipoPrendaCreateDto;
import com.retail.tienda.entity.TipoPrenda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipoPrendaService {

    Page<TipoPrenda> findAll(Pageable pageable);

    void deleteById(Integer id);

    TipoPrenda create(TipoPrendaCreateDto tipoPrenda);
}