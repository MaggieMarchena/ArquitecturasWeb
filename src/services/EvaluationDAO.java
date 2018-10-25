package services;

import java.util.List;

import javax.persistence.EntityManager;

import entities.Evaluation;
import entities.Subject;

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
