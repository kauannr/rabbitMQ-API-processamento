package com.example.api_processamento.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_processamento.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    
}
