package com.example.api_processamento.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.api_processamento.entities.Pedido;
import com.example.api_processamento.services.PedidoService;

@Component
public class PedidoListener {

    @Autowired
    PedidoService pedidoService;

    @RabbitListener(queues = "pedidos.v1.pedido-criado-gerar-processamento")
    public void ouvirEEnviarNotificacao(Pedido pedido) {

         // Simula o processamento do pedido
         System.out.println("Processando pedido: " + pedido);


        pedidoService.save(pedido);

    }

}
