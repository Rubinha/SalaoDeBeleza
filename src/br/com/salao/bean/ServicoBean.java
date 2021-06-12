package br.com.salao.bean;


import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.salao.dao.SalaoDAO;
import br.com.salao.entidade.Funcionario;
import br.com.salao.entidade.Servico;


@ManagedBean
@SessionScoped
public class ServicoBean {
	
	private Servico servico;
	private List<Servico> lstServico;

	private SalaoDAO salaoDAO;
	private String estadoTela = "buscar"; //Inserir, Editar, Buscar
	private String nome;
	
	
	public ServicoBean() {
		salaoDAO = new SalaoDAO();
	}
	
	public void novo() {
		servico = new Servico();
		mudarParaInseri();
	}
	
	public void salvar() {
		
		Funcionario funcionario = salaoDAO.buscarFuncionarioNome(nome);
		if(funcionario == null) {
			adicionarMensagem("Funcionario Não Encontrado!", FacesMessage.SEVERITY_ERROR);
		}
		servico.setFuncionario(funcionario);
		try {
			salaoDAO.salvarServico(servico);
			mudarParaBusca();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void editar(Servico servico) {
		try {
			this.servico = servico;
			mudarParaEdita();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deletar(Servico servico) {
		try {
			salaoDAO.deletarServico(servico);
			servico = new Servico();
			lstServico = salaoDAO.buscarServico(servico);
			adicionarMensagem("Deletado com sucesso!", FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
			e.printStackTrace();
		}	
	}

	public void buscar() {
		if(isBusca() == false){
			mudarParaBusca();
			return;
		}
		try {
			lstServico = salaoDAO.buscarServico(servico);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro){
		FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
		FacesContext.getCurrentInstance().addMessage(null,fm);
	}
	
	//Metodos para controle da tela
	public boolean isInseri(){
		return "inserir".equals(estadoTela);
	}
	
	public boolean isEdita(){
		return "editar".equals(estadoTela);
	}
	
	public boolean isBusca(){
		return "buscar".equals(estadoTela);
	}
	
	public void mudarParaInseri(){
		estadoTela = "inserir";
	}
	
	public void mudarParaEdita(){
		estadoTela = "editar";
	}
	
	public void mudarParaBusca(){
		estadoTela = "buscar";
	}

	// GETTERS AND SETTERS
	
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<Servico> getLstServico() {
		return lstServico;
	}

	public void setLstServico(List<Servico> lstServico) {
		this.lstServico = lstServico;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


		
}
