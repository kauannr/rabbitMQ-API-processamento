package com.example.api_processamento.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.api_processamento.entities.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Pedido {

    @Id
    private UUID id = UUID.randomUUID();
    private String cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensDoPedido = new ArrayList<>();
    
    private String emailParaNotificar;

    private Double valorTotal = 0.0;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime instantePedido = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido = StatusPedido.EM_PROCESSAMENTO;

    public Pedido(String cliente, List<ItemPedido> itensDoPedido, String emailParaNotificar) {
        this.cliente = cliente;
        this.itensDoPedido = itensDoPedido;
        this.emailParaNotificar = emailParaNotificar;
    }

    public void calcularValorTotal() {
        this.valorTotal = itensDoPedido.stream().mapToDouble(ItemPedido::getSubTotal).sum();
    }

}
