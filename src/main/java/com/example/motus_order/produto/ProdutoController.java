package com.example.motus_order.produto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "Endpoints relacionados a produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Produto por ID", description = "Retorna um produto com base no ID fornecido.")
    public ResponseEntity<ProdutoResponse> buscarProdutoPorId(@PathVariable @NotNull String id) {
        ProdutoResponse produto = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/nome")
    @Operation(summary = "Buscar Produtos por Nome", description = "Retorna uma lista de produtos com base no nome fornecido.")
    public ResponseEntity<List<ProdutoResponse>> buscarProdutosPorNome(@RequestParam @NotEmpty String nome) {
        List<ProdutoResponse> produtos = produtoService.buscarProdutosPorNome(nome);
        return ResponseEntity.ok(produtos);
    }
}
