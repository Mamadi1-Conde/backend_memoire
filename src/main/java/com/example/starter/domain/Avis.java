package com.example.starter.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "avis")
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer note; // Note de 1 à 5

    @Column(columnDefinition = "TEXT")
    private String commentaire;

    private LocalDateTime dateCreation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "auteur_id")
    private Utilisateur auteur;

    @ManyToOne
    @JoinColumn(name = "annonce_id")
    private Annonce annonce;

    @PrePersist
    public void auStockage() {
        this.dateCreation = LocalDateTime.now();
    }

    // les getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getNote() { return note; }
    public void setNote(Integer note) { this.note = note; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public LocalDateTime getDateCreation() { return dateCreation; }

    public Utilisateur getAuteur() { return auteur; }
    public void setAuteur(Utilisateur auteur) { this.auteur = auteur; }

    public Annonce getAnnonce() { return annonce; }
    public void setAnnonce(Annonce annonce) { this.annonce = annonce; }
}