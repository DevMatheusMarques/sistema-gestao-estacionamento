package org.compass.model.entities;

import org.compass.model.dao.implement.TicketDaoJDBC;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

public class Ticket {
    private int id;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private int cancelaEntrada;
    private int cancelaSaida;
    private int ocupacaoVagas;
    private double precoFinal;
    private String placa;
    private String tipoVeiculo;
    private int vagaInicial;
    private TicketDaoJDBC ticketDaoJDBC;

    public Ticket() {
        this.ticketDaoJDBC = new TicketDaoJDBC();
    }

    // Métodos Públicos
    public void registraTicket(Ticket ticket) {
        TicketDaoJDBC dao = this.ticketDaoJDBC;
        dao.registraTicket(ticket);
    }

    public void registrarSaidaVeiculo(Ticket ticket, int numeroCancelaSaida) throws SQLException {
        TicketDaoJDBC ticketDaoJDBC = this.ticketDaoJDBC;
        ticketDaoJDBC.registrarSaida(ticket, numeroCancelaSaida);
    }

    public Map<String, Object> getTicketByPlaca(String placa) throws SQLException {
        TicketDaoJDBC ticketDaoJDBC = this.ticketDaoJDBC;
        return ticketDaoJDBC.getTicketByPlaca(placa);
    }

    // Getters
    public int getId() {
        return id;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    public int getCancelaEntrada() {
        return cancelaEntrada;
    }

    public int getCancelaSaida() {
        return cancelaSaida;
    }

    public int getOcupacaoVagas() {
        return ocupacaoVagas;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public int getVagaInicial() {
        return vagaInicial;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public void setCancelaEntrada(int cancelaEntrada) {
        this.cancelaEntrada = cancelaEntrada;
    }

    public void setCancelaSaida(int cancelaSaida) {
        this.cancelaSaida = cancelaSaida;
    }

    public void setOcupacaoVagas(int ocupacaoVagas) {
        this.ocupacaoVagas = ocupacaoVagas;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public void setVagaInicial(int vagaInicial) {
        this.vagaInicial = vagaInicial;
    }
}
