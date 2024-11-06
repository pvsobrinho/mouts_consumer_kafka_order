package com.example.motus_order.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoRequest saveProduct(ProdutoRequest produtoRequest) {
        // Verifica se o produto já existe no banco de dados pelo ID ou outro campo único
        if (produtoRepository.existsById(produtoRequest.getId())) {
            System.out.println("Produto " + produtoRequest.getId() + " já existe na base de dados, não será salvo novamente.");
            return produtoRequest;
        }

        // Converte ProdutoRequest em Produto antes de salvar
        Produto produto = new Produto();
        produto.setId(produtoRequest.getId());
        produto.setNome(produtoRequest.getNome());
        produto.setPreco(produtoRequest.getPreco());
        produto.setQuantidade(produtoRequest.getQuantidade());

        // Salva o produto na base de dados
        produtoRepository.save(produto);

        return produtoRequest;
    }
}
