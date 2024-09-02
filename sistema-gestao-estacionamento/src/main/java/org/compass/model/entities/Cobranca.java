package org.compass.model.entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Cobranca {
    private static final double VALOR_MINUTO = 0.10; // Valor por minuto em centavos
    private static final double VALOR_MINIMO = 5; // Valor mínimo a ser cobrado
    private Ticket ticket; // Instância da classe Ticket para o veículo que está sendo cobrado

    public Cobranca(Ticket ticket) {
        this.ticket = ticket;
    }

    public float calcularValor() {
        // Calcula a diferença em minutos entre a entrada e o horário atual
        long minutosEstacionado = Duration.between(this.ticket.getDataHoraEntrada(), LocalDateTime.now()).toMinutes();

        // Calcula o valor baseado no tempo de estacionamento
        double valorPorTempo = minutosEstacionado * VALOR_MINUTO;

        // Soma o valor mínimo ao valor calculado pelo tempo
        double valor = valorPorTempo + VALOR_MINIMO;

        // Se o veículo for um carro, o valor é multiplicado por 2 (devido à ocupação de 2 vagas)
        double valorFinal = Objects.equals(this.ticket.getTipoVeiculo(), "carro") ? valor * 2 : valor;

        // Retorna o valor final
        return (float) valorFinal;
    }
}
