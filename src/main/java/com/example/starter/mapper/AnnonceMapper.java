package com.example.starter.mapper;

import com.example.starter.dto.AnnonceRequest;
import com.example.starter.dto.AnnonceResponse;
import com.example.starter.domaine.Annonce;
import com.example.starter.domaine.Bien;
import com.example.starter.domaine.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class AnnonceMapper {
    private final BienMapper bienMapper;

    public AnnonceMapper(BienMapper bienMapper){
        this.bienMapper = bienMapper;
    }

    public Annonce toEntity(AnnonceRequest request, Utilisateur utilisateur, Bien bien) {
        Annonce annonce = new Annonce(utilisateur, bien, request.titre(), request.prix());
        annonce.setDescription(request.description());
        return annonce;
    }

    public AnnonceResponse toResponse(Annonce annonce){
        return new AnnonceResponse(
                annonce.getId(),
                annonce.getTitre(),
                annonce.getDescription(),
                annonce.getPrix(),
                annonce.getStatut(),
                annonce.getDatePublication(),
                bienMapper.toResponse(annonce.getBien()),
                annonce.getUtilisateur().getNomAgence()
        );
    }
}
