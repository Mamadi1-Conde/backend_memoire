package com.example.starter.service;

import com.example.starter.dto.PaiementRequest;
import com.example.starter.dto.PaiementResponse;
import java.util.List;

public interface PaiementService {
    PaiementResponse effectuerPaiement(PaiementRequest requete, String emailPayeur);
    List<PaiementResponse> obtenirPaiementsParUtilisateur(String emailPayeur);
    List<PaiementResponse> obtenirTousLesPaiements();
}