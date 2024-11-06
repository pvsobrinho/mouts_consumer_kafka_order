package com.example.motus_order.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoConverter produtoConverter;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, ProdutoConverter produtoConverter) {
        this.produtoRepository = produtoRepository;
        this.produtoConverter = produtoConverter;
    }

    public ProdutoResponse buscarProdutoPorId(String id) {
        return produtoRepository.findById(id)
                .map(produtoConverter::toResponse)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID fornecido."));
    }

    public List<ProdutoResponse> buscarProdutosPorNome(String nome) {
        List<Produto> produtos = produtoRepository.findByNomeLike("%" + nome + "%");
        if (produtos.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado com o nome fornecido.");
        }
        return produtos.stream()
                .map(produtoConverter::toResponse)
                .collect(Collectors.toList());
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
        System.out.println("Produto " + produtoRequest.getId() + " foi gravado na base de dados na nuvem Azure.");

        return produtoRequest;
    }
}
