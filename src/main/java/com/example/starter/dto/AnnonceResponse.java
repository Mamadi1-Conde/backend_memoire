package com.example.starter.dto;

import com.example.starter.domain.Annonce;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AnnonceResponse(
        Long id,
        String titre,
        String descrption,
        BigDecimal prix,
        Annonce.Statut statut,
        LocalDateTime datePublication,
        BienResponse bien,
        String nomAgence
) {
}
