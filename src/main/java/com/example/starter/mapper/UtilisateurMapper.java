package com.example.starter.mapper;

import com.example.starter.dto.UtilisateurRequest;
import com.example.starter.dto.UtilisateurResponse;
import com.example.starter.domaine.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {

    public Utilisateur toEntity(UtilisateurRequest request, String motDePasseHache){
        Utilisateur utilisateur = new Utilisateur(
                request.nom(),
                request.email(),
                motDePasseHache,
                request.role()
        );
        utilisateur.setPrenom(request.prenom());
        utilisateur.setTelephone(request.telephone());
        utilisateur.setNomAgence(request.nomAgence());
        return utilisateur;
    }

    public UtilisateurResponse toResponse(Utilisateur utilisateur){
        return new UtilisateurResponse(
                utilisateur.getId(),
                utilisateur.getNom(),
                utilisateur.getPrenom(),
                utilisateur.getEmail(),
                utilisateur.getTelephone(),
                utilisateur.getRole(),
                utilisateur.getNomAgence(),
                utilisateur.getStatutVerification(),
                utilisateur.getCreatedAt()
        );
    }
}
