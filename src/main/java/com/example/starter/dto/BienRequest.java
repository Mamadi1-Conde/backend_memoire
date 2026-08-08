package com.example.starter.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record BienRequest(
        @NotBlank(message =" Le type de bien est obligatoire")
        String typeBien,

        BigDecimal surface,
        Short nombrePieces,

        @NotBlank(message = "L'adresse est obligatoire")
        String adresse,
        String zone,
        String ville) {
}
