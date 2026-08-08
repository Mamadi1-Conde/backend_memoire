package com.example.starter.domaine;


import com.example.starter.domain.BaseEntity;
import jakarta.persistence.*;


@Entity
@Table(name = "message")
public class Message extends BaseEntity {
    public enum Statut { NON_LU, LU}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_annonce", nullable = false)
    private Annonce annonce;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenu;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private  Statut statut = Statut.NON_LU;

    protected Message() {}

    public Message(Utilisateur utilisateur, Annonce annonce, String contenu){
        if (utilisateur == null ) throw new IllegalArgumentException("L'expediteur est obligatoire");
        this.utilisateur= utilisateur;
        if (annonce == null ) throw new IllegalArgumentException("L'annonce concernee est obligatoire");
        this.annonce = annonce;
        if(contenu == null || contenu.isBlank()) throw new IllegalArgumentException("Le message ne peut pas etre vide");
        this.contenu = contenu.trim();
    }

    public void marquerCommeLu(){
        this.statut = Statut.LU;
    }

    public Long getId() { return id; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public Annonce getAnnonce() { return annonce; }
    public String getContenu() { return contenu; }
    public Statut getStatut() { return statut; }
}
