package br.com.salao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.salao.entidade.Funcionario;
import br.com.salao.entidade.Servico;

public class SalaoDAO {
	
	 /**
	   * Método utilizado para obter o entity manager.
	   * @return
	   */
	  private EntityManager getEntityManager() {
	    EntityManagerFactory factory = null;
	    EntityManager entityManager = null;
	    
	    try {
	      //Obtém o factory a partir da unidade de persistência.
	    factory = Persistence.createEntityManagerFactory("crudHibernatePU");
	      //Cria um entity manager.
	      entityManager = factory.createEntityManager();
	      //Fecha o factory para liberar os recursos utilizado.
	    } catch(Exception e) {
	    	System.out.println(e.getMessage());
	      factory.close();
	    }
	    return entityManager;
	  }
	
	public void salvar(Funcionario funcionario){
	    EntityManager entityManager = getEntityManager();
	    try {
	      // Inicia uma transação com o banco de dados.
	      entityManager.getTransaction().begin();
	      System.out.println("Salvando a funcionario.");
	      // Verifica se a pessoa ainda não está salva no banco de dados.
	      if(funcionario.getId() == null) {
	        //Salva os dados da pessoa.
	        entityManager.persist(funcionario);
	      } else {
	        //Atualiza os dados da pessoa.
	    	  funcionario = entityManager.merge(funcionario);
	      }
	      // Finaliza a transação.
	      entityManager.getTransaction().commit();
	    } finally {
	      entityManager.close();
	    }
	}
	
	public void salvarServico(Servico servico){
	    EntityManager entityManager = getEntityManager();
	    try {
	      // Inicia uma transação com o banco de dados.
	      entityManager.getTransaction().begin();
	      System.out.println("Salvando a servico.");
	      // Verifica se a pessoa ainda não está salva no banco de dados.
	      if(servico.getId() == null) {
	        //Salva os dados da pessoa.
	        entityManager.persist(servico);
	      } else {
	        //Atualiza os dados da pessoa.
	    	  servico = entityManager.merge(servico);
	      }
	      // Finaliza a transação.
	      entityManager.getTransaction().commit();
	    } finally {
	      entityManager.close();
	    }
	}
	
	public void deletarFuncionario(Funcionario funcionario)  {
		 EntityManager entityManager = getEntityManager();
		    try {
		      // Inicia uma transação com o banco de dados.
		      entityManager.getTransaction().begin();
		      // Consulta a pessoa na base de dados através do seu ID.
		      Funcionario object = entityManager.find(Funcionario.class, funcionario.getId());
		      System.out.println("Excluindo os dados de: " + funcionario.getNome());
		      // Remove a pessoa da base de dados.
		      entityManager.remove(object);
		      // Finaliza a transação.
		      entityManager.getTransaction().commit();
		    } finally {
		      entityManager.close();
		    }
	}
	
	public void deletarServico(Servico servico)  {
		 EntityManager entityManager = getEntityManager();
		    try {
		      // Inicia uma transação com o banco de dados.
		      entityManager.getTransaction().begin();
		      // Consulta a pessoa na base de dados através do seu ID.
		      Servico object = entityManager.find(Servico.class, servico.getId());
		      System.out.println("Excluindo os dados de: " + servico.getTipoServico());
		      // Remove a pessoa da base de dados.
		      entityManager.remove(object);
		      // Finaliza a transação.
		      entityManager.getTransaction().commit();
		    } finally {
		      entityManager.close();
		    }
	}
	
	public void deletarTodosServicos(Integer id) {
		EntityManager session = getEntityManager();
		Query q = session.createQuery("Delete FROM Servico c WHERE c.funcionario = "+ id);
		q.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> buscarFuncionario(Funcionario funcionario) {
		EntityManager entityManager = getEntityManager();
		List<Funcionario> results = entityManager.createQuery("SELECT c FROM Funcionario c").getResultList();
	    return results;
		
	}
	
	public Funcionario buscarFuncionarioNome(String nome) {
		EntityManager entityManager = getEntityManager();
		Funcionario results = (Funcionario) entityManager.createQuery("SELECT c FROM Funcionario c WHERE c.nome = '"+ nome + "'").getSingleResult();
	    return results;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Servico> buscarServico(Servico servico) {
		EntityManager entityManager = getEntityManager();
		List<Servico> results = entityManager.createQuery("SELECT c FROM Servico c").getResultList();
	    return results;
		
	}


	
}