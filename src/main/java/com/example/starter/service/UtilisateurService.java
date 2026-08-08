package com.example.starter.service;

import com.example.starter.dto.UtilisateurRequest;
import com.example.starter.dto.UtilisateurResponse;

import java.util.List;

public interface UtilisateurService {
    UtilisateurResponse inscrire(UtilisateurRequest request);
    UtilisateurResponse findById(Long id);
    List<UtilisateurResponse> findAll();
    void verifierAgence(Long id);
    void rejeterAgence(Long id);
}
