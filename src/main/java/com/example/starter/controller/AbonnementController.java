package com.example.starter.controller;

import com.example.starter.dto.AbonnementRequest;
import com.example.starter.dto.AbonnementResponse;
import com.example.starter.service.AbonnementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abonnements")
public class AbonnementController {

    private final AbonnementService abonnementService;

    public AbonnementController(AbonnementService abonnementService) {
        this.abonnementService = abonnementService;
    }

    @PostMapping("/souscrire")
    @PreAuthorize("hasAnyRole('AGENCE', 'ADMIN')")
    public ResponseEntity<AbonnementResponse> souscrireAbonnement(@RequestBody AbonnementRequest requete) {
        AbonnementResponse reponse = abonnementService.souscrireAbonnement(requete);
        return new ResponseEntity<>(reponse, HttpStatus.CREATED);
    }

    @GetMapping("/agence/{agenceId}/actif")
    @PreAuthorize("hasAnyRole('AGENCE', 'ADMIN')")
    public ResponseEntity<AbonnementResponse> obtenirAbonnementActif(@PathVariable Long agenceId) {
        return ResponseEntity.ok(abonnementService.obtenirAbonnementActifParAgence(agenceId));
    }

    @GetMapping("/agence/{agenceId}")
    @PreAuthorize("hasAnyRole('AGENCE', 'ADMIN')")
    public ResponseEntity<List<AbonnementResponse>> obtenirAbonnementsParAgence(@PathVariable Long agenceId) {
        return ResponseEntity.ok(abonnementService.obtenirAbonnementsParAgence(agenceId));
    }

    @PutMapping("/{id}/valider")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AbonnementResponse> validerAbonnement(@PathVariable Long id) {
        return ResponseEntity.ok(abonnementService.validerAbonnementParAdmin(id));
    }

    @PutMapping("/{id}/annuler")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AbonnementResponse> annulerAbonnement(@PathVariable Long id) {
        return ResponseEntity.ok(abonnementService.annulerAbonnementParAdmin(id));
    }
}