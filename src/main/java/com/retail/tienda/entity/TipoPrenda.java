package com.retail.tienda.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipos_prenda")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TipoPrenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;

    @Override
    public String toString() {
        return "TipoPrenda{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
