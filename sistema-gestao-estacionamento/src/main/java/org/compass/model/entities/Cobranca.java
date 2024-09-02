package org.compass.model.entities;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Cobranca {
    private static final double VALOR_MINUTO = 0.10; //centavos
    private static final double VALOR_MINIMO = 5;
    private Ticket ticket;

    public Cobranca(Ticket ticket) {
        this.ticket = ticket;
    }

    public float calcularValor() {
        long minutosEstacionado = Duration.between(this.ticket.getDataHoraEntrada(), LocalDateTime.now()).toMinutes();
        double valorPorTempo = minutosEstacionado * VALOR_MINUTO;
        double valor = valorPorTempo + VALOR_MINIMO;
        double valorFinal = Objects.equals(this.ticket.getTipoVeiculo(), "carro") ? valor * 2 : valor;
        return (float) valorFinal;
    }
}
