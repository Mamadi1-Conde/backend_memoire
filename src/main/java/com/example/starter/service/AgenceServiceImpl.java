package com.example.starter.service;

import com.example.starter.domain.Agence;
import com.example.starter.domain.Utilisateur;
import com.example.starter.dto.AgenceRequest;
import com.example.starter.dto.AgenceResponse;
import com.example.starter.repository.AgenceRepository;
import com.example.starter.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AgenceServiceImpl implements AgenceService {

    private final AgenceRepository agenceRepository;
    private final UtilisateurRepository utilisateurRepository;

    public AgenceServiceImpl(AgenceRepository agenceRepository, UtilisateurRepository utilisateurRepository) {
        this.agenceRepository = agenceRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public AgenceResponse creeraAgence(AgenceRequest requete, String emailProprietaire) {
        Utilisateur proprietaire = utilisateurRepository.findByEmail(emailProprietaire)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Agence agence = new Agence();
        agence.setNomAgence(requete.getNomAgence());
        agence.setAdresse(requete.getAdresse());
        agence.setTelephone(requete.getTelephone());
        agence.setLogoUrl(requete.getLogoUrl());
        agence.setProprietaire(proprietaire);

        return mapperVersResponse(agenceRepository.save(agence));
    }

    @Override
    @Transactional(readOnly = true)
    public AgenceResponse obtenirAgenceParId(Long id) {
        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agence non trouvée"));
        return mapperVersResponse(agence);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AgenceResponse> obtenirToutesLesAgences() {
        return agenceRepository.findAll().stream()
                .map(this::mapperVersResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AgenceResponse modifierAgence(Long id, AgenceRequest requete, String emailProprietaire) {
        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agence non trouvée"));

        if (!agence.getProprietaire().getEmail().equals(emailProprietaire)) {
            throw new RuntimeException("Accès refusé : Vous n'êtes pas le propriétaire de cette agence");
        }

        agence.setNomAgence(requete.getNomAgence());
        agence.setAdresse(requete.getAdresse());
        agence.setTelephone(requete.getTelephone());
        agence.setLogoUrl(requete.getLogoUrl());

        return mapperVersResponse(agenceRepository.save(agence));
    }

    @Override
    public void supprimerAgence(Long id) {
        agenceRepository.deleteById(id);
    }

    private AgenceResponse mapperVersResponse(Agence agence) {
        AgenceResponse response = new AgenceResponse();
        response.setId(agence.getId());
        response.setNomAgence(agence.getNomAgence());
        response.setAdresse(agence.getAdresse());
        response.setTelephone(agence.getTelephone());
        response.setLogoUrl(agence.getLogoUrl());
        response.setDateCreation(agence.getDateCreation());
        response.setEmailProprietaire(agence.getProprietaire().getEmail());
        return response;
    }

    
}