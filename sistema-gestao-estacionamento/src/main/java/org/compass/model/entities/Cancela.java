package org.compass.model.entities;

public class Cancela {

    public static boolean permiteEntrar(String veiculo, Integer cancela) {
        try {
            boolean isLiberada = false;
            // Se a cancela for maior ou igual a 6 ela não é uma cancela de entrada, então é informado uma mensagem e retorna falso, não permitindo que a cancela seja liberada
            if (cancela >= 6) {
                System.out.println("Veiculos não podem entrar por esta cancela. Por favor dirija-se às cancelas 1, 2, 3, 4 ou 5.");
                return isLiberada;
            }

            // Verifica qual a classe de veículo que foi passada
            switch (veiculo) {
                // Carro e Serviço Público podem entrar por qualquer cancela de entrada
                case "carro", "VeiculoServicoPublico":
                    isLiberada = liberaCancela();
                    break;

                case "moto":
                    // Verifica qual a categoria da moto
                    if (cancela == 5) {
                        // Se a cancela passada for correspondente a 5 a cancela é liberada
                        isLiberada = liberaCancela();
                        break;
                    }
                    // Se a cancela passada não for correspondente a opção 5, a cancela é barrada e informa para qual cancela o motorista precisa ir
                    System.out.println("Este tipo de veículo não pode entrar por esta cancela. Por favor dirija-se à cancela 5.");
                    break;

                case "caminhao":
                    // Verifica se a cancela passada corresponde a cancela numero 1
                    if (cancela == 1) {
                        // Se a cancela passada for correspondente a 1 a cancela é liberada
                        isLiberada = liberaCancela();
                        break;
                    }
                    // Se a cancela passada não for correspondente a opção 1, a cancela é barrada e informa para qual cancela o motorista precisa ir
                    System.out.println("Este tipo de veículo não pode entrar por esta cancela. Por favor dirija-se à cancela 1.");
                    break;
            }
            // Se não for nenhuma das opçõe de classes retorna falso
            return isLiberada;

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean permiteSair(String veiculo, Integer cancela) {
        try {
            boolean isLiberada = false;
            // Se a cancela for menor ou igual a 5 ela não é uma cancela de saída, então é informado uma mensagem e retorna falso, não permitindo que a cancela seja liberada
            if (cancela <= 5) {
                System.out.println("Veiculos não podem sair por esta cancela. Por favor dirija-se às cancelas 6, 7, 8, 9 ou 10");
                return isLiberada;
            }

            // Verifica qual a classe de veículo que foi passada
            switch (veiculo) {
                // Carro e Serviço Público podem sair por qualquer cancela de saída
                case "carro", "VeiculoServicoPublico", "caminhao":
                    isLiberada = liberaCancela();
                    break;

                case "moto":
                    // Verifica qual a categoria da moto
                    if (cancela == 10) {
                        // Se a cancela passada for correspondente a 10, a cancela é liberada
                        isLiberada = liberaCancela();
                        break;
                    }
                    // Se a cancela passada não for correspondente a opção 10, a cancela é barrada e informa para qual cancela o motorista precisa ir
                    System.out.println("Este tipo de veículo não pode entrar por esta cancela. Por favor dirija-se à cancela 10.");
                    break;

            }
            // Se não for nenhuma das opçõe de classes retorna falso
            return isLiberada;

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean liberaCancela() {
        // Libera a cancela retornando true
        System.out.println("Cancela liberada");
        return true;
    }
}
