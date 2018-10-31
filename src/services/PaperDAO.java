package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Paper;
import entities.User;

public class PaperDAO implements DAO<Paper,Integer>{
	
	private static PaperDAO daoPaper;
	
	private PaperDAO(){}

	public static PaperDAO getInstance() {
		if(daoPaper==null)
			daoPaper=new PaperDAO();
		return daoPaper;
	}

	@Override
	public Paper findById(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		Paper paper=entityManager.find(Paper.class, id);
		entityManager.close();
		return paper;
	
	}
	
	public Paper findByName(String name) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("SELECT * FROM paper WHERE name= :name", Paper.class);
		query.setParameter("name", name);
		entityManager.getTransaction().commit();
		Paper paper=(Paper)query.getSingleResult();
		entityManager.close();
		return paper;
	}

	@Override
	public Paper persist(Paper paper) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		//error Unknown entity: entities.Article hacer Paper abstracto?
		entityManager.persist(paper);
		entityManager.getTransaction().commit();
		entityManager.close();
		return paper;
	}
	
	@Override
	public List<Paper> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("SELECT * FROM Paper", Paper.class);
		entityManager.getTransaction().commit();
		List <Paper>papers=query.getResultList();
		entityManager.close();
		return papers;
	}


	public Paper update(String name, Paper newEntityValues) {
		EntityManager entityManager = EMF.createEntityManager();
		Query query = entityManager.createNativeQuery("SELECT * FROM paper WHERE name= :name", Paper.class);
		query.setParameter("name", name);
		entityManager.getTransaction().commit();
		Paper paper=(Paper)query.getSingleResult();
		if (paper != null) {
			entityManager.getTransaction().begin();
			entityManager.persist(newEntityValues);
			entityManager.getTransaction().commit();
			entityManager.close();
			return paper;
		}
		entityManager.close();
		return null;
	}

	public boolean delete(String name) {
		EntityManager entityManager = EMF.createEntityManager();
		Paper paper= this.findByName(name);
		if(paper!=null) {
			entityManager.getTransaction().begin();
			entityManager.remove(paper);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		}
		else {
			entityManager.close();
			return false;
		}
	}

	@Override
	public Paper update(Integer id, Paper newEntityValues) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(Integer id) {
		throw new UnsupportedOperationException();
	}

}
