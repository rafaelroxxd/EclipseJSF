package br.com.drogaria.domain;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "tbl_produtos")
@NamedQueries({@NamedQuery(name="Produto.listar",query="SELECT produto FROM Produto produto where produto.fornecedor.codigo != 4"),
			   @NamedQuery(name="Produto.buscarPorCodigo",query="SELECT produto FROM Produto produto WHERE produto.codigo = :codigo"),
			   @NamedQuery(name = "Produto.buscarPorDescricao",query="SELECT produto FROM Produto produto WHERE lower(produto.descricao) like CONCAT(:descricao,'%')"),
			   @NamedQuery(name = "Produto.updateQuantidade",query="update Produto SET prod_quantidade = :quantidade WHERE prod_codigo = :codigo"),
			   @NamedQuery(name = "Produto.deleteByCodigo",query="delete Produto p WHERE p.codigo = :codigo"),
			   @NamedQuery(name = "Produto.updateQuantidadeTransient",query="update Produto SET prod_quantidade_transient = :quantidadetransient WHERE prod_codigo = :codigo")

})

public class Produto 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "prod_codigo")
	private Long codigo;
	
	@NotEmpty(message="O campo descrição é obrigatório")
	@Size(min=5,max = 50,message="Tamanho inválido para o campo descrição de 5 até 50")	
	@Column(name= "prod_descricao", length=50,nullable = false)
	private String  descricao;
	
	
	@NotNull(message = "o campo preçoC é obrigatório")
	@DecimalMin(value = "0.0", message="informe um valor maior ou igual a zero para o campo preço")
	@DecimalMax(value = "99999.99", message="informe um valor menor que 100 mil para o campo preço")
	@Digits(integer = 5 , fraction = 2,message="Informe um valor válido para o campo preço")
	@Column(name = "prod_precoC", precision = 7  , scale = 2,nullable = false )
	private BigDecimal precoc;
	
	@NotNull(message = "o campo preçoV é obrigatório")
	@DecimalMin(value = "0.0", message="informe um valor maior ou igual a zero para o campo preço")
	@DecimalMax(value = "99999.99", message="informe um valor menor que 100 mil para o campo preço")
	@Digits(integer = 5 , fraction = 2,message="Informe um valor válido para o campo preço")
	@Column(name = "prod_precoV", precision = 7  , scale = 2,nullable = false )
	private BigDecimal precov;
	
	
	@NotNull(message="O campo quantidade é obrigatório")
	@Min(value=0,message="Informe um valor maior ou igual a zero para o campo quantidade")
	@Max(value= 9999,message="Informe um valor menor que dez mil para o campo quantidade")
	@Column(name = "prod_quantidade",nullable = false,updatable = true)
	private Long quantidade;
	
	
	
	@ManyToOne()   
	@JoinColumn(name = "fornecedor_for_codigo",referencedColumnName = "for_codigo",nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Fornecedor fornecedor;
	
	@Transient
	private Long quantidadeAddEstoque;

	
	
	@Column(name = "prod_quantidade_transient",nullable = true,updatable = true)
	private Long quantidadetransient;
	

	
	





	public Long getQuantidadetransient() {
		return quantidadetransient;
	}

	public void setQuantidadetransient(Long quantidadetransient) {
		this.quantidadetransient = quantidadetransient;
	}

	public Long getQuantidadeAddEstoque() {
		return quantidadeAddEstoque;
	}

	public void setQuantidadeAddEstoque(Long quantidadeAddEstoque) {
		this.quantidadeAddEstoque = quantidadeAddEstoque;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public BigDecimal getPrecoc() {
		return precoc;
	}

	public void setPrecoc(BigDecimal precoc) {
		this.precoc = precoc;
	}

	public BigDecimal getPrecov() {
		return precov;
	}

	public void setPrecov(BigDecimal precov) {
		this.precov = precov;
	}





	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", precoc=" + precoc + ", precov=" + precov
				+ ", quantidade=" + quantidadetransient + ", fornecedor=" + fornecedor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
		result = prime * result + ((precoc == null) ? 0 : precoc.hashCode());
		result = prime * result + ((precov == null) ? 0 : precov.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((quantidadeAddEstoque == null) ? 0 : quantidadeAddEstoque.hashCode());
		result = prime * result + ((quantidadetransient == null) ? 0 : quantidadetransient.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fornecedor == null) {
			if (other.fornecedor != null)
				return false;
		} else if (!fornecedor.equals(other.fornecedor))
			return false;
		if (precoc == null) {
			if (other.precoc != null)
				return false;
		} else if (!precoc.equals(other.precoc))
			return false;
		if (precov == null) {
			if (other.precov != null)
				return false;
		} else if (!precov.equals(other.precov))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (quantidadeAddEstoque == null) {
			if (other.quantidadeAddEstoque != null)
				return false;
		} else if (!quantidadeAddEstoque.equals(other.quantidadeAddEstoque))
			return false;
		if (quantidadetransient == null) {
			if (other.quantidadetransient != null)
				return false;
		} else if (!quantidadetransient.equals(other.quantidadetransient))
			return false;
		return true;
	}

	

	
	
	
	
}
