/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author rafael
 */
public class Conexao {
    
    
    private static Connection conexao;

   public static Connection getConexao() throws Exception {
        if (conexao == null){
           Class.forName("org.postgresql.Driver"); 
           conexao = DriverManager.getConnection(
                   "jdbc:postgresql://localhost:5432/novo_procon", //URL
                   "postgres", //LOGIN
                   "1234"); //SENHA
        }
        return conexao;
    }
 // "jdbc:postgresql://192.168.0.164:5432/postgres", //URL
 //localhost:5432/novo_procon
}
