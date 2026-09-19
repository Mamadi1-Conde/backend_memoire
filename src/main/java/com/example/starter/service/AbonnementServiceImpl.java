package com.example.starter.service;

import com.example.starter.domain.Abonnement;
import com.example.starter.domain.Agence;
import com.example.starter.domain.enums.StatutAbonnement;
import com.example.starter.dto.AbonnementRequest;
import com.example.starter.dto.AbonnementResponse;
import com.example.starter.repository.AbonnementRepository;
import com.example.starter.repository.AgenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AbonnementServiceImpl implements AbonnementService {

    private final AbonnementRepository abonnementRepository;
    private final AgenceRepository agenceRepository;

    public AbonnementServiceImpl(AbonnementRepository abonnementRepository, AgenceRepository agenceRepository) {
        this.abonnementRepository = abonnementRepository;
        this.agenceRepository = agenceRepository;
    }

    @Override
    public AbonnementResponse souscrireAbonnement(AbonnementRequest requete) {
        Agence agence = agenceRepository.findById(requete.getAgenceId())
                .orElseThrow(() -> new RuntimeException("Agence non trouvée"));

        Abonnement abonnement = new Abonnement();
        abonnement.setFormule(requete.getFormule());
        abonnement.setTarif(requete.getTarif());
        abonnement.setDateDebut(LocalDateTime.now());
        abonnement.setDateFin(LocalDateTime.now().plusMonths(requete.getDureeEnMois()));
        abonnement.setStatut(StatutAbonnement.EN_ATTENTE);
        abonnement.setAgence(agence);

        return mapperVersResponse(abonnementRepository.save(abonnement));
    }

    @Override
    @Transactional(readOnly = true)
    public AbonnementResponse obtenirAbonnementActifParAgence(Long agenceId) {
        Abonnement abonnement = abonnementRepository
                .findFirstByAgenceIdAndStatutOrderByDateFinDesc(agenceId, StatutAbonnement.ACTIF)
                .orElseThrow(() -> new RuntimeException("Aucun abonnement actif trouvé pour cette agence"));
        return mapperVersResponse(abonnement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AbonnementResponse> obtenirAbonnementsParAgence(Long agenceId) {
        return abonnementRepository.findByAgenceId(agenceId).stream()
                .map(this::mapperVersResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AbonnementResponse validerAbonnementParAdmin(Long id) {
        Abonnement abonnement = abonnementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Abonnement non trouvé"));
        abonnement.setStatut(StatutAbonnement.ACTIF);
        return mapperVersResponse(abonnementRepository.save(abonnement));
    }

    @Override
    public AbonnementResponse annulerAbonnementParAdmin(Long id) {
        Abonnement abonnement = abonnementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Abonnement non trouvé"));
        abonnement.setStatut(StatutAbonnement.ANNULE);
        return mapperVersResponse(abonnementRepository.save(abonnement));
    }

    private AbonnementResponse mapperVersResponse(Abonnement abonnement) {
        AbonnementResponse response = new AbonnementResponse();
        response.setId(abonnement.getId());
        response.setFormule(abonnement.getFormule());
        response.setTarif(abonnement.getTarif());
        response.setDateDebut(abonnement.getDateDebut());
        response.setDateFin(abonnement.getDateFin());
        response.setStatut(abonnement.getStatut());
        response.setNomAgence(abonnement.getAgence().getNomAgence());
        return response;
    }
}