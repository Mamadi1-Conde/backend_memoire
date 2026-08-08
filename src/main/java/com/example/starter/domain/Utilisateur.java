package com.example.starter.domaine;
import com.example.starter.domain.BaseEntity;
import jakarta.persistence.*;
//import org.aspectj.bridge.Message;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class Utilisateur  extends BaseEntity {
    public enum Role {CHERCHEUR, AGENCE, ADMINISTRATEUR}
    public enum StatutVerification {EN_ATTENTE, VERIFIE, REJETE}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    private String nom;
    private String prenom;
    @Column(nullable = false, unique = true, length = 200)
    private String email;
    @Column(name ="mot_de_passe", nullable = false)
    private String motDePasse;
    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(name = "nom_agence", length = 150)
    private String nomAgence;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut_verification", length = 20)
    private StatutVerification statutVerification = StatutVerification.EN_ATTENTE;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Annonce> annonces = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    protected Utilisateur(){}

    public Utilisateur(String nom, String email, String motDePasseHache, Role role){
        renommer(nom);
        changerEmail(email);
        if(motDePasseHache == null || motDePasseHache.isBlank()){
            throw new IllegalArgumentException("Le mot de passe est obligatoire");
        }
        this.motDePasse = motDePasseHache;
        if(role == null) throw new IllegalArgumentException("Le role est obligatoire");
        this.role = role;
    }
    public void renommer(String nom){
        if (nom == null || nom.isBlank()) throw new IllegalArgumentException("Le nom est obligatoire");
        this.nom = nom.trim();
    }

    public void changerEmail(String email){
        if (email == null || !email.contains("@")) throw new IllegalStateException("Email invalide");
        this.email = email.trim().toLowerCase();
    }
    public void verifierAgence(){
        if (role != Role.AGENCE) throw new IllegalStateException("Seule une agence peut etre verifiee ");
        this.statutVerification = StatutVerification.VERIFIE;
    }
    public void rejeterAgence(){
        if(role != Role.AGENCE) throw new IllegalStateException("Seule une agence peut etre rejeter");
        this.statutVerification = StatutVerification.REJETE;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public String getMotDePasse() { return motDePasse; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public Role getRole() { return role; }
    public String getNomAgence() { return nomAgence; }
    public void setNomAgence(String nomAgence) { this.nomAgence = nomAgence; }
    public StatutVerification getStatutVerification() { return statutVerification; }
    public List<Annonce> getAnnonces() { return annonces; }
    public List<Message> getMessages() { return messages; }
}
