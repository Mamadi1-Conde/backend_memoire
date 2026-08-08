package com.example.starter.service;

import com.example.starter.dto.MessageRequest;
import com.example.starter.dto.MessageResponse;

import java.util.List;

public interface MessageService {
    MessageResponse envoyer(Long idExpediteur, MessageRequest request);
    List<MessageResponse> findByAnnonce(Long idAnnonce);
    void marquerCommeLu(Long id);
}