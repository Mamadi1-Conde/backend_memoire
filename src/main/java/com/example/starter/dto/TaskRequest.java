package com.example.starter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Contrat d'entrée. Une entité JPA ne sert jamais de DTO :
 * l'API et la base peuvent ainsi évoluer indépendamment.
 */
public record TaskRequest(
        @NotBlank(message = "Le titre est obligatoire")
        @Size(max = 120, message = "Le titre ne doit pas dépasser 120 caractères")
        String title,
        boolean completed
) {}
