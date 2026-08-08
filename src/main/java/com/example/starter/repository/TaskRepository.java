package com.example.starter.repository;

import com.example.starter.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/** Port d'accès aux données ; Spring Data fournit l'implémentation. */
public interface TaskRepository extends JpaRepository<Task, Long> {}
