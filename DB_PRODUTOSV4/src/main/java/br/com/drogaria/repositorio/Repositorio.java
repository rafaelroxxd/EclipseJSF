package br.com.drogaria.repositorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Funcionario;


public interface Repositorio<T> {
    void setConn(Connection conn);

    ArrayList<T> listar() throws SQLException, ClassNotFoundException;

   // T porId(Long id) throws SQLException;

    boolean salvar(T t) throws SQLException, ClassNotFoundException;
    Funcionario autenticar(String login,String senha) throws SQLException,ClassNotFoundException;
  

  //  void excluir(Long id) throws SQLException;
}
