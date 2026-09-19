package com.example.starter.controller;

import com.example.starter.dto.PaiementRequest;
import com.example.starter.dto.PaiementResponse;
import com.example.starter.service.PaiementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    private final PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PaiementResponse> effectuerPaiement(@RequestBody PaiementRequest requete,
                                                              @AuthenticationPrincipal UserDetails userDetails) {
        PaiementResponse reponse = paiementService.effectuerPaiement(requete, userDetails.getUsername());
        return new ResponseEntity<>(reponse, HttpStatus.CREATED);
    }

    @GetMapping("/mes-paiements")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<PaiementResponse>> obtenirMesPaiements(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(paiementService.obtenirPaiementsParUtilisateur(userDetails.getUsername()));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PaiementResponse>> obtenirTousLesPaiements() {
        return ResponseEntity.ok(paiementService.obtenirTousLesPaiements());
    }
}