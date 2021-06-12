package br.com.salao.bean;


import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.salao.dao.SalaoDAO;
import br.com.salao.entidade.Funcionario;


@ManagedBean
@SessionScoped
public class FuncionarioBean {
	
	private Funcionario funcionario;
	private List<Funcionario> lstFuncionario;

	private SalaoDAO salaoDAO;
	private String estadoTela = "buscar"; //Inserir, Editar, Buscar
	
	public FuncionarioBean() {
		salaoDAO = new SalaoDAO();
	}
	
	public void novo() {
		funcionario = new Funcionario();
		mudarParaInseri();
	}
	
	public void salvar() {
		try {
			salaoDAO.salvar(funcionario);
			mudarParaBusca();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void editar(Funcionario funcionario) {
		try {
			this.funcionario = funcionario;
			mudarParaEdita();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void deletar(Funcionario funcionario) {
		try {
			//salaoDAO.deletarTodosServicos(funcionario.getId());
			salaoDAO.deletarFuncionario(funcionario);
			funcionario = new Funcionario();
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
			lstFuncionario = salaoDAO.buscarFuncionario(funcionario);
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

		
		
		public Funcionario getFuncionario() {
			return funcionario;
		}

		public void setFuncionario(Funcionario funcionario) {
			this.funcionario = funcionario;
		}

		public List<Funcionario> getLstFuncionario() {
			return lstFuncionario;
		}

		public void setLstFuncionario(List<Funcionario> lstFuncionario) {
			this.lstFuncionario = lstFuncionario;
		}
		
		
}
