package org.compass.model.entities;

public class CaminhaoEntrega extends Veiculo {
    public CaminhaoEntrega(int id, String placa) {
        super(id, placa);
    }

    @Override
    public int getOcupacaoVagas() {
        return 4;
    }
}
