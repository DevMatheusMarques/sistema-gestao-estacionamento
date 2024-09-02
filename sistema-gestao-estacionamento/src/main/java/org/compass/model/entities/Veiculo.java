package org.compass.model.entities;
import org.compass.model.dao.implement.TicketDaoJDBC;
import org.compass.model.dao.implement.VeiculoDaoJDBC;

import java.sql.SQLException;
import java.util.Map;

public class Veiculo {
    // Propriedades
    protected int id;
    protected String placa;
    protected Tipo tipo;
    protected boolean mensalista;
    protected int ocupacaoVagas;
    protected int vagaInicial;
    protected VeiculoDaoJDBC veiculoDaoJDBC;

    // Construtores
    public Veiculo() throws SQLException {
        this.veiculoDaoJDBC = new VeiculoDaoJDBC();
    }

    public int getIdVeiculo(String placa) throws SQLException {
        VeiculoDaoJDBC veiculoDaoJDBC = this.veiculoDaoJDBC;
        Map<String, Object> resultado = veiculoDaoJDBC.getVeiculoByPlaca(placa);
        int veiculoId = (int) resultado.get("id");

        return veiculoId;
    }

    // Métodos Públicos
    public Map<String, Object> getPlacaVeiculo(String placa) throws SQLException {
        VeiculoDaoJDBC veiculoDaoJDBC = this.veiculoDaoJDBC;
        Map<String, Object> resultado = veiculoDaoJDBC.getVeiculoByPlaca(placa);

        return resultado;
    }

    public void CadastrarNovoVeiculo(Veiculo veiculo) throws SQLException {
        VeiculoDaoJDBC veiculoDaoJDBC = new VeiculoDaoJDBC();
        veiculoDaoJDBC.registraVeiculo(veiculo);

    }

    public void atualizaVeiculo(String placa, int vagaInicial) throws SQLException {
        VeiculoDaoJDBC veiculoDaoJDBC = this.veiculoDaoJDBC;
        veiculoDaoJDBC.atualizaVeiculo(placa, vagaInicial);
    }

    public void registrarSaidaVeiculo(int veiculoId) throws SQLException {
        VeiculoDaoJDBC veiculoDaoJDBC = this.veiculoDaoJDBC;
        veiculoDaoJDBC.registrarSaida(veiculoId);
    }

    public Integer getByVagaInicial(String placa) throws SQLException {
        VeiculoDaoJDBC veiculoDaoJDBC = new VeiculoDaoJDBC();
        int vagaInicial = veiculoDaoJDBC.getByVagaInicial(placa);
        return vagaInicial;
    }

    public String getTipoByPlaca(String placa) throws SQLException {
        VeiculoDaoJDBC veiculoDaoJDBC = new VeiculoDaoJDBC();
        String tipoVeiculo = veiculoDaoJDBC.getTipoByPlaca(placa);
        return tipoVeiculo;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public boolean getMensalista() {
        return mensalista;
    }

    public int getOcupacaoVagas() {
        return ocupacaoVagas;
    }

    public int getVagaInicial() {
        return vagaInicial;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public boolean setMensalista(boolean mensalista) {
        this.mensalista = mensalista;
        return mensalista;
    }

    public void setOcupacaoVagas(int ocupacaoVagas) {
        this.ocupacaoVagas = ocupacaoVagas;
    }

    public void setVagaInicial(int vagaInicial) {
        this.vagaInicial = vagaInicial;
    }
}
