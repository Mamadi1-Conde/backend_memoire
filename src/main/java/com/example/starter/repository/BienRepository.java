package com.example.starter.repository;

import com.example.starter.domaine.Bien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BienRepository extends JpaRepository<Bien, Long> {

}
