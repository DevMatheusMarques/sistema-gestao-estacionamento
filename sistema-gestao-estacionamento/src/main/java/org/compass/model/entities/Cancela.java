package org.compass.model.entities;

import java.util.Arrays;


public class Cancela {

    public boolean permiteEntrar(Veiculo veiculo, String cancela) {
        try {
            boolean isLiberada = false;

            // Verifica qual a classe de veículo que foi passada
            switch (veiculo.getClass().getSimpleName()) {
                case "CarroPasseio":
                    // Verifica qual a categoria do carro
                    Categoria categoriaCarro = ((CarroPasseio) veiculo).getCategoria();
                    if (categoriaCarro == Categoria.MENSAL || categoriaCarro == Categoria.AVULSO) {
                        // Se ele for mensal ou avulso vai verificar se a cancela passada corresponde a uma das cancelas listadas dentro do array
                        if (Arrays.asList("1", "2", "3", "4", "5").contains(cancela)) {
                            // Se a cancela passada for correspondente a uma das opções a cancela é liberada
                            System.out.println("Cancela liberada");
                            isLiberada = isCancelaEntrada(cancela);
                            break;
                        }
                        // Se a cancela passada não for correspondente a uma das opções, a cancela é barrada e informa para qual cancela o motorista precisa ir
                        System.out.println("Este tipo de veículo não pode entrar por esta cancela. Por favor dirija-se às cancelas 1, 2, 3, 4 ou 5.");
                        break;
                    }
                    // Se for uma categoria de veículo diferente mensal ou avulso, a cancela é barrada e informa que este veículo possui uma categroia inválida
                    System.out.println("Categoria de veículo inválida.");
                    break;

                case "Moto":
                    // Verifica qual a classe de veículo que foi passada
                    Categoria categoriaMoto = ((Moto) veiculo).getCategoria();
                    // Verifica qual a categoria da moto
                    if (categoriaMoto == Categoria.MENSAL || categoriaMoto == Categoria.AVULSO) {
                        // Se ele for mensal ou avulso vai verificar se a cancela passada corresponde a cancela numero 5
                        if ("5".equals(cancela)) {
                            // Se a cancela passada for correspondente a 5 a cancela é liberada
                            System.out.println("Cancela liberada");
                            isLiberada = true;
                            break;
                        }
                        // Se a cancela passada não for correspondente a opção 5, a cancela é barrada e informa para qual cancela o motorista precisa ir
                        System.out.println("Este tipo de veículo não pode entrar por esta cancela. Por favor dirija-se à cancela 5.");
                        break;
                    }
                    // Se for uma categoria de veículo diferente mensal ou avulso, a cancela é barrada e informa que este veículo possui uma categroia inválida
                    System.out.println("Categoria de veículo inválida.");
                    break;

                case "CaminhaoEntrega":
                    // Verifica se a cancela passada corresponde a cancela numero 1
                    if ("1".equals(cancela)) {
                        // Se a cancela passada for correspondente a 1 a cancela é liberada
                        System.out.println("Cancela liberada");
                        isLiberada = true;
                        break;
                    }
                    // Se a cancela passada não for correspondente a opção 1, a cancela é barrada e informa para qual cancela o motorista precisa ir
                    System.out.println("Este tipo de veículo não pode entrar por esta cancela. Por favor dirija-se à cancela 1.");
                    break;

                case "VeiculoServicoPublico":
                    // Verifica se a cancela passada corresponde a uma das cancelas listadas dentro do array
                    if (Arrays.asList("1", "2", "3", "4", "5").contains(cancela)) {
                        // Se a cancela passada for correspondente a uma das opções a cancela é liberada
                        System.out.println("Cancela liberada");
                        isLiberada = isCancelaEntrada(cancela);
                        break;
                    }
                    // Se a cancela passada não for correspondente a uma das opções, a cancela é barrada e informa para qual cancela o motorista precisa ir
                    System.out.println("Este tipo de veículo não pode entrar por esta cancela. Por favor dirija-se às cancelas 1, 2, 3, 4 ou 5.");
                    break;

                default:
                    // Se for tipo de veículo diferente dos listados na regra de negócio a cancela permanece fechada e exibe a mensagem.
                    System.out.println("Tipo inválido de veículo.");
                    isLiberada = false;
            }

            return isLiberada;

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean permiteSair(Veiculo veiculo, String cancela) {
        try {
            boolean isLiberada = false;

            // Verifica qual a classe de veículo que foi passada
            switch (veiculo.getClass().getSimpleName()) {
                case "CarroPasseio":
                    // Verifica qual a categoria do carro
                    Categoria categoriaCarro = ((CarroPasseio) veiculo).getCategoria();
                    if (categoriaCarro == Categoria.MENSAL || categoriaCarro == Categoria.AVULSO) {
                        // Se ele for mensal ou avulso vai verificar se a cancela passada corresponde a uma das cancelas listadas dentro do array
                        if (Arrays.asList("6", "7", "8", "9", "10").contains(cancela)) {
                            // Se a cancela passada for correspondente a uma das opções a cancela é liberada
                            System.out.println("Cancela liberada");
                            isLiberada = isCancelaSaida(cancela);
                            break;
                        }
                        // Se a cancela passada não for correspondente a uma das opções, a cancela é barrada e informa para qual cancela o motorista precisa ir
                        System.out.println("Este tipo de veículo não pode sair por esta cancela. Por favor dirija-se às cancelas 6, 7, 8, 9 ou 10.");
                        break;
                    }
                    // Se for uma categoria de veículo diferente mensal ou avulso, a cancela é barrada e informa que este veículo possui uma categroia inválida
                    System.out.println("Categoria de veículo inválida.");
                    break;

                case "Moto":
                    // Verifica qual a classe de veículo que foi passada
                    Categoria categoriaMoto = ((Moto) veiculo).getCategoria();
                    // Verifica qual a categoria da moto
                    if (categoriaMoto == Categoria.MENSAL || categoriaMoto == Categoria.AVULSO) {
                        // Se ele for mensal ou avulso vai verificar se a cancela passada corresponde a cancela numero 10
                        if ("10".equals(cancela)) {
                            // Se a cancela passada for correspondente a 10 a cancela é liberada
                            System.out.println("Cancela liberada");
                            isLiberada = true;
                            break;
                        }
                        // Se a cancela passada não for correspondente a opção 5, a cancela é barrada e informa para qual cancela o motorista precisa ir
                        System.out.println("Este tipo de veículo não pode sair por esta cancela. Por favor dirija-se à cancela 10.");
                        break;
                    }
                    // Se for uma categoria de veículo diferente mensal ou avulso, a cancela é barrada e informa que este veículo possui uma categroia inválida
                    System.out.println("Categoria de veículo inválida.");
                    break;

                case "CaminhaoEntrega":
                    // Verifica se a cancela passada corresponde a uma das cancelas listadas dentro do array
                    if (Arrays.asList("6", "7", "8", "9", "10").contains(cancela)) {
                        // Se a cancela passada for correspondente a uma das opções a cancela é liberada
                        System.out.println("Cancela liberada");
                        isLiberada = isCancelaSaida(cancela);
                        break;
                    }
                    // Se a cancela passada não for correspondente a opção 1, a cancela é barrada e informa para qual cancela o motorista precisa ir
                    System.out.println("Este tipo de veículo não pode sair por esta cancela. Por favor dirija-se às cancelas 6, 7, 8, 9 ou 10.");
                    break;

                case "VeiculoServicoPublico":
                    // Verifica se a cancela passada corresponde a uma das cancelas listadas dentro do array
                    if (Arrays.asList("6", "7", "8", "9", "10").contains(cancela)) {
                        // Se a cancela passada for correspondente a uma das opções a cancela é liberada
                        System.out.println("Cancela liberada");
                        isLiberada = isCancelaSaida(cancela);
                        break;
                    }
                    // Se a cancela passada não for correspondente a uma das opções, a cancela é barrada e informa para qual cancela o motorista precisa ir
                    System.out.println("Este tipo de veículo não pode sair por esta cancela. Por favor dirija-se às cancelas 6, 7, 8, 9 ou 10.");
                    break;

                default:
                    // Se for tipo de veículo diferente dos listados na regra de negócio a cancela permanece fechada e exibe a mensagem.
                    System.out.println("Tipo inválido de veículo.");
                    isLiberada = false;
            }

            return isLiberada;

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isCancelaEntrada(String cancela) {
        return "1".equals(cancela) || "2".equals(cancela) || "3".equals(cancela) || "4".equals(cancela) || "5".equals(cancela);
    }

    private boolean isCancelaSaida(String cancela) {
        return "6".equals(cancela) || "7".equals(cancela) || "8".equals(cancela) || "9".equals(cancela) || "10".equals(cancela);
    }
}
