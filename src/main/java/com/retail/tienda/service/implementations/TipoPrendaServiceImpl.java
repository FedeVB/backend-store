package com.retail.tienda.service.implementations;

import com.retail.tienda.dto.TipoPrendaCreateDto;
import com.retail.tienda.entity.TipoPrenda;
import com.retail.tienda.repository.TipoPrendaRepository;
import com.retail.tienda.service.interfaces.TipoPrendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipoPrendaServiceImpl implements TipoPrendaService {

    private final TipoPrendaRepository tipoPrendaRepository;

    @Override
    public Page<TipoPrenda> findAll(Pageable pageable) {
        return tipoPrendaRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        if (!tipoPrendaRepository.existsById(id)) {
            throw new RuntimeException("TipoPrenda no encontrado con id: " + id);
        }
        tipoPrendaRepository.deleteById(id);
    }

    @Override
    public TipoPrenda create(TipoPrendaCreateDto tipoPrenda) {
        TipoPrenda entity = new TipoPrenda();
        entity.setTipo(tipoPrenda.getTipo());
        return tipoPrendaRepository.save(entity);
    }
}