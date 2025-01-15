package com.JVictor011.maquina_de_turing.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    private final SimpMessagingTemplate messagingTemplate;

    public MessagingService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendStepToFrontend(String message) {
        messagingTemplate.convertAndSend("/topic/turing-machine", message);
    }
}