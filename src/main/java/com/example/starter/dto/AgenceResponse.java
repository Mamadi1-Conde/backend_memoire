package com.example.starter.dto;

import java.time.LocalDateTime;

public class AgenceResponse {
    private Long id;
    private String nomAgence;
    private String adresse;
    private String telephone;
    private String logoUrl;
    private LocalDateTime dateCreation;
    private String emailProprietaire;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomAgence() { return nomAgence; }
    public void setNomAgence(String nomAgence) { this.nomAgence = nomAgence; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public String getEmailProprietaire() { return emailProprietaire; }
    public void setEmailProprietaire(String emailProprietaire) { this.emailProprietaire = emailProprietaire; }
}