package com.example.starter.controller;

import com.example.starter.dto.AvisRequest;
import com.example.starter.dto.AvisResponse;
import com.example.starter.service.AvisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
public class AvisController {

    private final AvisService avisService;

    public AvisController(AvisService avisService) {
        this.avisService = avisService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AvisResponse> ajouterAvis(@RequestBody AvisRequest requete,
                                                    @AuthenticationPrincipal UserDetails userDetails) {
        AvisResponse reponse = avisService.ajouterAvis(requete, userDetails.getUsername());
        return new ResponseEntity<>(reponse, HttpStatus.CREATED);
    }

    @GetMapping("/annonce/{annonceId}")
    public ResponseEntity<List<AvisResponse>> obtenirAvisParAnnonce(@PathVariable Long annonceId) {
        return ResponseEntity.ok(avisService.obtenirAvisParAnnonce(annonceId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> supprimerAvis(@PathVariable Long id,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        avisService.supprimerAvis(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }
}