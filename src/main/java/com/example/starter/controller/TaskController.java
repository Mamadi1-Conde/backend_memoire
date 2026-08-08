package com.example.starter.controller;

import com.example.starter.dto.*;
import com.example.starter.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Adaptateur HTTP mince : validation, codes HTTP et délégation.
 * Il ne contient ni règle métier, ni accès au repository (SRP).
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;

    // Dépendance vers l'interface TaskService : exemple du principe DIP.
    public TaskController(TaskService service) { this.service = service; }
    @GetMapping public List<TaskResponse> list() { return service.findAll(); }
    @GetMapping("/{id}") public TaskResponse get(@PathVariable Long id) { return service.findById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public TaskResponse create(@Valid @RequestBody TaskRequest request) { return service.create(request); }
    @PutMapping("/{id}") public TaskResponse update(@PathVariable Long id, @Valid @RequestBody TaskRequest request) { return service.update(id, request); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}
