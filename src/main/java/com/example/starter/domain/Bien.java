package com.example.starter.domain;


import com.example.starter.domain.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bien")
public class Bien extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_bien", nullable = false, length = 50)
    private String typeBien;
    @Column(precision = 6, scale = 2)
    private BigDecimal surface;
    @Column(name = "nombre_pieces")
    private Short nombrePieces;

    @Column(nullable = false, length = 255)
    private String adresse;

    private String zone;

    @Column(nullable = false, length = 100)
    private String ville = "Dakar";

    @OneToMany(mappedBy = "bien", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Annonce> annonces = new ArrayList<>();

    protected Bien () {}

    public Bien(String typeBien, String adresse, String ville){
        renseignerType(typeBien);
        renseignerAdresse(adresse);
        if(ville != null && !ville.isBlank())  this.ville = ville.trim();
    }

    public void renseignerType(String typeBien){
        if (typeBien == null || typeBien.isBlank()){
            throw new IllegalArgumentException("le type de bien est obligatoire");
        }
        this.typeBien = typeBien.trim();
    }

    public void renseignerAdresse(String adresse){
        if (adresse == null || adresse.isBlank()){
            throw new IllegalArgumentException("L'adresse est obligatoire");
        }
        this.adresse = adresse.trim();
    }
    public Long getId() { return id;}
    public String getTypeBien() { return typeBien;}
    public BigDecimal getSurface() { return surface;}
    public void setSurface(BigDecimal surface) { this.surface = surface;}
    public Short getNombrePieces() { return nombrePieces;}
    public void setNombrePieces(Short nombrePieces) { this.nombrePieces = nombrePieces;}
    public String getAdresse(){ return adresse;}
    public String getZone() { return zone;}
    public void setZone(String zone) {this.zone = zone;}
    public String getVille() { return ville;}
    public List<Annonce> getAnnonces() { return  annonces;}

}
