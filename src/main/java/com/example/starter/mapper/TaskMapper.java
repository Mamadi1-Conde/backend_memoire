package com.example.starter.mapper;

import com.example.starter.domain.Task;
import com.example.starter.dto.*;
import org.springframework.stereotype.Component;

/** SRP : cette classe ne fait que convertir domaine et contrats HTTP. */
@Component
public class TaskMapper {
    public Task toEntity(TaskRequest request) {
        return new Task(request.title(), request.completed());
    }

    public TaskResponse toResponse(Task task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.isCompleted(),
                task.getCreatedAt(), task.getUpdatedAt());
    }
}
