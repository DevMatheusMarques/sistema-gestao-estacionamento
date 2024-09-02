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

    public Estacionamento() {
        this.totalVagas = 500; // Define o total de vagas como 500
        this.vagasMensalistas = 200; // Reserva 200 vagas para mensalistas
        this.vagasAvulsos = this.totalVagas - this.vagasMensalistas; // Calcula vagas para avulsos

        // Inicializa a lista de vagas, todas começando como livres (0)
        this.vagas = new ArrayList<>();
        for (int i = 0; i < this.totalVagas; i++) {
            vagas.add(0); // Inicialmente todas as vagas estão livres (0)
        }
    }

    public int getVagasDisponiveis() {
        int vagasOcupadas = 0; // Contador de vagas ocupadas
        for (int vaga : vagas) {
            if (vaga == 1) vagasOcupadas++; // Conta quantas vagas estão ocupadas
        }
        return totalVagas - vagasOcupadas; // Retorna o número de vagas livres
    }

    public String getVagasDisponiveisTabela(boolean paraMensalistas) {
        List<Integer> vagasDisponiveis = new ArrayList<>();

        // Define o intervalo de vagas a considerar, dependendo se é para mensalistas ou avulsos
        int inicio = paraMensalistas ? 0 : vagasMensalistas;
        int fim = paraMensalistas ? vagasMensalistas : totalVagas;

        // Adiciona as vagas livres à lista
        for (int i = inicio; i < fim; i++) {
            if (vagas.get(i) == 0) {
                vagasDisponiveis.add(i + 1); // +1 para exibir as vagas em formato humano (1-based)
            }
        }

        int numColunas = 10; // Número fixo de colunas na tabela
        StringBuilder sb = new StringBuilder();

        // Adiciona linhas da tabela
        for (int i = 0; i < vagasDisponiveis.size(); i++) {
            if (i % numColunas == 0 && i != 0) {
                sb.append("\n"); // Adiciona uma nova linha a cada 10 vagas
            }
            sb.append(String.format("%-5d", vagasDisponiveis.get(i))); // Formata as vagas para exibição
        }
        return sb.toString(); // Retorna a tabela formatada como string
    }

    private int encontrarVagasDisponiveis(int tamanhoNecessario, boolean paraMensalistas) {
        // Define o intervalo de vagas a considerar, dependendo se é para mensalistas ou avulsos
        int inicio = paraMensalistas ? 0 : vagasMensalistas;
        int fim = paraMensalistas ? vagasMensalistas : totalVagas;

        // Procura por um bloco de vagas livres do tamanho necessário
        for (int i = inicio; i <= fim - tamanhoNecessario; i++) {
            boolean espacoDisponivel = true;
            for (int j = 0; j < tamanhoNecessario; j++) {
                if (vagas.get(i + j) == 1) { // Verifica se a vaga está ocupada
                    espacoDisponivel = false;
                    break; // Sai do loop interno se alguma vaga do bloco estiver ocupada
                }
            }
            if (espacoDisponivel) {
                return i; // Retorna o índice da primeira vaga do bloco encontrado
            }
        }
        return -1; // Retorna -1 se não houver um bloco de vagas livres do tamanho necessário
    }

    public Map<String, Object> entradaVeiculo(String tipoVeiculo, boolean mensalista) {
        int tamanhoNecessario;

        // Determina o tamanho necessário para o veículo com base no tipo
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
                throw new IllegalArgumentException("Tipo de veículo inválido."); // Lança exceção para tipos de veículos inválidos
        }

        // Encontra um bloco de vagas disponíveis
        int vagaInicial = encontrarVagasDisponiveis(tamanhoNecessario, mensalista);
        Map<String, Object> resultado = new HashMap<>();

        if (vagaInicial == -1) {
            // Se não houver vagas disponíveis, imprime mensagem e retorna erro
            System.out.println("Sem vagas disponíveis para " + tipoVeiculo + " " + (mensalista ? "mensalista" : "avulso"));
            resultado.put("sucesso", false);
            resultado.put("vagaInicial", -1);
            return resultado;
        }

        // Ocupa as vagas para o veículo
        for (int i = 0; i < tamanhoNecessario; i++) {
            vagas.set(vagaInicial + i, 1); // Marca as vagas como ocupadas
        }

        // Imprime mensagem de sucesso e retorna o resultado
        System.out.println(tipoVeiculo + " " + (mensalista ? "mensalista" : "avulso") + " estacionado nas vagas " + (vagaInicial + 1) + " a " + (vagaInicial + tamanhoNecessario));
        resultado.put("sucesso", true);
        resultado.put("vagaInicial", vagaInicial + 1); // Retorna o índice da primeira vaga ocupada
        return resultado;
    }

    public void saidaVeiculo(int vagaInicial, String tipoVeiculo, boolean mensalista) {
        int tamanhoNecessario;

        // Determina o tamanho necessário para o veículo com base no tipo
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
                throw new IllegalArgumentException("Tipo de veículo inválido."); // Lança exceção para tipos de veículos inválidos
        }

        // Libera as vagas que o veículo ocupava
        for (int i = 0; i < tamanhoNecessario; i++) {
            vagas.set(vagaInicial - 1 + i, 0); // Marca as vagas como livres (ajuste para índice baseado em 1)
        }

        // Imprime mensagem indicando a saída do veículo
        System.out.println(tipoVeiculo + " " + (mensalista ? "mensalista" : "avulso") + " saiu das vagas " + vagaInicial + " a " + (vagaInicial + tamanhoNecessario - 1));
    }
}
