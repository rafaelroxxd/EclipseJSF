package br.com.drogaria.domain;

import java.math.BigDecimal;

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
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_itens")
@NamedQueries({@NamedQuery(name="Item.listar",query="SELECT item FROM Item item"),
	   @NamedQuery(name="Item.buscarPorCodigo",query="SELECT item FROM Item item WHERE item.codigo = :codigo"),
	   @NamedQuery(name = "Item.updateQuantidadeTransient",query="update Item SET ite_quantidade_transient = :quantidadeTransient WHERE ite_codigo = :codigo")
	
})
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ite_codigo")	
	private Long codigo;
	
	@Column(name = "ite_valor_parcial",precision = 7,scale = 2, nullable = false)
	private BigDecimal valor;
	
	
	@Column(name = "ite_quantidade",nullable = false)
	private Long quantidade;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "venda_ven_codigo",referencedColumnName="ven_codigo" ,nullable = false)
	private Venda venda;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produto_prod_codigo",referencedColumnName="prod_codigo",nullable = false )
	private Produto produto;
	
	@Column(name = "ite_quantidade_transient",nullable = true)
	private Long quantidadetransient;
	
	


	public Long getQuantidadetransient() {
		return quantidadetransient;
	}

	public void setQuantidadetransient(Long quantidadetransient) {
		this.quantidadetransient = quantidadetransient;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return ""+quantidadetransient + " X "+produto.getDescricao()+";";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((quantidadetransient == null) ? 0 : quantidadetransient.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result + ((venda == null) ? 0 : venda.hashCode());
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
		Item other = (Item) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (quantidadetransient == null) {
			if (other.quantidadetransient != null)
				return false;
		} else if (!quantidadetransient.equals(other.quantidadetransient))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}




	
	
}
