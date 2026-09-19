package com.example.starter.dto;

import java.time.LocalDateTime;

public class AvisResponse {
    private Long id;
    private Integer note;
    private String commentaire;
    private LocalDateTime dateCreation;
    private String emailAuteur;
    private Long annonceId;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getNote() { return note; }
    public void setNote(Integer note) { this.note = note; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public String getEmailAuteur() { return emailAuteur; }
    public void setEmailAuteur(String emailAuteur) { this.emailAuteur = emailAuteur; }

    public Long getAnnonceId() { return annonceId; }
    public void setAnnonceId(Long annonceId) { this.annonceId = annonceId; }
}