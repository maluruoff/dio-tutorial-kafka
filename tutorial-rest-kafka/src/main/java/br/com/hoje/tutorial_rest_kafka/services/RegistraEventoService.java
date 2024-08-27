package br.com.hoje.tutorial_rest_kafka.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistraEventoService {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public <T> void adicionarEvento(String topico, T dados) {
        kafkaTemplate.send(topico, dados);
    }
}
