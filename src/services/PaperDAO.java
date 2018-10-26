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

	public Paper findById(Integer id, EntityManager entityManager) {		
		Paper paper=entityManager.find(Paper.class, id);
		return paper;
	
	}
	
	public Paper findByName(String name,EntityManager entityManager) {
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("SELECT * FROM paper WHERE name= :name", Paper.class);
		query.setParameter("name", name);
		entityManager.getTransaction().commit();
		Paper paper=(Paper)query.getSingleResult();
		return paper;
	}

	public Paper persist(Paper paper, EntityManager entityManager) {
		entityManager.getTransaction().begin();
		entityManager.persist(paper);
		entityManager.getTransaction().commit();
		return paper;
	}

	@Override
	public List<Paper> findAll() {
		throw new UnsupportedOperationException();
	}
	
	public List<Paper> findAll(EntityManager entityManager) {
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("SELECT * FROM Paper", Paper.class);
		entityManager.getTransaction().commit();
		List <Paper>papers=query.getResultList();
		return papers;
	}


	public Paper update(String name, Paper newEntityValues, EntityManager entityManager) {
		Query query = entityManager.createNativeQuery("SELECT * FROM paper WHERE name= :name", Paper.class);
		query.setParameter("name", name);
		entityManager.getTransaction().commit();
		Paper paper=(Paper)query.getSingleResult();
		if (paper != null) {
			entityManager.getTransaction().begin();
			entityManager.persist(newEntityValues);
			entityManager.getTransaction().commit();
			return paper;
		}
	return null;
}

	@Override
	public Paper persist(Paper entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paper findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paper update(Integer id, Paper newEntityValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
