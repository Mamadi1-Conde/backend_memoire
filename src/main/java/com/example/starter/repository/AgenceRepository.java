// AgenceRepository.java
package com.example.starter.repository;

import com.example.starter.domain.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AgenceRepository extends JpaRepository<Agence, Long> {
    Optional<Agence> findByProprietaireId(Long utilisateurId);
}
