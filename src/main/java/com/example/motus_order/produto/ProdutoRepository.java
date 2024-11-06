package com.example.motus_order.produto;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {

    boolean existsById(String id);

    // Busca por nome contendo a string (like)
    @Query("{ 'nome': { $regex: ?0, $options: 'i' } }")
    List<Produto> findByNomeLike(String nome);
}

