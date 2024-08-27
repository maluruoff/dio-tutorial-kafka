package br.com.hoje.tutorial_microservico_kafka.service;

import br.com.hoje.tutorial_microservico_kafka.data.PedidoData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SalvarPedidoService {

    @KafkaListener(topics = "SalvarPedido", groupId = "MicroservicoSalvaPedido")
    private void executar(ConsumerRecord<String, String> record) {
        log.info("Key: {}, Value: {}", record.key(), record.value());
        log.info("Cabeçalho: {}", record.headers());
        log.info("Particao: {}, Offset: {}", record.partition(), record.offset());
        log.info("Consumidor: {}", Thread.currentThread().getName());

        String strDados = record.value();

        ObjectMapper mapper = new ObjectMapper();
        PedidoData pedidoData = null;
        try {
            pedidoData = mapper.readValue(strDados, PedidoData.class);
        } catch (JsonProcessingException e) {
            log.error("Erro ao converter JSON: [dados={}]", strDados, e);
            return;
        }

        log.info("Evento recebido: {}", pedidoData);
    }
}
