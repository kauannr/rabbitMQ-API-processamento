package com.example.api_processamento.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.dlx.name}")
    private String exchangeDlxName;

    @Value("${rabbitmq.queue.dlq.name}")
    private String queueDlqName;

    @Bean
    public FanoutExchange pedidosExchange() {
        return new FanoutExchange(exchangeName);
    }

    @Bean
    public FanoutExchange pedidosDlxExchange() {
        return new FanoutExchange(exchangeDlxName);
    }

    @Bean
    public Queue queueDeProcessamento() {
        Map<String, Object> argumentos = new HashMap<>();
        argumentos.put("x-dead-letter-exchange", exchangeDlxName);

        return new Queue(queueName, true, false, false, argumentos);
    }

    @Bean
    public Queue queueDlqDeProcessamento() {
        return new Queue(queueDlqName);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueDeProcessamento()).to(pedidosExchange());
    }

    @Bean
    public Binding bindingDlq() {
        return BindingBuilder.bind(queueDlqDeProcessamento()).to(pedidosDlxExchange());
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);

        return rabbitTemplate;
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationListener(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

}
