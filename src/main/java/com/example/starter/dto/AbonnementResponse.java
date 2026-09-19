package com.example.starter.dto;

import com.example.starter.domain.enums.StatutAbonnement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AbonnementResponse {
    private Long id;
    private String formule;
    private BigDecimal tarif;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private StatutAbonnement statut;
    private String nomAgence;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFormule() { return formule; }
    public void setFormule(String formule) { this.formule = formule; }

    public BigDecimal getTarif() { return tarif; }
    public void setTarif(BigDecimal tarif) { this.tarif = tarif; }

    public LocalDateTime getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }

    public LocalDateTime getDateFin() { return dateFin; }
    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }

    public StatutAbonnement getStatut() { return statut; }
    public void setStatut(StatutAbonnement statut) { this.statut = statut; }

    public String getNomAgence() { return nomAgence; }
    public void setNomAgence(String nomAgence) { this.nomAgence = nomAgence; }
}