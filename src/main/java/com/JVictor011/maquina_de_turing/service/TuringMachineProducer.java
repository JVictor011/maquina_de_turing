package com.JVictor011.maquina_de_turing.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TuringMachineProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TuringMachineProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Async
    public void sendTransitionStep(Map<Integer, String> stepDetails) {
        try {
            rabbitTemplate.convertAndSend("turing-exchange", "turing.routing.key", stepDetails);
            System.out.println("Mensagem enviada para RabbitMQ: " + stepDetails);
        } catch (Exception e) {
            System.err.println("Erro ao enviar mensagem para RabbitMQ: " + e.getMessage());
        }
    }
}