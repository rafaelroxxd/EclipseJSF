package br.com.drogaria.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_fornecedores")
@NamedNativeQueries({@NamedNativeQuery(name = "Fornecedor.listar",query= "SELECT fornecedor FROM Fornecedor fornecedor WHERE fornecedor.codigo != 4"),
	@NamedNativeQuery(name = "Fornecedor.buscarPorCodigo",query= "SELECT fornecedor FROM Fornecedor fornecedor WHERE fornecedor.codigo = codigo")

})
public class Fornecedor
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "for_codigo")
	private Long codigo;
	@NotEmpty(message = "O campo descrição é obrigatório")
	@Size(min = 5, max = 50, message = "Tamanho inválido para o campo descrição de 5 ha 50")
	@Column(name = "for_descricao",length = 50,nullable = false)
	private String descricao;
	
	@NotEmpty(message = "O campo telefone é obrigatório")
	@Size(min = 15, max = 16, message = "Tamanho inválido para o campo")
	@Column(name = "for_telefone",length=16,nullable = false)	
	private String telefone;
	
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	@Override
	public String toString() {
		return "Fornecedor [codigo=" + codigo + ", descricao=" + descricao + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		Fornecedor other = (Fornecedor) obj;
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
		return true;
	}
	

	
	

}
