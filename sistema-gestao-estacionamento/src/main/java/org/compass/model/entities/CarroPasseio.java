package org.compass.model.entities;

public class CarroPasseio extends Veiculo {
    private Categoria categoria; // Utilizando o enum Categoria

    public CarroPasseio(int id, String placa, Categoria categoria) {
        super(id, placa);
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public int getOcupacaoVagas() {
        return 2;
    }
}
