package org.compass.model.entities;

import java.time.Duration;

public class Cobranca {
    protected double valorMinuto = 0.10;
    protected double valorMinimo = 5.00;
    protected double valorMensalidade = 250.00;

    protected Ticket ticket;

    public Cobranca() {
    }

    public Cobranca(double valorMinuto, double valorMinimo, double valorMensalidade) {
        this.valorMinuto = valorMinuto;
        this.valorMinimo = valorMinimo;
        this.valorMensalidade = valorMensalidade;
    }

    public double getValorMinuto() {
        return valorMinuto;
    }

    public double getValorMinimo() {
        return valorMinimo;
    }

    public double getValorMensalidade() {
        return valorMensalidade;
    }

    public double calcularValor() {
        long minutosEstacionado = Duration.between(ticket.getDataHoraEntrada(), ticket.getDataHoraSaida()).toMinutes();
        return Math.max(getValorMinuto(), minutosEstacionado * getValorMinimo() * ticket.getVagasOcupadas());
    }
}
