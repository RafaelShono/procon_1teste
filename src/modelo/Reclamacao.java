/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import java.util.Date;

/**
 *
 * @author rafael
 */
public class Reclamacao {
    
    private int id;
    private Pessoa pessoa;
    private int pessoaId;
    private String descricao;
    private Date data_reclamacao;
    public int getPessoaId() {
        return  pessoaId;
    }
    public void setPessoaId(int pessoaId) {
        this. pessoaId =  pessoaId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_reclamacao() {
        return data_reclamacao;
    }

    public void setData_reclamacao(Date data_reclamacao) {
        this.data_reclamacao = data_reclamacao;
    }

}
