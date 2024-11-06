package com.example.motus_order.service;

import com.example.motus_order.produto.ProdutoRequest;
import com.example.motus_order.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private ProdutoService produtoService;

    @KafkaListener(topics = "nome-do-topico-produtoA", groupId = "order-group")
    public void receiveProduto(ProdutoRequest produtoRequest) {
        System.out.println("Produto recebido via Kafka: " + produtoRequest);
        produtoService.saveProduct(produtoRequest);

    }
}
