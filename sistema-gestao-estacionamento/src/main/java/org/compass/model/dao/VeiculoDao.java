package org.compass.model.dao;

import org.compass.model.entities.Veiculo;

import java.util.List;

public interface VeiculoDao {
    void insertVeiculo(Veiculo veiculo);
    void updateVeiculo(Veiculo veiculo);
    void deleteVeiculo(Veiculo veiculo);
    Veiculo getVeiculoById(int id);
    List<Veiculo> getAllVeiculos();
}
