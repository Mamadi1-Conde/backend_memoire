package com.example.starter.service;

import com.example.starter.domaine.Annonce;
import com.example.starter.dto.AnnonceRequest;
import com.example.starter.dto.AnnonceResponse;

import java.util.List;

public interface AnnonceService {
    AnnonceResponse publier(Long idAgence, AnnonceRequest request);
    List<AnnonceResponse> findPubliees();
    List<AnnonceResponse> findByAgence(Long idAgence);
    AnnonceResponse findById(Long id);
    void moderer(Long id, Annonce.Statut decision);
}