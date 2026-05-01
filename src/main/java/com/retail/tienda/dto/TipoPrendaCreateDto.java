package com.retail.tienda.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoPrendaCreateDto {

    @NotBlank(message = "El tipo es obligatorio")
    private String tipo;
}