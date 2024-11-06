package com.example.motus_order.service;

import com.example.motus_order.dto.ProdutoRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @KafkaListener(topics = "nome-do-topico-produtoA", groupId = "order-group")
    public void receiveProduto(ProdutoRequest produtoRequest) {
        System.out.println("Produto recebido via Kafka: " + produtoRequest);
    }
}
