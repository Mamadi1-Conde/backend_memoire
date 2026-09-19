package com.example.starter.dto;

public class AvisRequest {
    private Integer note;
    private String commentaire;
    private Long annonceId;

    // Getters et Setters
    public Integer getNote() { return note; }
    public void setNote(Integer note) { this.note = note; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public Long getAnnonceId() { return annonceId; }
    public void setAnnonceId(Long annonceId) { this.annonceId = annonceId; }
}