package com.example.starter.service;

import com.example.starter.domaine.Utilisateur;
import com.example.starter.dto.UtilisateurRequest;
import com.example.starter.dto.UtilisateurResponse;
import com.example.starter.exception.ResourceNotFoundException;
import com.example.starter.mapper.UtilisateurMapper;
import com.example.starter.repository.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository repository;
    private final UtilisateurMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurRepository repository,
                                  UtilisateurMapper mapper,
                                  PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UtilisateurResponse inscrire(UtilisateurRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Cet email est deja utilise");
        }
        if (request.role() == Utilisateur.Role.AGENCE
                && (request.nomAgence() == null || request.nomAgence().isBlank())) {
            throw new IllegalArgumentException("Le nom de l'agence est obligatoire pour ce role");
        }
        String motDePasseHache = passwordEncoder.encode(request.motDePasse());
        Utilisateur utilisateur = mapper.toEntity(request, motDePasseHache);
        return mapper.toResponse(repository.save(utilisateur));
    }

    @Override
    @Transactional(readOnly = true)
    public UtilisateurResponse findById(Long id) {
        return mapper.toResponse(findEntity(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UtilisateurResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public void verifierAgence(Long id) {
        findEntity(id).verifierAgence();
    }

    @Override
    public void rejeterAgence(Long id) {
        findEntity(id).rejeterAgence();
    }

    private Utilisateur findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur " + id + " introuvable"));
    }
}