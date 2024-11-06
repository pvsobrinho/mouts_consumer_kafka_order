package com.example.motus_order.produto;

import lombok.Data;

@Data
public class ProdutoResponse {
    private String id;
    private String nome;
    private Double preco;
    private Integer quantidade;
}
