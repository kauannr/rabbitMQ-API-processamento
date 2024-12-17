package com.example.api_processamento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api_processamento.entities.ItemPedido;
import com.example.api_processamento.entities.Pedido;
import com.example.api_processamento.repositories.ItemPedidoRepository;

import jakarta.transaction.TransactionScoped;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public ItemPedido save(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public List<ItemPedido> save(List<ItemPedido> itensDoPedido) {

        return itemPedidoRepository.saveAll(itensDoPedido);

    }

    @TransactionScoped
    public void updatedItemPedido(List<ItemPedido> itens, Pedido pedido) {
        itens.forEach(item -> {
            item.setPedido(pedido);
            this.save(item);
        });
    }

}
