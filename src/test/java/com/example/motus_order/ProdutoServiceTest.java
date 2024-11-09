package com.example.motus_order;


import com.example.motus_order.produto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoConverter produtoConverter;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarProdutoPorId_ProdutoExiste() {
        String produtoId = "123";
        Produto produto = new Produto();
        produto.setId(produtoId);

        ProdutoResponse produtoResponse = new ProdutoResponse();
        produtoResponse.setId(produtoId);

        when(produtoRepository.findById(produtoId)).thenReturn(Optional.of(produto));
        when(produtoConverter.toResponse(produto)).thenReturn(produtoResponse);

        ProdutoResponse result = produtoService.buscarProdutoPorId(produtoId);

        assertNotNull(result);
        assertEquals(produtoId, result.getId());
        verify(produtoRepository, times(1)).findById(produtoId);
        verify(produtoConverter, times(1)).toResponse(produto);
    }

    @Test
    void testBuscarProdutoPorId_ProdutoNaoExiste() {
        String produtoId = "123";

        when(produtoRepository.findById(produtoId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            produtoService.buscarProdutoPorId(produtoId);
        });

        assertEquals("Produto não encontrado com o ID fornecido.", exception.getMessage());
        verify(produtoRepository, times(1)).findById(produtoId);
    }

    @Test
    void testBuscarProdutosPorNome_ProdutosEncontrados() {
        String nome = "Produto";
        Produto produto = new Produto();
        produto.setNome(nome);

        ProdutoResponse produtoResponse = new ProdutoResponse();
        produtoResponse.setNome(nome);

        when(produtoRepository.findByNomeLike("%" + nome + "%")).thenReturn(List.of(produto));
        when(produtoConverter.toResponse(produto)).thenReturn(produtoResponse);

        List<ProdutoResponse> result = produtoService.buscarProdutosPorNome(nome);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(nome, result.get(0).getNome());
        verify(produtoRepository, times(1)).findByNomeLike("%" + nome + "%");
        verify(produtoConverter, times(1)).toResponse(produto);
    }

    @Test
    void testBuscarProdutosPorNome_NenhumProdutoEncontrado() {
        String nome = "ProdutoInexistente";

        when(produtoRepository.findByNomeLike("%" + nome + "%")).thenReturn(Collections.emptyList());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            produtoService.buscarProdutosPorNome(nome);
        });

        assertEquals("Nenhum produto encontrado com o nome fornecido.", exception.getMessage());
        verify(produtoRepository, times(1)).findByNomeLike("%" + nome + "%");
    }

    @Test
    void testExistsById_ProdutoExiste() {
        String produtoId = "123";

        when(produtoRepository.existsById(produtoId)).thenReturn(true);

        boolean exists = produtoService.existsById(produtoId);

        assertTrue(exists);
        verify(produtoRepository, times(1)).existsById(produtoId);
    }

    @Test
    void testExistsById_ProdutoNaoExiste() {
        String produtoId = "123";

        when(produtoRepository.existsById(produtoId)).thenReturn(false);

        boolean exists = produtoService.existsById(produtoId);

        assertFalse(exists);
        verify(produtoRepository, times(1)).existsById(produtoId);
    }

    @Test
    void testSaveProduct() {
        ProdutoRequest produtoRequest = new ProdutoRequest();
        produtoRequest.setId("123");
        produtoRequest.setNome("Produto Teste");
        produtoRequest.setPreco(100.0);
        produtoRequest.setQuantidade(10);

        Produto produto = new Produto();
        produto.setId(produtoRequest.getId());
        produto.setNome(produtoRequest.getNome());
        produto.setPreco(produtoRequest.getPreco());
        produto.setQuantidade(produtoRequest.getQuantidade());

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        ProdutoRequest result = produtoService.saveProduct(produtoRequest);

        assertNotNull(result);
        assertEquals(produtoRequest.getId(), result.getId());
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }
}
