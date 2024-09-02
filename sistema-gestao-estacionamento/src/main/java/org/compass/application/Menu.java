package org.compass.application;

import org.compass.model.entities.*;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    protected Estacionamento estacionamento;
    protected Veiculo veiculo;


    public Menu() throws SQLException {
        this.estacionamento = new Estacionamento();
        this.veiculo = new Veiculo();
    }

    public void inicio() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu de Opções:");
            System.out.println("1. Entrada");
            System.out.println("2. Saída");
            System.out.println("3. Sair do Sistema");
            System.out.println();
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            System.out.println();

            switch (opcao) {
                case 1:
                    entrada(scanner);
                    break;
                case 2:
                    saida(scanner);
                    break;
                case 3:
                    System.out.println("Encerrando o sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void entrada(Scanner scanner) throws SQLException {
        // Verifica se é uma emergência
        System.out.print("Este é um veículo de emergência? (S/N): ");
        String emergencia = scanner.next();
        System.out.println();

        if (emergencia.equalsIgnoreCase("S")) {
            System.out.print("Informe a cancela: ");
            int numeroCancela = scanner.nextInt();
            System.out.println();
            Cancela.permiteEntrar("VeiculoServicoPublico", numeroCancela);
            System.out.println();
            return;
        }

        System.out.print("Informe a placa do veículo: ");
        String placa = scanner.next();
        System.out.println();

        Veiculo veiculo = this.veiculo;
        Boolean veiculoEncontrado = veiculo.getPlacaVeiculo(placa);// renomear função

        if (veiculoEncontrado) {
            String tipoVeiculo = veiculo.getTipoByPlaca(placa); // remover consulta redundante
            System.out.println("Tipo do veículo: " + tipoVeiculo);
            System.out.println();

            System.out.print("Informe a cancela: ");
            int numeroCancela = scanner.nextInt();
            System.out.println();
            boolean podeEntrar = Cancela.permiteEntrar(tipoVeiculo, numeroCancela);
            System.out.println();

            if (podeEntrar) {
                if (Objects.equals(tipoVeiculo, "caminhao")) {
                    Estacionamento estacionamento = this.estacionamento;//resolver classe sobrescrita
                    estacionamento.entradaVeiculo(tipoVeiculo, false);
                    System.out.println();
                    System.out.println("Vagas disponíveis (Avulso): \n" + estacionamento.getVagasDisponiveisTabela(false));
                    System.out.println();
                    return;
                }

                Estacionamento estacionamento = this.estacionamento;
                estacionamento.entradaVeiculo(tipoVeiculo, true);
                System.out.println();
                System.out.println("Vagas disponíveis (Mensalista): \n" + estacionamento.getVagasDisponiveisTabela(true));
                System.out.println();
                return;
            }

            return;
        }

        System.out.println("Categorias disponíveis:");
        System.out.println("1. Avulso");
        System.out.println("2. Mensalista");
        System.out.println("3. Caminhão");
        System.out.println();

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        System.out.println();

        if (opcao == 1) {
            System.out.println("Selecione uma das opções:");
            System.out.println("1. Carro");
            System.out.println("2. Moto");
            System.out.println();

            System.out.print("Escolha uma opção: ");
            int tipo = scanner.nextInt();
            System.out.println();

            String tipoVeiculo;
            if (tipo == 1) {
                tipoVeiculo = "carro";
            } else if (tipo == 2) {
                tipoVeiculo = "moto";
            } else {
                System.out.println("Opção inválida.");
                System.out.println();
                return;
            }

            System.out.print("Informe a cancela: ");
            int numeroCancela = scanner.nextInt();
            System.out.println();
            boolean podeEntrar = Cancela.permiteEntrar(tipoVeiculo, numeroCancela);
            System.out.println();

            if (podeEntrar) {
                Estacionamento estacionamento = this.estacionamento;
                Map<String, Object> resultado = estacionamento.entradaVeiculo(tipoVeiculo, false);

                int vagaInicial;
                if ((boolean) resultado.get("sucesso")) {
                    vagaInicial = (int) resultado.get("vagaInicial");
                } else {
                    vagaInicial = -1;
                }

                Ticket ticket = new Ticket();
                ticket.setPlaca(placa);
                ticket.setCancelaEntrada(numeroCancela);
                ticket.setTipoVeiculo(tipoVeiculo);
                ticket.setVagaInicial(vagaInicial);

                if (Objects.equals(tipoVeiculo, "carro")) {
                    ticket.setOcupacaoVagas(2);
                } else {
                    ticket.setOcupacaoVagas(1);
                }
                System.out.println();
                ticket.registraTicket(ticket);

                System.out.println();
                System.out.println("Vagas disponíveis (Avulsos): \n" + estacionamento.getVagasDisponiveisTabela(false));
                System.out.println();
                return;
            }
            return;

        } else if (opcao == 2) {
            System.out.println("Selecione uma das opções:");
            System.out.println("1. Carro");
            System.out.println("2. Moto");
            System.out.println();

            System.out.print("Escolha uma opção: ");
            int tipo = scanner.nextInt();
            System.out.println();

            String tipoVeiculo;
            if (tipo == 1) {
                tipoVeiculo = "carro";
            } else if (tipo == 2) {
                tipoVeiculo = "moto";
            } else {
                System.out.println("Opção inválida.");
                System.out.println();
                return;
            }

            System.out.print("Informe a cancela: ");
            int numeroCancela = scanner.nextInt();
            System.out.println();
            boolean podeEntrar = Cancela.permiteEntrar(tipoVeiculo, numeroCancela);
            System.out.println();

            if (podeEntrar) {
                System.out.println("Necessário efetuar pagamento da mensalidade!");
                System.out.println();
                System.out.println("Valor mensalidade: R$250,00");
                System.out.println();
                System.out.println("----- Pagamento Mensalidade -----");
                System.out.println();

                System.out.println("Selecione o método de pagamento:");
                System.out.println();
                System.out.println("1. Cartão de Débito");
                System.out.println("2. Cartão de Crédito");
                System.out.println("3. PIX");
                System.out.println();
                System.out.print("Informe a opção desejada: ");
                int metodoPagamento = scanner.nextInt();
                System.out.println();

                if (metodoPagamento == 1) {
                    System.out.println("Método de pagamento selecionado: Cartão de Débito");
                    System.out.println();
                    System.out.println("Efetuando pagamento...");
                } else if (metodoPagamento == 2) {
                    System.out.println("Método de pagamento selecionado: Cartão de Crédito");
                    System.out.println();
                    System.out.println("Efetuando pagamento...");
                } else if (metodoPagamento == 3) {
                    System.out.println("Método de pagamento selecionado: PIX");
                    System.out.println();
                    System.out.println("Efetuando pagamento...");
                }

                System.out.println();
                System.out.println("Consultando sistema TEF...");
                System.out.println();
                System.out.println("Transação autorizada");
                System.out.println();
                System.out.println("Valor pago: R$250,00");
                System.out.println();

                Estacionamento estacionamento = this.estacionamento;
                Map<String, Object> resultado = estacionamento.entradaVeiculo(tipoVeiculo, true);

                int vagaInicial;
                if ((boolean) resultado.get("sucesso")) {
                    vagaInicial = (int) resultado.get("vagaInicial");
                } else {
                    vagaInicial = -1;
                }

                Veiculo novoVeiculo = this.veiculo;
                veiculo.setPlaca(placa);
                veiculo.setMensalista(true);
                veiculo.setVagaInicial(vagaInicial);

                if (Objects.equals(tipoVeiculo, "carro")) {
                    veiculo.setTipo(Tipo.CARRO);
                    veiculo.setOcupacaoVagas(2);
                } else if (Objects.equals(tipoVeiculo, "moto")) {
                    veiculo.setTipo(Tipo.MOTO);
                    veiculo.setOcupacaoVagas(1);
                }
                novoVeiculo.CadastrarNovoVeiculo(novoVeiculo);
                System.out.println();
                System.out.println("Vagas disponíveis (Mensalista): \n" + estacionamento.getVagasDisponiveisTabela(true));
                System.out.println();
                return;
            }
            return;
        } else if (opcao == 3) {
            String tipoVeiculo = "caminhao";
            System.out.print("Informe a cancela: ");
            int numeroCancela = scanner.nextInt();
            System.out.println();
            boolean podeEntrar = Cancela.permiteEntrar(tipoVeiculo, numeroCancela);
            System.out.println();

            if (podeEntrar) {
                Estacionamento estacionamento = this.estacionamento;
                Map<String, Object> resultado = estacionamento.entradaVeiculo(tipoVeiculo, false);

                int vagaInicial;
                if ((boolean) resultado.get("sucesso")) {
                    vagaInicial = (int) resultado.get("vagaInicial");
                } else {
                    vagaInicial = -1;
                }

                Veiculo novoVeiculo = this.veiculo;
                veiculo.setPlaca(placa);
                veiculo.setTipo(Tipo.CAMINHAO);
                veiculo.setMensalista(false);
                veiculo.setVagaInicial(vagaInicial);
                veiculo.setOcupacaoVagas(4);

                novoVeiculo.CadastrarNovoVeiculo(novoVeiculo);

                System.out.println();
                System.out.println("Vagas disponíveis (Avulsos): \n" + estacionamento.getVagasDisponiveisTabela(false));
                System.out.println();
                return;
            }
            return;
        }

    }

    private void saida(Scanner scanner) throws SQLException {
        // Verifica se é uma emergência
        System.out.print("Este é um veículo de emergência? (S/N): ");
        String emergencia = scanner.next();
        System.out.println();

        if (emergencia.equalsIgnoreCase("S")) {
            System.out.print("Informe a cancela: ");
            int numeroCancela = scanner.nextInt();
            System.out.println();
            Cancela.permiteSair("VeiculoServicoPublico", numeroCancela);
            System.out.println();
            return;
        }

        System.out.print("Possui ticket ? (S/N): ");
        String respostaTicket = scanner.next();
        System.out.println();

        if (respostaTicket.equalsIgnoreCase("S")) {
            System.out.print("Informe a placa do veículo: ");
            String placa = scanner.next();
            System.out.println();

            Ticket ticket = new Ticket();
            Map<String, Object> resultado = ticket.getTicketByPlaca(placa);

            if ((Boolean) resultado.get("sucesso")) {
                System.out.println("Ticket encontrado!");
                System.out.println();

                Ticket ticketData = (Ticket) resultado.get("ticket");
                String tipoVeiculo = ticketData.getTipoVeiculo();
                System.out.println("Veículo do tipo: " + tipoVeiculo);
                System.out.println();

                System.out.println("----- Pagamento Ticket -----");
                System.out.println();

                System.out.println("Selecione o método de pagamento:");
                System.out.println();
                System.out.println("1. Cartão de Débito");
                System.out.println("2. Cartão de Crédito");
                System.out.println("3. PIX");
                System.out.println();
                System.out.print("Informe a opção desejada: ");
                int metodoPagamento = scanner.nextInt();
                System.out.println();

                if (metodoPagamento == 1) {
                    System.out.println("Método de pagamento selecionado: Cartão de Débito");
                    System.out.println();
                    System.out.println("Efetuando pagamento...");
                } else if (metodoPagamento == 2) {
                    System.out.println("Método de pagamento selecionado: Cartão de Crédito");
                    System.out.println();
                    System.out.println("Efetuando pagamento...");
                } else if (metodoPagamento == 3) {
                    System.out.println("Método de pagamento selecionado: PIX");
                    System.out.println();
                    System.out.println("Efetuando pagamento...");
                }

                System.out.println();
                System.out.println("Consultando sistema TEF...");
                System.out.println();
                System.out.println("Transação autorizada");
                System.out.println();

                System.out.print("Informe a cancela: ");
                int numeroCancela = scanner.nextInt();
                System.out.println();

                boolean podeSair = Cancela.permiteSair(tipoVeiculo, numeroCancela);
                System.out.println();

                if (podeSair) {
                    ticketData.registrarSaidaVeiculo(ticketData, numeroCancela);
                    System.out.println();
                    System.out.println("Ticket:");
                    System.out.println("ID: " + ticketData.getId());
                    System.out.println("Placa: " + ticketData.getPlaca());
                    System.out.println("TipoVeiculo: " + ticketData.getTipoVeiculo());
                    System.out.println("DataHora: " + ticketData.getDataHoraEntrada());
                    System.out.println("DataHoraSaida: " + ticketData.getDataHoraSaida());
                    System.out.println("CancelaEntrada: " + ticketData.getCancelaEntrada());
                    System.out.println("CancelaSaida: " + ticketData.getCancelaSaida());
                    System.out.println("OcupacaoVagas: " + ticketData.getOcupacaoVagas());
                    System.out.println("PrecoFinal: " + ticketData.getPrecoFinal());
                    System.out.println();

                    Estacionamento estacionamento = this.estacionamento;
                    estacionamento.saidaVeiculo(ticketData.getVagaInicial(), tipoVeiculo, false);
                    System.out.println();
                    System.out.println("Vagas disponíveis (Avulso): \n" + estacionamento.getVagasDisponiveisTabela(false));
                    System.out.println();
                    return;
                }
            }
            return;
        }

        System.out.print("Informe a placa do veículo: ");
        String placa = scanner.next();
        System.out.println();

        Veiculo veiculo = this.veiculo;
        Boolean veiculoEncontrado = veiculo.getPlacaVeiculo(placa);

        if (veiculoEncontrado) {
            String tipoVeiculo = veiculo.getTipoByPlaca(placa);
            System.out.println("Tipo do veículo: " + tipoVeiculo);
            System.out.println();

            System.out.print("Informe a cancela: ");
            int numeroCancela = scanner.nextInt();
            System.out.println();
            boolean podeSair = Cancela.permiteSair(tipoVeiculo, numeroCancela);
            System.out.println();

            int vagaInicial = veiculo.getByVagaInicial(placa);

            if (podeSair) {
                if (Objects.equals(tipoVeiculo, "caminhao")) {
                    Estacionamento estacionamento = this.estacionamento;
                    estacionamento.saidaVeiculo(vagaInicial ,tipoVeiculo, false);
                    System.out.println();
                    System.out.println("Vagas disponíveis (Avulso): \n" + estacionamento.getVagasDisponiveisTabela(false));
                    System.out.println();
                    return;
                }

                Estacionamento estacionamento = this.estacionamento;
                estacionamento.saidaVeiculo(vagaInicial ,tipoVeiculo, true);
                System.out.println();
                System.out.println("Vagas disponíveis (Mensalista): \n" + estacionamento.getVagasDisponiveisTabela(true));
                System.out.println();
                return;
            }
            return;
        }

    }
}
