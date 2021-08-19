/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Pessoa;
import modelo.Reclamacao;
import visao_controle.CadReclamacao;

/**
 *
 * @author suporte
 */
public class ReclamacaoCompletaDAO {
   
    
    public Reclamacao carregar(Reclamacao reclamacao) throws Exception {
         CadReclamacao cadReclamacao = new CadReclamacao();

        String sql = "select * from reclamacao inner join pessoa on reclamacao.id_pessoa = pessoa.id_pessoa " +
        "where pessoa.id_pessoa = ? ";

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        consulta.setInt(1, cadReclamacao.reclamacaoInfo.getId() );

        ResultSet resultadoConsulta = consulta.executeQuery();

        //Faz a varredura na tabelinha de resultado da consulta
      
    Reclamacao reclamacaoPesquisar = new Reclamacao();
        while (resultadoConsulta.next()) {
         
            Pessoa pessoa = new Pessoa();
            reclamacaoPesquisar.setPessoa(new Pessoa());
            
            reclamacaoPesquisar.setPessoa(pessoa);
            reclamacaoPesquisar.getPessoa().setId(resultadoConsulta.getInt("id_pessoa"));
            reclamacaoPesquisar.getPessoa().setNome(resultadoConsulta.getString("nome"));
            reclamacaoPesquisar.getPessoa().setTelefone(resultadoConsulta.getString("telefone"));
            reclamacaoPesquisar.getPessoa().setCpf_cnpj(resultadoConsulta.getString("cpf_cnpj"));
          
            reclamacaoPesquisar.setDescricao(resultadoConsulta.getString("descricao"));
            reclamacaoPesquisar.setData_reclamacao(resultadoConsulta.getDate("data_atendimento"));
            

        }

        return reclamacaoPesquisar;

    }
//    public ArrayList<Pessoa> preenC(String filtro) throws Exception{
//    
//     ArrayList<Pessoa>  buscaPessoa = new ArrayList();
//
//        String sql = "select * from pessoa "
//                + "where registro_ativo and "
//                + "(lower(cpf_cnpj) like ? or lower(nome) like ?)";
//        //like ? or lower(c.nome) like ?
//
//        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
//        consulta.setString(1, "%" + filtro.toLowerCase() + "%");
//         consulta.setString(2, "%" + filtro.toLowerCase() + "%");
//       
//           ResultSet resultadoConsulta = consulta.executeQuery();
//
//       
//        while (resultadoConsulta.next()) {
//
//            Pessoa  pessoaPesquisar = new Pessoa();
//         
//            pessoaPesquisar.setId(resultadoConsulta.getInt("id_pessoa"));
//            pessoaPesquisar.setNome(resultadoConsulta.getString("nome"));
//            pessoaPesquisar.setCpf_cnpj(resultadoConsulta.getString("cpf_cnpj"));
//     
//            buscaPessoa.add(pessoaPesquisar);
//
//        }
//
//        return buscaPessoa;
    
    
  //  }
}


   
    
    

