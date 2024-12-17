package com.example.api_processamento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api_processamento.entities.ItemPedido;
import com.example.api_processamento.entities.Produto;
import com.example.api_processamento.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void save(List<ItemPedido> itens) {

        for (ItemPedido itemPedido : itens) {
            produtoRepository.save(itemPedido.getProduto());
        }
        
    }

    public boolean isTransient(Produto produto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isTransient'");
    }
}
