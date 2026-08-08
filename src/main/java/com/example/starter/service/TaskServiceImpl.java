package com.example.starter.service;

import com.example.starter.domain.Task;
import com.example.starter.dto.*;
import com.example.starter.exception.ResourceNotFoundException;
import com.example.starter.mapper.TaskMapper;
import com.example.starter.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Implémentation des cas d'utilisation. Les transactions vivent ici :
 * une opération métier reste atomique même avec plusieurs repositories.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;

    // Injection constructeur : dépendances obligatoires, immuables et testables.
    public TaskServiceImpl(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse findById(Long id) {
        return mapper.toResponse(findEntity(id));
    }

    @Override
    public TaskResponse create(TaskRequest request) {
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    public TaskResponse update(Long id, TaskRequest request) {
        Task task = findEntity(id);
        task.rename(request.title());
        task.changeCompletion(request.completed());
        return mapper.toResponse(task);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findEntity(id));
    }

    private Task findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tâche " + id + " introuvable"));
    }
}
