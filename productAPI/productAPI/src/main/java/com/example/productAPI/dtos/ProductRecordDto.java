package com.example.productAPI.dtos;

import jakarta.validation.constraints.NotBlank; //Não receber valores em branco ou nulos
import jakarta.validation.constraints.NotNull; // Não receber valores nulos (usado para números)

import java.math.BigDecimal;

//Repassando apenas nome e valor porque o ID será gerado automaticamente (olhar o Model)
public record ProductRecordDto(@NotBlank String name, @NotNull BigDecimal value) {
}
