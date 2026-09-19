package com.example.starter.service;

import com.example.starter.domain.Annonce;
import com.example.starter.domain.Message;
import com.example.starter.domain.Utilisateur;
import com.example.starter.dto.MessageRequest;
import com.example.starter.dto.MessageResponse;
import com.example.starter.exception.ResourceNotFoundException;
import com.example.starter.mapper.MessageMapper;
import com.example.starter.repository.AnnonceRepository;
import com.example.starter.repository.MessageRepository;
import com.example.starter.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final AnnonceRepository annonceRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final MessageMapper mapper;

    public MessageServiceImpl(MessageRepository messageRepository,
                              AnnonceRepository annonceRepository,
                              UtilisateurRepository utilisateurRepository,
                              MessageMapper mapper) {
        this.messageRepository = messageRepository;
        this.annonceRepository = annonceRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.mapper = mapper;
    }

    @Override
    public MessageResponse envoyer(Long idExpediteur, MessageRequest request) {
        Utilisateur expediteur = utilisateurRepository.findById(idExpediteur)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur " + idExpediteur + " introuvable"));
        Annonce annonce = annonceRepository.findById(request.idAnnonce())
                .orElseThrow(() -> new ResourceNotFoundException("Annonce " + request.idAnnonce() + " introuvable"));

        Message message = mapper.toEntity(request, expediteur, annonce);
        return mapper.toResponse(messageRepository.save(message));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageResponse> findByAnnonce(Long idAnnonce) {
        return messageRepository.findByAnnonceId(idAnnonce)
                .stream().map(mapper::toResponse).toList();
    }

    @Override
    public void marquerCommeLu(Long id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message " + id + " introuvable"));
        message.marquerCommeLu();
    }
}