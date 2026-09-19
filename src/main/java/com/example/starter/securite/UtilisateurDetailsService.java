package com.example.starter.securite;

import com.example.starter.domain.Utilisateur;
import com.example.starter.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

    private final UtilisateurRepository repository;

    public UtilisateurDetailsService(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Utilisateur utilisateur = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Aucun utilisateur pour cet email"));
        return new UtilisateurDetails(utilisateur);
    }
}