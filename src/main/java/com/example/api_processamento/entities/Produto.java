package com.example.api_processamento.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Produto {

    @Id
    private UUID id = UUID.randomUUID();
    private String nome;
    private Double valor;

    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> items = new ArrayList<>();

}
