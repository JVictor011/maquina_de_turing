package com.JVictor011.maquina_de_turing.service;

import com.JVictor011.maquina_de_turing.dto.TuringStepDetails;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendToClient(TuringStepDetails stepDetails) {
        messagingTemplate.convertAndSend("/topic/turingStep", stepDetails); // Rota do WebSocket que o frontend está ouvindo
    }
}
