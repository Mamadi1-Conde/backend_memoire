package com.example.starter.repository;

import com.example.starter.domaine.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByAnnonceId(Long annonceId);
}
