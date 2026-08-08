package com.example.starter.controller;

import com.example.starter.dto.LoginRequest;
import com.example.starter.dto.LoginResponse;
import com.example.starter.securite.JwtService;
import com.example.starter.securite.UtilisateurDetails;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.motDePasse())
        );

        UtilisateurDetails details = (UtilisateurDetails) authentication.getPrincipal();
        String token = jwtService.genererToken(details);

        String role = details.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        return new LoginResponse(token, details.getUsername(), role);
    }
}