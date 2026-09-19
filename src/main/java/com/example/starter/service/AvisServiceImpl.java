package com.example.starter.service;

import com.example.starter.domain.Annonce;
import com.example.starter.domain.Avis;
import com.example.starter.domain.Utilisateur;
import com.example.starter.dto.AvisRequest;
import com.example.starter.dto.AvisResponse;
import com.example.starter.repository.AnnonceRepository;
import com.example.starter.repository.AvisRepository;
import com.example.starter.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AvisServiceImpl implements AvisService {

    private final AvisRepository avisRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final AnnonceRepository annonceRepository;

    public AvisServiceImpl(AvisRepository avisRepository, UtilisateurRepository utilisateurRepository, AnnonceRepository annonceRepository) {
        this.avisRepository = avisRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.annonceRepository = annonceRepository;
    }

    @Override
    public AvisResponse ajouterAvis(AvisRequest requete, String emailAuteur) {
        Utilisateur auteur = utilisateurRepository.findByEmail(emailAuteur)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Annonce annonce = annonceRepository.findById(requete.getAnnonceId())
                .orElseThrow(() -> new RuntimeException("Annonce non trouvée"));

        Avis avis = new Avis();
        avis.setNote(requete.getNote());
        avis.setCommentaire(requete.getCommentaire());
        avis.setAuteur(auteur);
        avis.setAnnonce(annonce);

        return mapperVersResponse(avisRepository.save(avis));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AvisResponse> obtenirAvisParAnnonce(Long annonceId) {
        return avisRepository.findByAnnonceId(annonceId).stream()
                .map(this::mapperVersResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void supprimerAvis(Long id, String emailUtilisateur) {
        Avis avis = avisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis non trouvé"));

        boolean estAuteur = avis.getAuteur().getEmail().equals(emailUtilisateur);
        boolean estAdmin = utilisateurRepository.findByEmail(emailUtilisateur)
                .map(u -> u.getRole().name().equals("ROLE_ADMIN"))
                .orElse(false);

        if (!estAuteur && !estAdmin) {
            throw new RuntimeException("Accès refusé : Vous ne pouvez pas supprimer cet avis");
        }

        avisRepository.deleteById(id);
    }

    private AvisResponse mapperVersResponse(Avis avis) {
        AvisResponse response = new AvisResponse();
        response.setId(avis.getId());
        response.setNote(avis.getNote());
        response.setCommentaire(avis.getCommentaire());
        response.setDateCreation(avis.getDateCreation());
        response.setEmailAuteur(avis.getAuteur().getEmail());
        response.setAnnonceId(avis.getAnnonce() != null ? avis.getAnnonce().getId() : null);
        return response;
    }
}