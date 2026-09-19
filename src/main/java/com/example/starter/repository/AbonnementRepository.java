
package com.example.starter.repository;

import com.example.starter.domain.Abonnement;
import com.example.starter.domain.enums.StatutAbonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    List<Abonnement> findByAgenceId(Long agenceId);
    Optional<Abonnement> findFirstByAgenceIdAndStatutOrderByDateFinDesc(Long agenceId, StatutAbonnement statut);
}

