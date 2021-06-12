package br.com.salao.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TB_V1_FUNCIONARIO")
public class Funcionario {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "DATA_NASC")
	private String dataNasc;
	
	@Column(name = "EMAIL")
	private String email;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

 
}
