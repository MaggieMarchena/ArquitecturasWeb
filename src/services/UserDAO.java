package services;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Evaluation;
import entities.Paper;
import entities.User;

public class UserDAO implements DAO<User,Integer>{
	
	private static UserDAO daoUser;
	
	private UserDAO(){}

	public static UserDAO getInstance() {
		if(daoUser==null)
			daoUser=new UserDAO();
		return daoUser;
	}
	
	@Override
	public User persist(User user) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		return user;
	}
	
	@Override
	public User findById(Integer id) {	
		EntityManager entityManager = EMF.createEntityManager();
		User user=entityManager.find(User.class, id);
		user.getEvaluation().size();
		user.getKnownSubjects().size();
		return user;
	
	}
	
	@Override
	public List<User> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("SELECT * FROM User", User.class);
		entityManager.getTransaction().commit();
		List<User> users=query.getResultList();
		entityManager.close();
		return users;
	}

	@Override
	public boolean delete(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		User user= this.findById(id);
		if(user!=null) {
			entityManager.getTransaction().begin();
			entityManager.remove(user);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		}
		else {
			entityManager.close();
			return false;
		}
	}
	
	public List<Evaluation> findEvaluationsByDate(int id,Calendar from,Calendar to){
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("SELECT * FROM Evaluation WHERE evaluator_dni= :evaluatorId and date BETWEEN :from and :to", Evaluation.class);
		query.setParameter("evaluatorId", id);
		query.setParameter("from", from);
		query.setParameter("to", to);
		entityManager.getTransaction().commit();
		List<Evaluation>evaluations=query.getResultList();
		entityManager.close();
		return evaluations;
	}
	
	public void assignPaperToEvaluate(int id) {
		EntityManager entityManager = EMF.createEntityManager();
		User evaluator = entityManager.find(User.class, id);
		entityManager.getTransaction().begin();
		evaluator.addPaperToEvaluate();
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public User update(Integer id, User newEntityValues) {
		EntityManager entityManager = EMF.createEntityManager();
		User user = entityManager.find(User.class, id);
		if (user != null) {
			entityManager.getTransaction().begin();
			entityManager.persist(newEntityValues);
			entityManager.getTransaction().commit();
			entityManager.close();
			return user;
		}
		entityManager.close();
		return null;
	}

}
