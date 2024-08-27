package org.compass.model.entities;

import java.time.LocalDateTime;

public class Mensalista {
    private int id;
    private String nome;
    private Veiculo veiculo;
    private LocalDateTime dataAssinatura;
    private LocalDateTime dataExpiracao;

    public Mensalista(int id, Veiculo veiculo, String nome, LocalDateTime dataAssinatura, LocalDateTime dataExpiracao) {
        this.id = id;
        this.nome = nome;
        this.veiculo = veiculo;
        this.dataAssinatura = dataAssinatura;
        this.dataExpiracao = dataExpiracao;
    }

    public int getId() {
        return id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getDataAssinatura() {
        return dataAssinatura;
    }

    public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }

    public boolean isAtivo() {
        // Verifica se a assinatura ainda está ativa
        return LocalDateTime.now().isBefore(dataExpiracao);
    }
}


