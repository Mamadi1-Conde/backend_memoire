package com.example.starter.repository;

import com.example.starter.domain.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByPayeurId(Long utilisateurId);
}
