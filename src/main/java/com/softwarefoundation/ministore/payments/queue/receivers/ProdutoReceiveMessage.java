package com.softwarefoundation.ministore.payments.queue.receivers;

import com.softwarefoundation.ministore.payments.dto.ProdutoDto;
import com.softwarefoundation.ministore.payments.respository.ProdutoRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProdutoReceiveMessage {

    private ProdutoRespository produtoRespository;

    @Autowired
    public ProdutoReceiveMessage(ProdutoRespository produtoRespository) {
        this.produtoRespository = produtoRespository;
    }

    @RabbitListener(queues = {"${ministore.rabbitmq.queue}"})
    public void receive(@Payload ProdutoDto produtoDto){
        produtoRespository.save(produtoDto.toEntity());
        log.info("Produto ID: {} - {} recebido", produtoDto.getId(), produtoDto.getNome());
    }
}
