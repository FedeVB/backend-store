package com.retail.tienda.repository;

import com.retail.tienda.entity.TipoPrenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPrendaRepository extends JpaRepository<TipoPrenda, Integer> {
}