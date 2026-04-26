package com.retail.tienda.repository;

import com.retail.tienda.entity.Movimiento;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MovimientoSpecification {

    public static Specification<Movimiento> search(String tipoPrenda, String movimiento, String descripcion) {
        return (root, query, cb) -> {
            List<jakarta.persistence.criteria.Predicate> predicates = new ArrayList<>();

            if (tipoPrenda != null && !tipoPrenda.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("tipoPrenda")), "%" + tipoPrenda.toLowerCase() + "%"));
            }

            if (movimiento != null && !movimiento.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("movimiento")), "%" + movimiento.toLowerCase() + "%"));
            }

            if (descripcion != null && !descripcion.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("descripcion")), "%" + descripcion.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}