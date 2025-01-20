//package com.JVictor011.maquina_de_turing.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitConfig {
//
//    @Bean
//    public Queue turingMachineQueue() {
//        return new Queue("turing-machine-queue", true, false, false);
//    }
//
//    @Bean
//    public DirectExchange directExchange() {
//        return new DirectExchange("turing-exchange", true, false); // true = durable
//    }
//
//    @Bean
//    public Binding binding(Queue turingMachineQueue, DirectExchange directExchange) {
//        return BindingBuilder.bind(turingMachineQueue).to(directExchange).with("turing.routing.key");
//    }
//
//    @Bean
//    public Jackson2JsonMessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
//                                                                            Jackson2JsonMessageConverter jsonMessageConverter) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(jsonMessageConverter);
//        return factory;
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(jsonMessageConverter());
//        return rabbitTemplate;
//    }
//}
