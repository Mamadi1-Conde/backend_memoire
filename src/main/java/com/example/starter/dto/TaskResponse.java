package com.example.starter.dto;

import java.time.Instant;

/** Contrat de sortie immuable : seules les données choisies sont exposées. */
public record TaskResponse(
        Long id,
        String title,
        boolean completed,
        Instant createdAt,
        Instant updatedAt
) {}
