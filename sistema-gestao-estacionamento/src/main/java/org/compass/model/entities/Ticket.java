package org.compass.model.entities;

import java.time.Duration;
import java.time.LocalDateTime;

public class Ticket {
    private Veiculo veiculo;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private int numeroCancelaEntrada;
    private int numeroCancelaSaida;
    private int vagasOcupadas;

    private Cobranca cobranca;

    public Ticket(Veiculo veiculo, LocalDateTime dataHoraEntrada, int numeroCancelaEntrada) {
        this.veiculo = veiculo;
        this.dataHoraEntrada = dataHoraEntrada;
        this.numeroCancelaEntrada = numeroCancelaEntrada;
        this.vagasOcupadas = veiculo.getOcupacaoVagas();
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    public int getNumeroCancelaEntrada() {
        return numeroCancelaEntrada;
    }

    public int getNumeroCancelaSaida() {
        return numeroCancelaSaida;
    }

    public int getVagasOcupadas() {
        return vagasOcupadas;
    }

    public void registrarEntrada(LocalDateTime dataHoraEntrada, int numeroCancelaEntrada) {

    }

    public void registrarSaida(LocalDateTime dataHoraSaida, int numeroCancelaSaida) {

    }
}

