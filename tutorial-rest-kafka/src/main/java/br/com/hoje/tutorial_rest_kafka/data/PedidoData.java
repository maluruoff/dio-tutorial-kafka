package br.com.hoje.tutorial_rest_kafka.data;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoData {
    private String codigo;
    private String nomeProduto;
    private BigDecimal valor;
}
