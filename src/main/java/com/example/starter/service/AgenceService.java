package com.example.starter.service;

import com.example.starter.dto.AgenceRequest;
import com.example.starter.dto.AgenceResponse;
import java.util.List;

public interface AgenceService {
    AgenceResponse creeraAgence(AgenceRequest requete, String emailProprietaire);
    AgenceResponse obtenirAgenceParId(Long id);
    List<AgenceResponse> obtenirToutesLesAgences();
    AgenceResponse modifierAgence(Long id, AgenceRequest requete, String emailProprietaire);
    void supprimerAgence(Long id);
}