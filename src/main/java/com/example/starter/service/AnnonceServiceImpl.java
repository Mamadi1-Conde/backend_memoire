package com.example.starter.service;

import com.example.starter.domaine.Annonce;
import com.example.starter.domaine.Bien;
import com.example.starter.domaine.Utilisateur;
import com.example.starter.dto.AnnonceRequest;
import com.example.starter.dto.AnnonceResponse;
import com.example.starter.exception.ResourceNotFoundException;
import com.example.starter.mapper.AnnonceMapper;
import com.example.starter.mapper.BienMapper;
import com.example.starter.repository.AnnonceRepository;
import com.example.starter.repository.BienRepository;
import com.example.starter.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnnonceServiceImpl implements AnnonceService {

    private final AnnonceRepository annonceRepository;
    private final BienRepository bienRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final AnnonceMapper annonceMapper;
    private final BienMapper bienMapper;

    public AnnonceServiceImpl(AnnonceRepository annonceRepository,
                              BienRepository bienRepository,
                              UtilisateurRepository utilisateurRepository,
                              AnnonceMapper annonceMapper,
                              BienMapper bienMapper) {
        this.annonceRepository = annonceRepository;
        this.bienRepository = bienRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.annonceMapper = annonceMapper;
        this.bienMapper = bienMapper;
    }

    @Override
    public AnnonceResponse publier(Long idAgence, AnnonceRequest request) {
        Utilisateur agence = utilisateurRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur " + idAgence + " introuvable"));

        Bien bien = bienMapper.toEntity(request.bien());
        bien = bienRepository.save(bien);

        Annonce annonce = annonceMapper.toEntity(request, agence, bien);
        return annonceMapper.toResponse(annonceRepository.save(annonce));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnnonceResponse> findPubliees() {
        return annonceRepository.findByStatut(Annonce.Statut.PUBLIEE)
                .stream().map(annonceMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnnonceResponse> findByAgence(Long idAgence) {
        return annonceRepository.findByUtilisateurId(idAgence)
                .stream().map(annonceMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AnnonceResponse findById(Long id) {
        return annonceMapper.toResponse(findEntity(id));
    }

    @Override
    public void moderer(Long id, Annonce.Statut decision) {
        Annonce annonce = findEntity(id);
        switch (decision) {
            case PUBLIEE -> annonce.publier();
            case REJETEE -> annonce.rejeter();
            case ARCHIVEE -> annonce.archiver();
            default -> throw new IllegalArgumentException("Decision de moderation invalide");
        }
    }

    private Annonce findEntity(Long id) {
        return annonceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annonce " + id + " introuvable"));
    }
}