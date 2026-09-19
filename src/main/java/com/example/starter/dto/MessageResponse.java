package com.example.starter.dto;

import com.example.starter.domain.Message;

import java.time.Instant;

public record MessageResponse(
        Long id,
        String contenu,
        String auteur,
        Long idAnnonce,
        Message.Statut statut,
        Instant dateEnvoi
) {
}
