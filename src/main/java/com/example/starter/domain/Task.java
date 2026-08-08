package com.example.starter.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String title;
    private boolean completed;

    protected Task() {}
    public Task(String title, boolean completed) {
        rename(title);
        this.completed = completed;
    }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    /** Le domaine protège ses invariants, même hors d'un appel HTTP validé. */
    public void rename(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Le titre est obligatoire");
        }
        this.title = title.trim();
    }
    public boolean isCompleted() { return completed; }
    public void changeCompletion(boolean completed) { this.completed = completed; }
}
