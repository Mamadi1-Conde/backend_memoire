package com.example.starter.mapper;

import com.example.starter.dto.BienRequest;
import com.example.starter.dto.BienResponse;
import com.example.starter.domaine.Bien;
import org.springframework.stereotype.Component;

@Component
public class BienMapper {
    public Bien toEntity(BienRequest request){
        Bien bien = new Bien(request.typeBien(), request.adresse(), request.ville());
        bien.setSurface(request.surface());
        bien.setNombrePieces(request.nombrePieces());
        bien.setZone(request.zone());
        return bien;
    }

    public BienResponse toResponse(Bien bien){
        return new BienResponse(
                bien.getId(),
                bien.getTypeBien(),
                bien.getSurface(),
                bien.getNombrePieces(),
                bien.getAdresse(),
                bien.getZone(),
                bien.getVille()
        );
    }
}
