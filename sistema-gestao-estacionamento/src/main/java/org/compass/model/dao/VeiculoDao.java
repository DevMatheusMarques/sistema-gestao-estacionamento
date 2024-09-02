package org.compass.model.dao;

import org.compass.model.entities.Tipo;
import org.compass.model.entities.Veiculo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface VeiculoDao {
    Boolean registraVeiculo(Veiculo veiculo);
    int getByVagaInicial(String placa);
    String getTipoByPlaca(String placa) throws SQLException;
    Map<String, Object> getVeiculoByPlaca(String placa) throws SQLException;
}
