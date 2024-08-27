package org.compass.model.entities;

public abstract class Veiculo {
    protected int id;
    protected String placa;

    public Veiculo(int id, String placa) {
        this.id = id;
        this.placa = placa;
    }

    public int getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public abstract int getOcupacaoVagas();
}
