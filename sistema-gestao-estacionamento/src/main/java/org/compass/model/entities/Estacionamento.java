package org.compass.model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estacionamento {
    private final int totalVagas;
    private final int vagasMensalistas;
    private final int vagasAvulsos;
    private List<Integer> vagas;
    private List<Integer> vagasMensalistasList;
    private List<Integer> vagasAvulsosList;

    public Estacionamento() {
        this.totalVagas = 500;
        this.vagasMensalistas = 200;
        this.vagasAvulsos = this.totalVagas - this.vagasMensalistas;

        this.vagas = new ArrayList<>();
        for (int i = 0; i < this.totalVagas; i++) {
            vagas.add(0); // Inicialmente todas as vagas estão livres (0)
        }

        this.vagasMensalistasList = new ArrayList<>(vagas.subList(0, this.vagasMensalistas));
        this.vagasAvulsosList = new ArrayList<>(vagas.subList(this.vagasMensalistas, this.totalVagas));
    }

    public int getVagasDisponiveis() {
        int vagasOcupadas = 0;
        for (int vaga : vagas) {
            if (vaga == 1) vagasOcupadas++;
        }
        return totalVagas - vagasOcupadas;
    }

    public String getVagasDisponiveisTabela(boolean paraMensalistas) {
        List<Integer> vagasDisponiveis = new ArrayList<>();
        List<Integer> vagasParaChecar = paraMensalistas ? vagasMensalistasList : vagasAvulsosList;

        for (int i = 0; i < vagasParaChecar.size(); i++) {
            if (vagasParaChecar.get(i) == 0) {
                vagasDisponiveis.add(i + (paraMensalistas ? 0 : vagasMensalistas));
            }
        }

        int numColunas = 10; // Número fixo de colunas na tabela
        StringBuilder sb = new StringBuilder();

        // Adiciona cabeçalho vazio
        sb.append("     ");
        for (int i = 0; i < numColunas; i++) {
            sb.append(String.format("%-5s", ""));
        }
        sb.append("\n");

        // Adiciona linhas da tabela
        for (int i = 0; i < vagasDisponiveis.size(); i++) {
            if (i % numColunas == 0) {
                if (i != 0) {
                    sb.append("\n");
                }
            }
            sb.append(String.format("%-5d", vagasDisponiveis.get(i)));
        }
        return sb.toString();
    }

    private int encontrarVagasDisponiveis(int tamanhoNecessario, boolean paraMensalistas) {
        List<Integer> vagasParaChecar = paraMensalistas ? vagasMensalistasList : vagasAvulsosList;

        for (int i = 0; i <= vagasParaChecar.size() - tamanhoNecessario; i++) {
            boolean espacoDisponivel = true;
            for (int j = 0; j < tamanhoNecessario; j++) {
                if (vagasParaChecar.get(i + j) == 1) {
                    espacoDisponivel = false;
                    break;
                }
            }
            if (espacoDisponivel) {
                return i + (paraMensalistas ? 0 : vagasMensalistas);
            }
        }
        return -1;
    }

    public Map<String, Object> entradaVeiculo(String tipoVeiculo, boolean mensalista) {
        int tamanhoNecessario;
        switch (tipoVeiculo.toLowerCase()) {
            case "moto":
                tamanhoNecessario = 1;
                break;
            case "carro":
                tamanhoNecessario = 2;
                break;
            case "caminhao":
                tamanhoNecessario = 4;
                break;
            default:
                throw new IllegalArgumentException("Tipo de veículo inválido.");
        }

        int vagaInicial = encontrarVagasDisponiveis(tamanhoNecessario, mensalista);
        Map<String, Object> resultado = new HashMap<>();

        if (vagaInicial == -1) {
            System.out.println("Sem vagas disponíveis para " + tipoVeiculo + " " + (mensalista ? "mensalista" : "avulso"));
            resultado.put("sucesso", false);
            resultado.put("vagaInicial", -1);
            return resultado;
        }

        List<Integer> vagasParaAtualizar = mensalista ? vagasMensalistasList : vagasAvulsosList;
        for (int i = 0; i < tamanhoNecessario; i++) {
            vagasParaAtualizar.set(vagaInicial + i, 1); // Ocupar as vagas
        }

        System.out.println(tipoVeiculo + " " + (mensalista ? "mensalista" : "avulso") + " estacionado nas vagas " + vagaInicial + " a " + (vagaInicial + tamanhoNecessario - 1));
        resultado.put("sucesso", true);
        resultado.put("vagaInicial", vagaInicial);
        return resultado;
    }

    public void saidaVeiculo(int vagaInicial, String tipoVeiculo, boolean mensalista) {
        int tamanhoNecessario;
        switch (tipoVeiculo.toLowerCase()) {
            case "moto":
                tamanhoNecessario = 1;
                break;
            case "carro":
                tamanhoNecessario = 2;
                break;
            case "caminhao":
                tamanhoNecessario = 4;
                break;
            default:
                throw new IllegalArgumentException("Tipo de veículo inválido.");
        }

        List<Integer> vagasParaAtualizar = mensalista ? vagasMensalistasList : vagasAvulsosList;
        for (int i = 0; i < tamanhoNecessario; i++) {
            vagasParaAtualizar.set(vagaInicial + i, 0); // Liberar as vagas
        }

        System.out.println(tipoVeiculo + " " + (mensalista ? "mensalista" : "avulso") + " saiu das vagas " + vagaInicial + " a " + (vagaInicial + tamanhoNecessario - 1));
    }
}
