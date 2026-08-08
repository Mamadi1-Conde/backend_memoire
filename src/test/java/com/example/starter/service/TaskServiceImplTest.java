package com.example.starter.service;

import com.example.starter.domain.Task;
import com.example.starter.dto.TaskRequest;
import com.example.starter.mapper.TaskMapper;
import com.example.starter.repository.TaskRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Test unitaire rapide : aucun contexte Spring ni base de données.
 * Le repository est remplacé par un mock pour isoler le service.
 */
@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @Mock
    TaskRepository repository;
    private TaskServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new TaskServiceImpl(repository, new TaskMapper());
    }

    @Test
    void shouldUpdateExistingTask() {
        Task task = new Task("Avant", false);
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        var response = service.update(1L, new TaskRequest("Après", true));

        assertThat(response.title()).isEqualTo("Après");
        assertThat(response.completed()).isTrue();
        verify(repository).findById(1L);
    }
}
