package com.JVictor011.maquina_de_turing.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue turingMachineQueue() {
        return new Queue("turing-machine-queue", true, false, false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("turing-exchange", true, false); // true = durável
    }

    @Bean
    public Binding binding(Queue turingMachineQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(turingMachineQueue).to(directExchange).with("turing.routing.key");
    }
}
