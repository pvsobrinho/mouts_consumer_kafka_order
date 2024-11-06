package com.example.motus_order.produto;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {

    boolean existsById(String id);
}

