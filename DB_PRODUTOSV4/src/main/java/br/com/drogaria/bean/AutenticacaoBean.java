package br.com.drogaria.bean;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.drogaria.servico.FuncionarioServico;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.util.FacesUtil;


@ManagedBean
@SessionScoped
public class AutenticacaoBean
{
private Funcionario funcionarioLogado;

	public Funcionario getFuncionarioLogado() {
		if(funcionarioLogado == null)
		{
			funcionarioLogado = new Funcionario();
		}	
		 return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}
	
	public String autenticar() throws ClassNotFoundException 
	{
		
		try 
		{
		FuncionarioServico funcionarioServico = new FuncionarioServico();
		
		funcionarioLogado = funcionarioServico.autenticar(funcionarioLogado.getCpf(),DigestUtils.md5Hex(funcionarioLogado.getSenha().toString()));
			
		
		if(funcionarioLogado == null)
		{
			FacesUtil.adicionarMsgInfo("Senha ou CPF invalidos");
		
			return null;	
		}else 
		{	
		
	   // VendaBean.isCarregado = false;
		FacesUtil.adicionarMsgInfo("Funcionario autenticado com sucesso");
		return "/pages/principal.xhtml?faces-redirect=true";	
	    
		}
		}catch(RuntimeException ex) 
		{
			FacesUtil.addError("Erro ao tentar entrar no sistema");
			return null;
		}
		
		
	}
	
	public String  sair() 
	{
		//VendaBean.isCarregado = false;
		funcionarioLogado = null;
		return "/pages/autenticacao.xhtml?faces-redirect=true";
		
	}

}
