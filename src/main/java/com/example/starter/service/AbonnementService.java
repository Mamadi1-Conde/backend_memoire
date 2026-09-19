package com.example.starter.service;

import com.example.starter.dto.AbonnementRequest;
import com.example.starter.dto.AbonnementResponse;
import java.util.List;

public interface AbonnementService {
    AbonnementResponse souscrireAbonnement(AbonnementRequest requete);
    AbonnementResponse obtenirAbonnementActifParAgence(Long agenceId);
    List<AbonnementResponse> obtenirAbonnementsParAgence(Long agenceId);
    AbonnementResponse validerAbonnementParAdmin(Long id);
    AbonnementResponse annulerAbonnementParAdmin(Long id);
}