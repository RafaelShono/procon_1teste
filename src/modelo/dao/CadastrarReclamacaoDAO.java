/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import modelo.Pessoa;
import modelo.Reclamacao;
import visao_controle.CadReclamacao;

/**
 *
 * @author rafael
 */
public class CadastrarReclamacaoDAO {

    public Pessoa pessoaEmEdicao;

    public void setPessoaEmEdicaoDAO(Pessoa pessoaEmEdicao) {
        this.pessoaEmEdicao = pessoaEmEdicao;

    }

    public void inserir(Reclamacao reclamacao) throws Exception {

        Pessoa pessoa = new Pessoa();
        //CadReclamacao cadeclamacao = new CadReclamacao();

        // PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);     
        String sql = "insert into reclamacao (id_pessoa, descricao, data_atendimento) values (?,?,?)";
        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        consulta.setInt(1, reclamacao.getPessoaId());
        consulta.setString(2, reclamacao.getDescricao());
        consulta.setDate(3, (Date) new java.sql.Date(reclamacao.getData_reclamacao().getTime()));
        consulta.executeUpdate();

    }

}
