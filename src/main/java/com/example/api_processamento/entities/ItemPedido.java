package com.example.api_processamento.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class ItemPedido {

    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne()
    private Produto produto;

    private Integer quantidade;

    @JsonProperty(access = Access.READ_ONLY)
    private Double subTotal;

    @ManyToOne()
    private Pedido pedido;

    public ItemPedido(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        calcularSubTotal();
    }

    private void calcularSubTotal() {
        if (produto != null && quantidade != null) {
            this.subTotal = produto.getValor() * getQuantidade();
        }
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        calcularSubTotal();
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        calcularSubTotal();
    }

}
