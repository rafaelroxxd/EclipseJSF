package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drogaria.servico.FuncionarioServico;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;


@ManagedBean
@ViewScoped
public class FuncionarioBean 
{
	private Funcionario funcionarioCadastro;
	private List<Funcionario> listaFuncionarios;
	private List<Funcionario>  listaFuncionariosFiltrados;
	private String acao;
	private Long codigo;
	
	
	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Funcionario> getListaFuncionariosFiltrados() {
		return listaFuncionariosFiltrados;
	}

	public void setListaFuncionariosFiltrados(List<Funcionario> listaFuncionariosFiltrados) {
		this.listaFuncionariosFiltrados = listaFuncionariosFiltrados;
	}

	public void salvar() throws ClassNotFoundException, SQLException 
	{
		
		try 
		{
			FuncionarioServico funcionarioServico = new FuncionarioServico();
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
				
			funcionarioServico.salvar(funcionarioCadastro);
			
			funcionarioCadastro = new Funcionario();
			
		FacesUtil.adicionarMsgInfo("Funcionario salvo com sucesso");
		}catch(RuntimeException ex) 
		{
			FacesUtil.addError("Erro ao tentar incluir um funcionario: "+ ex.getMessage());
		}
		
	}
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public void novo() 
	{
		funcionarioCadastro = new Funcionario();
	}
	
	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}
	
	public Funcionario getFuncionarioCadastro() 
	{
		if(funcionarioCadastro == null)
		{
			funcionarioCadastro = new Funcionario();
		}	
		return funcionarioCadastro;
	}
	
	public void carregarPesquisa() 
	{
		try 
		{
			FuncionarioServico FuncionarioServico = new FuncionarioServico();
			//listaFuncionarios = FuncionarioServico.listar();
			
		}catch(RuntimeException ex) 
		{
			FacesUtil.addError("Erro ao tentar listar os funcionarios: "+ ex.getMessage());
		}
	}
	
	
	public void carregarCadastro() 
	{
		try 
		{
			
			acao = FacesUtil.getParam("funacao");
			
			
			
			if(codigo != null)
			{
				
				FuncionarioServico FuncionarioServico = new FuncionarioServico();
				//funcionarioCadastro = FuncionarioServico.buscarPorCodigo(codigo);
			}	
			
		}catch(RuntimeException ex) 
		{
			
		}
	}
	
	
	public void excluir() 
	{
		
		try 
		{
	
			FuncionarioServico FuncionarioServico = new FuncionarioServico();
			//FuncionarioServico.excluir(funcionarioCadastro);
			
			
			
		FacesUtil.adicionarMsgInfo("Funcionario removido com sucesso");
		}catch(RuntimeException ex) 
		{
			FacesUtil.addError("Erro ao tentar remover o funcionario: "+ ex.getMessage());
			ex.printStackTrace();
		
		}
		
	}
	
	
	public void editar() 
	{
		
		try 
		{
			FuncionarioServico funcionarioServico = new FuncionarioServico();
			
			funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			//FuncionarioServico.editar(funcionarioCadastro);
			
			
			
		FacesUtil.adicionarMsgInfo("Funcionario editado com sucesso");
		}catch(RuntimeException ex) 
		{
			FacesUtil.addError("Erro ao tentar editar um funcionario: "+ ex.getMessage());
		}
		
	}
	
	public String getAcao() {
		return acao;
	}
	
	public void setAcao(String acao) {
		this.acao = acao;
	}
	
	
	
}
