package com.example.motus_order.dto;

import lombok.Data;

@Data
public class ProdutoRequest {
    private String id;
    private String nome;
    private Double preco;
    private Integer quantidade;
}
