package com.example.starter.mapper;

import com.example.starter.dto.MessageRequest;
import com.example.starter.dto.MessageResponse;
import com.example.starter.domain.Annonce;
import com.example.starter.domain.Message;
import com.example.starter.domain.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public Message toEntity(MessageRequest request, Utilisateur utilisateur, Annonce annonce){
        return new Message(utilisateur, annonce, request.contenu());
    }

    public MessageResponse toResponse(Message message){
        return new MessageResponse(
                message.getId(),
                message.getContenu(),
                message.getUtilisateur().getNom(),
                message.getAnnonce().getId(),
                message.getStatut(),
                message.getCreatedAt()
        );
    }
}
