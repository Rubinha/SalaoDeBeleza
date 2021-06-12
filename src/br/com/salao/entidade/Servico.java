package br.com.salao.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "TB_V1_SERVICO")
public class Servico {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

	@Column(name = "TIPO_SERVICO")
	private String tipoServico;
	
	@Column(name = "VALOR_SERVICO")
	private double valorServico;
	
	@ManyToOne
	@JoinColumn(name = "ID_FUNCIONARIO")
	private Funcionario funcionario;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public double getValorServico() {
		return valorServico;
	}

	public void setValorServico(double valorServico) {
		this.valorServico = valorServico;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
 
}
