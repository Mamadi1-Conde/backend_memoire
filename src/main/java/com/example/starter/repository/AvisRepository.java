// AvisRepository.java
package com.example.starter.repository;

import com.example.starter.domain.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvisRepository extends JpaRepository<Avis, Long> {
    List<Avis> findByAnnonceId(Long annonceId);
}


