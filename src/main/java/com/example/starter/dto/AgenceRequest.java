package com.example.starter.dto;

public class AgenceRequest {
    private String nomAgence;
    private String adresse;
    private String telephone;
    private String logoUrl;

    // Getters et Setters
    public String getNomAgence() { return nomAgence; }
    public void setNomAgence(String nomAgence) { this.nomAgence = nomAgence; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
}