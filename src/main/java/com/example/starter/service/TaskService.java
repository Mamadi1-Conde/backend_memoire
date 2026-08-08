package com.example.starter.service;

import com.example.starter.dto.*;
import java.util.List;

/**
 * Contrat des cas d'utilisation.
 * DIP : le contrôleur dépend de cette abstraction, pas de l'implémentation.
 */
public interface TaskService {
    List<TaskResponse> findAll();
    TaskResponse findById(Long id);
    TaskResponse create(TaskRequest request);
    TaskResponse update(Long id, TaskRequest request);
    void delete(Long id);
}
