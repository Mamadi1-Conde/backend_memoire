package com.example.starter.dto;

import com.example.starter.domain.enums.StatutPaiement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaiementResponse {
    private Long id;
    private BigDecimal montant;
    private String moyenPaiement;
    private String referenceTransaction;
    private StatutPaiement statut;
    private LocalDateTime datePaiement;
    private String emailPayeur;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }

    public String getMoyenPaiement() { return moyenPaiement; }
    public void setMoyenPaiement(String moyenPaiement) { this.moyenPaiement = moyenPaiement; }

    public String getReferenceTransaction() { return referenceTransaction; }
    public void setReferenceTransaction(String referenceTransaction) { this.referenceTransaction = referenceTransaction; }

    public StatutPaiement getStatut() { return statut; }
    public void setStatut(StatutPaiement statut) { this.statut = statut; }

    public LocalDateTime getDatePaiement() { return datePaiement; }
    public void setDatePaiement(LocalDateTime datePaiement) { this.datePaiement = datePaiement; }

    public String getEmailPayeur() { return emailPayeur; }
    public void setEmailPayeur(String emailPayeur) { this.emailPayeur = emailPayeur; }
}