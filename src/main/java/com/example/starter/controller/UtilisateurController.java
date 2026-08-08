package com.example.starter.controller;

import com.example.starter.dto.UtilisateurRequest;
import com.example.starter.dto.UtilisateurResponse;
import com.example.starter.service.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService service;

    public UtilisateurController(UtilisateurService service) {
        this.service = service;
    }

    @PostMapping("/inscription")
    @ResponseStatus(HttpStatus.CREATED)
    public UtilisateurResponse inscrire(@Valid @RequestBody UtilisateurRequest request) {
        return service.inscrire(request);
    }

    @GetMapping("/{id}")
    public UtilisateurResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<UtilisateurResponse> list() {
        return service.findAll();
    }

    @PostMapping("/{id}/verifier")
    public void verifier(@PathVariable Long id) {
        service.verifierAgence(id);
    }

    @PostMapping("/{id}/rejeter")
    public void rejeter(@PathVariable Long id) {
        service.rejeterAgence(id);
    }
}