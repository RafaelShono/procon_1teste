/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Pessoa;
import modelo.Reclamacao;
import visao_controle.CadReclamacao;

/**
 *
 * @author rafael
 */
public class CadastrarPessoaDAO {

    public void gravar(Pessoa pessoa) throws Exception {
        if (pessoa.getId() == 0) {
            inserir(pessoa);
        }
    }

    public void inserir(Pessoa pessoa) throws Exception {

        String sql = "insert into pessoa (nome, telefone, cpf_cnpj) values "
                + "(?,?,?)";

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        consulta.setString(1, pessoa.getNome());
        consulta.setString(2, pessoa.getTelefone());
        consulta.setString(3, pessoa.getCpf_cnpj());

        consulta.executeUpdate();

        ResultSet retornoId = consulta.getGeneratedKeys();
        if (retornoId.next()) {

            pessoa.setId(retornoId.getInt(1));

        }

    }

    public void alterarPessoa(Pessoa pessoa) throws Exception {
        String sql = "update pessoa set "
                + "nome = ?, telefone = ?, cpf_cnpj = ? "
                + "where id_pessoa =?  ";

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);

        consulta.setString(1, pessoa.getNome());
        consulta.setString(2, pessoa.getTelefone());
        consulta.setString(3, pessoa.getCpf_cnpj());
        consulta.setInt(4, pessoa.getId());

        consulta.executeUpdate();//Roda o insert no BD
    }

    public void alterarReclamacao(Reclamacao reclamacao) throws Exception {
        String sql = "update reclamacao set "
                + "descricao = ?, data_atendimento = ?"
                + "where id_reclamacao = ? ";

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        consulta.setString(1, reclamacao.getDescricao());
        consulta.setDate(2, (Date) new java.sql.Date(reclamacao.getData_reclamacao().getTime()));
        consulta.setInt(3, reclamacao.getId());

        consulta.executeUpdate();//Roda o insert no BD
    }

    public ArrayList<Pessoa> pesquisa(String filtro) throws Exception {

        ArrayList<Pessoa> buscaPessoa = new ArrayList();

        String sql = "select *  from pessoa  join reclamacao on reclamacao.id_pessoa = pessoa.id_pessoa "
                + " where (lower(nome) like ? or  lower(cpf_cnpj) SIMILAR TO  ? )  ORDER BY pessoa.id_pessoa  DESC "
                + " LIMIT 10000000";
        //like ? or lower(c.nome) like ?
        //select * from reclamacao inner join pessoa on reclamacao.id_pessoa = pessoa.id_pessoa " +
        //"where pessoa.id_pessoa = ?
        //select *  from pessoa  join reclamacao on reclamacao.id_pessoa = pessoa.id_pessoa 
        //  where (nome) like 'Carlos Massa' ORDER BY pessoa.id_pessoa  DESC
//LIMIT 10000000;

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        consulta.setString(1, "%" + filtro.toLowerCase() + '%');
        consulta.setString(2, "%" + filtro.toLowerCase() + "%");

        ResultSet resultadoConsulta = consulta.executeQuery();

        System.out.println(resultadoConsulta);
        while (resultadoConsulta.next()) {

            Pessoa pessoaPesquisar = new Pessoa();

            pessoaPesquisar.setId(resultadoConsulta.getInt("id_pessoa"));
            pessoaPesquisar.setNome(resultadoConsulta.getString("nome"));
            pessoaPesquisar.setTelefone(resultadoConsulta.getString("telefone"));

            pessoaPesquisar.setCpf_cnpj(resultadoConsulta.getString("cpf_cnpj"));

            Reclamacao classReclamacao = new Reclamacao();

            pessoaPesquisar.setReclamacao(classReclamacao);
            classReclamacao.setId(resultadoConsulta.getInt("id_reclamacao"));
            classReclamacao.setDescricao(resultadoConsulta.getString("descricao"));
            classReclamacao.setData_reclamacao(resultadoConsulta.getDate("data_atendimento"));

            buscaPessoa.add(pessoaPesquisar);

        }

        return buscaPessoa;

    }
}
