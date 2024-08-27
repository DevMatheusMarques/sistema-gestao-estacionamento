package org.compass.model.entities;

public class Moto extends Veiculo {
    private Categoria categoria;


    public Moto(int id, String placa, Categoria categoria) {
        super(id, placa);
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public int getOcupacaoVagas() {
        return 1;
    }
}
