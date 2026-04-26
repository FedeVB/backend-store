package com.retail.tienda.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    private LocalDateTime date;
    private String code;
    private String name;

    public ExceptionResponse(String code, String name) {
        this.date = LocalDateTime.now();
        this.code = code;
        this.name = name;
    }

}