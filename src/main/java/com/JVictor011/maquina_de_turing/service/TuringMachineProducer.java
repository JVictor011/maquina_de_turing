package com.JVictor011.maquina_de_turing.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TuringMachineProducer {

    private final RabbitTemplate rabbitTemplate;

    public TuringMachineProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTransitionStep(Map<Integer, String> stepDetails) {
        try {
            rabbitTemplate.convertAndSend("turing-exchange", "turing.routing.key", stepDetails);
            System.out.println("Message sent to RabbitMQ: " + stepDetails);
        } catch (Exception e) {
            System.err.println("Error sending message to RabbitMQ: " + e.getMessage());
        }
    }
}