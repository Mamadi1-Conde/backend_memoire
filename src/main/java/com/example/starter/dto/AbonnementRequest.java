package com.example.starter.dto;

import java.math.BigDecimal;

public class AbonnementRequest {
    private String formule;
    private BigDecimal tarif;
    private Integer dureeEnMois;
    private Long agenceId;

    // Getters et Setters
    public String getFormule() { return formule; }
    public void setFormule(String formule) { this.formule = formule; }

    public BigDecimal getTarif() { return tarif; }
    public void setTarif(BigDecimal tarif) { this.tarif = tarif; }

    public Integer getDureeEnMois() { return dureeEnMois; }
    public void setDureeEnMois(Integer dureeEnMois) { this.dureeEnMois = dureeEnMois; }

    public Long getAgenceId() { return agenceId; }
    public void setAgenceId(Long agenceId) { this.agenceId = agenceId; }
}