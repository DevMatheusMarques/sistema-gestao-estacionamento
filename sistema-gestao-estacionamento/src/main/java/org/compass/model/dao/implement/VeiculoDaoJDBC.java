package org.compass.model.dao.implement;

import org.compass.model.dao.VeiculoDao;
import org.compass.model.entities.Veiculo;

import java.sql.Connection;
import java.util.List;

public class VeiculoDaoJDBC implements VeiculoDao {

    private Connection connection;

    public VeiculoDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertVeiculo(Veiculo veiculo) {

    }

    @Override
    public void updateVeiculo(Veiculo veiculo) {

    }

    @Override
    public void deleteVeiculo(Veiculo veiculo) {

    }

    @Override
    public Veiculo getVeiculoById(int id) {
        return null;
    }

    @Override
    public List<Veiculo> getAllVeiculos() {
        return List.of();
    }
}
