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

	public Evaluation findById(Integer id, EntityManager entityManager) {		
		Evaluation evaluation=entityManager.find(Evaluation.class, id);
		return evaluation;	
	}
	
	public Evaluation findByPaper(Paper paper,EntityManager entityManager) {
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("SELECT * FROM Evaluation WHERE paper= :paper", Evaluation.class);
		query.setParameter("paper", paper);
		entityManager.getTransaction().commit();
		Evaluation evaluation=(Evaluation)query.getSingleResult();
		return evaluation;
	}

	public Evaluation persist(Evaluation evaluation, EntityManager entityManager) {
		entityManager.getTransaction().begin();
		entityManager.persist(evaluation);
		entityManager.getTransaction().commit();
		return evaluation;
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
	public Evaluation update(Integer id, Evaluation entity) {
		throw new UnsupportedOperationException();
	}
	
	public Evaluation update(Paper paper, Evaluation newEntityValues, EntityManager entityManager) {
		Query query = entityManager.createNativeQuery("SELECT * FROM evaluation WHERE paper= :paper", Paper.class);
		query.setParameter("paper", paper);
		entityManager.getTransaction().commit();
		Evaluation evaluation=(Evaluation)query.getSingleResult();
		if (evaluation != null) {
			entityManager.getTransaction().begin();
			entityManager.persist(newEntityValues);
			entityManager.getTransaction().commit();
			return evaluation;
		}
	return null;
	}

	@Override
	public Evaluation persist(Evaluation entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evaluation findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
