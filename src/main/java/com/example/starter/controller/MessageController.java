package com.example.starter.controller;

import com.example.starter.dto.MessageRequest;
import com.example.starter.dto.MessageResponse;
import com.example.starter.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse envoyer(@RequestParam Long idExpediteur,
                                   @Valid @RequestBody MessageRequest request) {
        return service.envoyer(idExpediteur, request);
    }

    @GetMapping("/annonce/{idAnnonce}")
    public List<MessageResponse> parAnnonce(@PathVariable Long idAnnonce) {
        return service.findByAnnonce(idAnnonce);
    }

    @PostMapping("/{id}/lu")
    public void marquerLu(@PathVariable Long id) {
        service.marquerCommeLu(id);
    }
}