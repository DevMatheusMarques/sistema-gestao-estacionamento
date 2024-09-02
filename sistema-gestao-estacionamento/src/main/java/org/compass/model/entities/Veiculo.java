package org.compass.model.entities;

import org.compass.model.dao.InterfaceVeiculo;
import org.compass.model.dao.implement.VeiculoDaoJDBC;

import java.sql.SQLException;
import java.util.Map;

public class Veiculo implements InterfaceVeiculo {
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

    public Boolean getPlacaVeiculo(String placa) throws SQLException {
        VeiculoDaoJDBC veiculoDaoJDBC = this.veiculoDaoJDBC;
        Map<String, Object> resultado = veiculoDaoJDBC.getVeiculoByPlaca(placa);

        if ((boolean) resultado.get("sucesso")) {
            // Se bem-sucedido, obtenha o objeto Veiculo e a mensagem
            String mensagem = (String) resultado.get("mensagem");

            System.out.println(mensagem);  // Exibe a mensagem
            System.out.println();

            return true;

        } else {
            // Se não encontrado, exiba a mensagem de erro
            String mensagem = (String) resultado.get("mensagem");
            System.out.println(mensagem);
            System.out.println();
        }
        return false;
    }

    public void CadastrarNovoVeiculo(Veiculo veiculo) throws SQLException {
        VeiculoDaoJDBC veiculoDaoJDBC = new VeiculoDaoJDBC();
        veiculoDaoJDBC.registraVeiculo(veiculo);

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
