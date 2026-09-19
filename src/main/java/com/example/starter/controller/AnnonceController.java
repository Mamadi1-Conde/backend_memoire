package com.example.starter.controller;

import com.example.starter.domain.Annonce;
import com.example.starter.dto.AnnonceRequest;
import com.example.starter.dto.AnnonceResponse;
import com.example.starter.service.AnnonceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annonces")
public class AnnonceController {

    private final AnnonceService service;

    public AnnonceController(AnnonceService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnnonceResponse publier(@RequestParam Long idAgence,
                                   @Valid @RequestBody AnnonceRequest request) {
        return service.publier(idAgence, request);
    }

    @GetMapping
    public List<AnnonceResponse> rechercher() {
        return service.findPubliees();
    }

    @GetMapping("/agence/{idAgence}")
    public List<AnnonceResponse> mesAnnonces(@PathVariable Long idAgence) {
        return service.findByAgence(idAgence);
    }

    @GetMapping("/{id}")
    public AnnonceResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/{id}/moderer")
    public void moderer(@PathVariable Long id, @RequestParam Annonce.Statut decision) {
        service.moderer(id, decision);
    }
}