package com.example.starter.controller;

import com.example.starter.dto.AgenceRequest;
import com.example.starter.dto.AgenceResponse;
import com.example.starter.service.AgenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
public class AgenceController {

    private final AgenceService agenceService;

    public AgenceController(AgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('AGENCE', 'ADMIN')")
    public ResponseEntity<AgenceResponse> creerAgence(@RequestBody AgenceRequest requete,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        AgenceResponse reponse = agenceService.creeraAgence(requete, userDetails.getUsername());
        return new ResponseEntity<>(reponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgenceResponse> obtenirAgenceParId(@PathVariable Long id) {
        return ResponseEntity.ok(agenceService.obtenirAgenceParId(id));
    }

    @GetMapping
    public ResponseEntity<List<AgenceResponse>> obtenirToutesLesAgences() {
        return ResponseEntity.ok(agenceService.obtenirToutesLesAgences());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('AGENCE', 'ADMIN')")
    public ResponseEntity<AgenceResponse> modifierAgence(@PathVariable Long id,
                                                         @RequestBody AgenceRequest requete,
                                                         @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(agenceService.modifierAgence(id, requete, userDetails.getUsername()));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimerAgence(@PathVariable Long id) {
        agenceService.supprimerAgence(id);
        return ResponseEntity.noContent().build();
    }
}