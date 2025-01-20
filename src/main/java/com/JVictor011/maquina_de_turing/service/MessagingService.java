package com.JVictor011.maquina_de_turing.service;

import com.JVictor011.maquina_de_turing.dto.TuringStepDetails;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    private final SimpMessagingTemplate messagingTemplate;
    private final RabbitTemplate rabbitTemplate;

    public MessagingService(SimpMessagingTemplate messagingTemplate, RabbitTemplate rabbitTemplate) {
        this.messagingTemplate = messagingTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendStepToFrontend(TuringStepDetails stepDetails) {
        if (stepDetails == null) {
            stepDetails = new TuringStepDetails(0, "");
        }
        messagingTemplate.convertAndSend("/topic/steps", stepDetails);
    }

    public void sendStepToQueue(TuringStepDetails stepDetails) {
        if (stepDetails == null) {
            System.out.println("StepDetails is empty or null, nothing will be sent to the queue");
            return;
        }
        try {
            rabbitTemplate.convertAndSend("turing-exchange", "turing.routing.key", stepDetails);
            System.out.println("Message successfully sent to the queue");
        } catch (Exception e) {
            System.err.println("Error sending message to the queue: " + e.getMessage());
        }
    }
}
