package com.example.starter.service;

import com.example.starter.dto.AvisRequest;
import com.example.starter.dto.AvisResponse;
import java.util.List;

public interface AvisService {
    AvisResponse ajouterAvis(AvisRequest requete, String emailAuteur);
    List<AvisResponse> obtenirAvisParAnnonce(Long annonceId);
    void supprimerAvis(Long id, String emailUtilisateur);
}