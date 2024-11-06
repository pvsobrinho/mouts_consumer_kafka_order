package com.example.motus_order.produto;

import org.springframework.stereotype.Component;

@Component
public class ProdutoConverter {

    public ProdutoResponse toResponse(Produto produto) {
        ProdutoResponse response = new ProdutoResponse();
        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setPreco(produto.getPreco());
        response.setQuantidade(produto.getQuantidade());
        return response;
    }
}
