package com.example.api_processamento.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api_processamento.entities.ItemPedido;
import com.example.api_processamento.entities.Pedido;
import com.example.api_processamento.repositories.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    private final Logger logger = LoggerFactory.getLogger(Pedido.class);

    public void save(Pedido pedido) {

        try {
            // salvando os produtos
            produtoService.save(pedido.getItensDoPedido());

            // salvando os itens
            List<ItemPedido> listItens = itemPedidoService.save(pedido.getItensDoPedido());

            // salvando pedido
            pedidoRepository.save(pedido);

            // atualiza os itens para o pedido que pertence
            itemPedidoService.updatedItemPedido(listItens, pedido);

            logger.info("pedido salvo {}", pedido.toString());
            System.out.println("-------------");
            // System.out.println("teste: " + novaLista.get(0));
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

}
