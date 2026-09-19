package com.example.starter.dto;

import java.math.BigDecimal;

public class PaiementRequest {
    private BigDecimal montant;
    private String moyenPaiement;
    private Long abonnementId;

    // Getters et Setters
    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }

    public String getMoyenPaiement() { return moyenPaiement; }
    public void setMoyenPaiement(String moyenPaiement) { this.moyenPaiement = moyenPaiement; }

    public Long getAbonnementId() { return abonnementId; }
    public void setAbonnementId(Long abonnementId) { this.abonnementId = abonnementId; }
}