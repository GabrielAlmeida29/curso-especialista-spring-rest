package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;

    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCriacao;

    @CreationTimestamp
    @Column(columnDefinition = "datetime")
    private LocalDateTime dataConfirmacao;

    @CreationTimestamp
    @Column(columnDefinition = "datetime")
    private LocalDateTime dataCancelamento;

    @CreationTimestamp
    @Column(columnDefinition = "datetime")
    private LocalDateTime dataEntrega;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedido = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @JsonIgnore
    @Embedded
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

}
