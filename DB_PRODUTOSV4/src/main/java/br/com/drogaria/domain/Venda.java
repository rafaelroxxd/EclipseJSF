package br.com.drogaria.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_vendas")
@NamedQueries({@NamedQuery(name="Venda.listar",query="SELECT venda FROM Venda venda"),
	   @NamedQuery(name="Venda.buscarPorCodigo",query="SELECT venda FROM Venda venda WHERE venda.codigo = :codigo")
})
public class Venda 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ven_codigo")
	private Long codigo;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "ven_horario",nullable = false)
	private Date horario;
	
	@Column(name = "ven_total",precision = 7 , scale = 2,nullable = false)
	private BigDecimal valor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "funcionario_fun_codigo",referencedColumnName = "fun_codigo",nullable = false)
	private Funcionario funcionario;

	@Transient
	private Long quantidade;
	
	@Column(name = "valor_recebido",precision = 7 , scale = 2,nullable = true)
	private BigDecimal valorRecebido;
	
	@Column(name = "troco",precision = 7 , scale = 2,nullable = true)
	private  BigDecimal troco;
	
	@Column(name = "produtos_consumidos")
	private String produtosConsumidos; 
	

	public String getProdutosConsumidos() {
		if(produtosConsumidos==null)
		{
			produtosConsumidos = "";
		}	
		return produtosConsumidos;
	}

	public void setProdutosConsumidos(String produtosConsumidos) {
		this.produtosConsumidos = produtosConsumidos;
	}
	

	public BigDecimal getTroco() {
		if(troco == null)
		{
			troco = new BigDecimal("0.00");
		}	
		
		return troco;
	}

	public void setTroco(BigDecimal troco) {
		this.troco = troco;
	}

	public BigDecimal getValorRecebido() {
		if(valorRecebido == null)
		{
			valorRecebido = new BigDecimal("0.00");
		}	
		return valorRecebido;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getHorario() {
	  if(horario == null)
	  {
		  horario = new Date();
		  
	  }	  
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "Venda [codigo=" + codigo + ", horario=" + horario + ", valor=" + valor + ", funcionario=" + funcionario.getNome()
				+ "]";
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	

	
	

}
