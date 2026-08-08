package com.example.starter.dto;

import com.example.starter.domaine.Utilisateur;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UtilisateurRequest(
        @NotBlank(message = "Le nom est obligatoire")
        String nom,
        String prenom,

        @NotBlank(message = "L'email est obligatoire")
        @Email(message = "Format d'email invalide")
        String email,

        @NotBlank(message = "Le mot de passe est obligatoire")
        @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caracters")
        String motDePasse,

        String telephone,

        @NotNull(message = "Le role est obligatoire")
        Utilisateur.Role role,

        String nomAgence
)
{
}
