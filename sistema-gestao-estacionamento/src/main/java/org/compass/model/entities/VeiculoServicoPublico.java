package org.compass.model.entities;

public class VeiculoServicoPublico extends Veiculo {

    public VeiculoServicoPublico(int id, String placa) {
        super(id, placa);
    }

    @Override
    public int getOcupacaoVagas() {
        // Veículos de Serviço Público não ocupam vagas
        return 0;
    }
}
