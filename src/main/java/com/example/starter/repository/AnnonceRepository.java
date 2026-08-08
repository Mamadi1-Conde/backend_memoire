package com.example.starter.repository;

import com.example.starter.domaine.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    List<Annonce> findByStatut(Annonce.Statut statut);
    List<Annonce> findByUtilisateurId(Long utilisateurId);
}
