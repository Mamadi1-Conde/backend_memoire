package com.example.starter.service;

import com.example.starter.domain.Abonnement;
import com.example.starter.domain.Paiement;
import com.example.starter.domain.Utilisateur;
import com.example.starter.domain.enums.StatutPaiement;
import com.example.starter.dto.PaiementRequest;
import com.example.starter.dto.PaiementResponse;
import com.example.starter.repository.AbonnementRepository;
import com.example.starter.repository.PaiementRepository;
import com.example.starter.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepository paiementRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final AbonnementRepository abonnementRepository;

    public PaiementServiceImpl(PaiementRepository paiementRepository, UtilisateurRepository utilisateurRepository, AbonnementRepository abonnementRepository) {
        this.paiementRepository = paiementRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.abonnementRepository = abonnementRepository;
    }

    @Override
    public PaiementResponse effectuerPaiement(PaiementRequest requete, String emailPayeur) {
        Utilisateur payeur = utilisateurRepository.findByEmail(emailPayeur)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Paiement paiement = new Paiement();
        paiement.setMontant(requete.getMontant());
        paiement.setMoyenPaiement(requete.getMoyenPaiement());
        paiement.setReferenceTransaction("TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        paiement.setStatut(StatutPaiement.VALIDE);
        paiement.setPayeur(payeur);

        if (requete.getAbonnementId() != null) {
            Abonnement abonnement = abonnementRepository.findById(requete.getAbonnementId())
                    .orElseThrow(() -> new RuntimeException("Abonnement non trouvé"));
            paiement.setAbonnement(abonnement);
        }

        return mapperVersResponse(paiementRepository.save(paiement));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementResponse> obtenirPaiementsParUtilisateur(String emailPayeur) {
        Utilisateur payeur = utilisateurRepository.findByEmail(emailPayeur)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return paiementRepository.findByPayeurId(payeur.getId()).stream()
                .map(this::mapperVersResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementResponse> obtenirTousLesPaiements() {
        return paiementRepository.findAll().stream()
                .map(this::mapperVersResponse)
                .collect(Collectors.toList());
    }

    private PaiementResponse mapperVersResponse(Paiement paiement) {
        PaiementResponse response = new PaiementResponse();
        response.setId(paiement.getId());
        response.setMontant(paiement.getMontant());
        response.setMoyenPaiement(paiement.getMoyenPaiement());
        response.setReferenceTransaction(paiement.getReferenceTransaction());
        response.setStatut(paiement.getStatut());
        response.setDatePaiement(paiement.getDatePaiement());
        response.setEmailPayeur(paiement.getPayeur().getEmail());
        return response;
    }
}