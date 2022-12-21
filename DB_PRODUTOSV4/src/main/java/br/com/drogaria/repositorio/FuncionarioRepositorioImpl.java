package br.com.drogaria.repositorio;


import br.com.drogaria.domain.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FuncionarioRepositorioImpl implements Repositorio<Funcionario>{

    private Connection conn;
    private String retornaFuncaoFuncionario;

    public FuncionarioRepositorioImpl(Connection conn) {
        this.conn = conn;
    }

    public FuncionarioRepositorioImpl() {
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public ArrayList<Funcionario> listar() throws SQLException, ClassNotFoundException
    {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from tbl_funcionarios")) {
            while (rs.next()) {
                Funcionario f = criarFuncionario(rs);
                funcionarios.add(f);
            }
        }
        return funcionarios;
    }





  


    private Funcionario criarFuncionario(ResultSet rs) throws SQLException {
        Funcionario f = new Funcionario();
        f.setCodigo(rs.getLong("fun_codigo"));
        f.setCpf(rs.getString("fun_cpf"));
        f.setFuncao(rs.getString("fun_funcao"));
        f.setNome(rs.getString("fun_nome"));
        f.setSenha(rs.getString("fun_senha"));
        return f;
    }


	@Override
	public boolean salvar(Funcionario funcionario) throws SQLException , ClassNotFoundException 
{
	       String sql =  "INSERT INTO tbl_funcionarios(fun_codigo, fun_cpf,fun_funcao, fun_nome, fun_senha) VALUES(?,?,?,?,?)";
	        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) 
	        {
	            
	            Long id = (long) 0;	
	            

	          
	                try (ResultSet rs = stmt.getGeneratedKeys()) 
	                {
	                    if (rs.next()) 
	                    {
	                      id =    rs.getLong(1);
	                    }
	                }
	                
	                stmt.setLong(1, id);
	                stmt.setString(2, funcionario.getCpf());
	                stmt.setString(3, funcionario.getFuncao());
	                stmt.setString(4, funcionario.getNome());
	                stmt.setString(5, funcionario.getSenha());
	                stmt.executeUpdate();
	                
	                return true;
	            }catch(SQLException ex) 
	        	{
	            
	            	ex.printStackTrace();
	            	
	            	
	        	}
	            
	            
	            

	            
	        
	       return false;
	}

	@Override
	public Funcionario autenticar(String login, String senha) throws SQLException , ClassNotFoundException
	{
		Funcionario funcionario = null;

	        try (Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery("select * from tbl_funcionarios")) {
	            while (rs.next()) 
	            {
	            	if(rs.getString("fun_cpf").equalsIgnoreCase(login) && rs.getString("fun_senha").equalsIgnoreCase(senha)) 
	            	{
	            		funcionario.setCodigo(rs.getLong("fun_codigo"));
	            		funcionario.setCpf(rs.getString("fun_cpf"));
	            		funcionario.setFuncao(rs.getString("fun_funcao"));
	            		funcionario.setNome(rs.getString("fun_nome"));
	            		funcionario.setSenha(rs.getString("fun_senha"));
	            		return funcionario;
	            	}
	            	
	            	
	            }
	        }

		
		
		return funcionario;
	}
	
	

	public String getRetornaFuncaoFuncionario() {
		return retornaFuncaoFuncionario;
	}



	
	

}
