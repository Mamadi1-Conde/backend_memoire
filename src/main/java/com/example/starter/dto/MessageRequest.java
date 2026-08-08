package com.example.starter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MessageRequest(
        @NotNull(message ="L'annonce concernee est obligatoire")
        Long idAnnonce,

        @NotBlank(message = "Le message ne peut pas etre vide")
        String contenu
)
{
}
