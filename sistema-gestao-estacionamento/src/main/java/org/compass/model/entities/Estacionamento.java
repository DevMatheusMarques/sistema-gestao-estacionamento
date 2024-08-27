package org.compass.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private int vagasAvulso = 300;
    private int vagasMensalistas = 200;

    private List<Veiculo> veiculosAvulsos = new ArrayList<>();
    private List<Mensalista> mensalistas = new ArrayList<>();

    public int getVagasAvulso() {
        return vagasAvulso;
    }

    public int getVagasMensalistas() {
        return vagasMensalistas;
    }

    public boolean podeEstacionar(Veiculo veiculo) {
        return true;
    }

    public void registrarEntrada(Veiculo veiculo, int numeroCancela) {
    }

    public void registrarSaida(Veiculo veiculo, int numeroCancela) {
    }

    private Mensalista getMensalistaPorVeiculo(Veiculo veiculo) {
        return null;
    }

    public void adicionarMensalista(Mensalista mensalista) {
        this.mensalistas.add(mensalista);
    }
}

