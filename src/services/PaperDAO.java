package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Paper;
import entities.Subject;
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
		paper.getKeyWords().size();
		paper.getAuthors().size();
		paper.getEvaluators().size();
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
		List<Paper>papers = query.getResultList();
		entityManager.close();
		return papers;
	}


	public Paper update(String name, Paper newEntityValues) {
		EntityManager entityManager = EMF.createEntityManager();
//		Query query = entityManager.createNativeQuery("SELECT * FROM paper WHERE name= :name", Paper.class);
//		query.setParameter("name", name);
//		entityManager.getTransaction().commit();
//		Paper paper=(Paper)query.getSingleResult();
		Paper paper = this.findByName(name);
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
		EntityManager entityManager = EMF.createEntityManager();
		Paper paper = entityManager.find(Paper.class, id);
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
	
	public void addSubjectToPaper(int id_paper, int id_subject) {
		EntityManager entityManager = EMF.createEntityManager();
		Paper paper = entityManager.find(Paper.class, id_paper);
		Subject subject = entityManager.find(Subject.class, id_subject);
		entityManager.getTransaction().begin();
		paper.addKeyWord(subject);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public void assignAuthorToPaper(int id_paper, int id_author) {
		EntityManager entityManager = EMF.createEntityManager();
		Paper paper = entityManager.find(Paper.class, id_paper);
		User author = entityManager.find(User.class, id_author);
		entityManager.getTransaction().begin();
		paper.addAuthor(author);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public void assignEvaluatorToPaper(int id_paper, int id_evaluator) {
		EntityManager entityManager = EMF.createEntityManager();
		Paper paper = entityManager.find(Paper.class, id_paper);
		User evaluator = entityManager.find(User.class, id_evaluator);
		entityManager.getTransaction().begin();
		paper.addEvaluator(evaluator);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	//encontrar la consulta con join
	public List<User> getEvaluators(int id_paper){
		EntityManager entityManager = EMF.createEntityManager();
		Query query = entityManager.createNativeQuery("SELECT * FROM paper_evaluator WHERE Paper_id= :id_paper", User.class);
		query.setParameter("id_paper", id_paper);
		entityManager.getTransaction().commit();
		List<User> result = query.getResultList();
		return result;
	}

	@Override
	public boolean delete(Integer id) {
		throw new UnsupportedOperationException();
	}



}
