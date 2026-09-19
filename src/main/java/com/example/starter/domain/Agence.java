package com.example.starter.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "agences")
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomAgence;

    private String adresse;

    private String telephone;

    private String logoUrl;

    private LocalDateTime dateCreation;

    @OneToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur proprietaire;

    @OneToMany(mappedBy = "agence", cascade = CascadeType.ALL)
    private List<Abonnement> abonnements;

    @PrePersist
    public void auStockage() {
        this.dateCreation = LocalDateTime.now();
    }

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

    public Utilisateur getProprietaire() { return proprietaire; }
    public void setProprietaire(Utilisateur proprietaire) { this.proprietaire = proprietaire; }

    public List<Abonnement> getAbonnements() { return abonnements; }
    public void setAbonnements(List<Abonnement> abonnements) { this.abonnements = abonnements; }
}