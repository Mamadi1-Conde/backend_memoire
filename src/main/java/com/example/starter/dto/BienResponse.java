package com.example.starter.dto;

import java.math.BigDecimal;

public record BienResponse(
        Long id,
        String typeBien,
        BigDecimal surface,
        Short nombrePieces,
        String adresse,
        String zone,
        String ville

) {
}
