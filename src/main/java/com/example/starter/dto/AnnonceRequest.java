package com.example.starter.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AnnonceRequest(
        @NotBlank(message="Le titre est obligatoire")
        String titre,
        String description,

        @NotNull(message = "Le prix est obligatoire")
        @Positive(message ="Le prix doit etre positif")
        BigDecimal prix,

        @NotNull(message = "Les informations du bien sont obligatoires")
        @Valid
        BienRequest bien
) {
}
