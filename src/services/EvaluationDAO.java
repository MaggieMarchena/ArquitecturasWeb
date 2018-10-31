package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Evaluation;
import entities.Paper;
import entities.Subject;
import entities.User;

public class EvaluationDAO implements DAO<Evaluation,Integer>{
	
	private static EvaluationDAO daoEvaluation;
	
	private EvaluationDAO(){}

	public static EvaluationDAO getInstance() {
		if(daoEvaluation==null)
			daoEvaluation=new EvaluationDAO();
		return daoEvaluation;
	}

	@Override
	public Evaluation findById(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		Evaluation evaluation=entityManager.find(Evaluation.class, id);
		entityManager.close();
		return evaluation;	
	}
	
	public Evaluation findByPaper(Paper paper) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("SELECT * FROM Evaluation WHERE paper= :paper", Evaluation.class);
		query.setParameter("paper", paper);
		entityManager.getTransaction().commit();
		Evaluation evaluation=(Evaluation)query.getSingleResult();
		entityManager.close();
		return evaluation;
	}

	@Override
	public Evaluation persist(Evaluation evaluation) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(evaluation);
		entityManager.getTransaction().commit();
		entityManager.close();
		return evaluation;
	}
	
	public Evaluation update(Paper paper, Evaluation newEntityValues) {
		EntityManager entityManager = EMF.createEntityManager();
		Query query = entityManager.createNativeQuery("SELECT * FROM evaluation WHERE paper= :paper", Evaluation.class);
		query.setParameter("paper", paper);
		entityManager.getTransaction().commit();
		Evaluation evaluation=(Evaluation)query.getSingleResult();
		if (evaluation != null) {
			entityManager.getTransaction().begin();
			entityManager.persist(newEntityValues);
			entityManager.getTransaction().commit();
			entityManager.close();
			return evaluation;
		}
		entityManager.close();
		return null;
	}

	@Override
	public List<Evaluation> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(Integer id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Evaluation update(Integer id, Evaluation newEntityValues) {
		throw new UnsupportedOperationException();
	}

}
