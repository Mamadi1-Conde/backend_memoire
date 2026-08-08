package com.example.starter.domaine;


import com.example.starter.domain.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "annonce")
public class Annonce extends BaseEntity {
    public enum  Statut { EN_ATTENTE, PUBLIEE, REJETEE, ARCHIVEE}
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="id_utilisateur", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bien", nullable = false)
    private Bien bien;

    @Column(nullable = false, length = 150)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Statut statut = Statut.EN_ATTENTE;

    @Column(name = "date_publication")
    private LocalDateTime datePublication;

    @OneToMany(mappedBy = "annonce", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    protected Annonce() {}

    public Annonce(Utilisateur utilisateur, Bien bien, String titre, BigDecimal prix){
        if (utilisateur == null || utilisateur.getRole() !=Utilisateur.Role.AGENCE) {
            throw new IllegalArgumentException("Seule une agence peut publier une annonce");
        }
        this.utilisateur = utilisateur;
        if (bien == null) throw new IllegalArgumentException("Le bien est obligatoire");
        this.bien = bien;
        renommer(titre);
        fixerPrix(prix);
    }

    public void renommer(String titre){
        if (titre == null || titre.isBlank()) throw new IllegalArgumentException("Le titre est obligatoire");
        this.titre = titre.trim();
    }

    public void fixerPrix(BigDecimal prix){
        if (prix == null || prix.signum() <= 0){
            throw new IllegalArgumentException("Le prix doit etre positif");
        }
        this.prix = prix;
    }

    public void publier(){
        this.statut = Statut.PUBLIEE;
        this.datePublication = LocalDateTime.now();
    }

    public void rejeter(){
        this.statut = Statut.REJETEE;
    }


    public void archiver(){
        this.statut = Statut.ARCHIVEE;
    }

    public Long getId() { return id; }
    public Utilisateur getUtilisateur() { return utilisateur; }
    public Bien getBien() { return bien; }
    public String getTitre() { return titre; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrix() { return prix; }
    public Statut getStatut() { return statut; }
    public LocalDateTime getDatePublication() { return datePublication; }
    public List<Message> getMessages() { return messages; }
}
