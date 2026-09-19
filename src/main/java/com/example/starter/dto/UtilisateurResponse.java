package com.example.starter.dto;

import com.example.starter.domain.Utilisateur;

import java.time.Instant;

public record UtilisateurResponse(
        Long id,
        String nom,
        String prenom,
        String email,
        String telephone,
        Utilisateur.Role role,
        String nomAgnece,
        Utilisateur.StatutVerification statutVerification,
        Instant createdAt) {
}
