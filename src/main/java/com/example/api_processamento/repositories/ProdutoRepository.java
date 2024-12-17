package com.example.api_processamento.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_processamento.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    
}
