package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Paper;
import entities.Subject;
import entities.User;

public class SubjectDAO implements DAO<Subject,Integer>{
	
	private static SubjectDAO daoSubject;
	
	private SubjectDAO(){}

	public static SubjectDAO getInstance() {
		if(daoSubject==null)
			daoSubject=new SubjectDAO();
		return daoSubject;
	}
	
	@Override
	public Subject persist(Subject subject) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(subject);
		entityManager.getTransaction().commit();
		entityManager.close();
		return subject;
	}

	public Subject findById(Integer id) {	
		EntityManager entityManager = EMF.createEntityManager();
		Subject subject=entityManager.find(Subject.class, id);
		entityManager.close();
		return subject;
	
	}
	
	public Subject findByName(String name) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("SELECT * FROM Subject WHERE name= :name", Subject.class);
		query.setParameter("name", name);
		entityManager.getTransaction().commit();
		Subject subject=(Subject)query.getSingleResult();
		entityManager.close();
		return subject;
	}
	
	public List<Subject> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("SELECT * FROM Subject", Subject.class);
		entityManager.getTransaction().commit();
		List<Subject> subjects=query.getResultList();
		entityManager.close();
		return subjects;
	}

	
	public boolean delete(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		Subject subject= this.findById(id);
		if(subject!=null) {
			entityManager.getTransaction().begin();
			entityManager.remove(subject);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		}
		else {
			entityManager.close();
			return false;
		}
	}

	public Subject update(String name, Subject newEntityValues) {
		EntityManager entityManager = EMF.createEntityManager();
		Query query = entityManager.createNativeQuery("SELECT * FROM subject WHERE name= :name", Subject.class);
		query.setParameter("name", name);
		entityManager.getTransaction().commit();
		Subject subject = (Subject)query.getSingleResult();
		if (subject != null) {
			entityManager.getTransaction().begin();
			entityManager.persist(newEntityValues);
			entityManager.getTransaction().commit();
			entityManager.close();
			return subject;
		}
		entityManager.close();
		return null;
	}

	@Override
	public Subject update(Integer id, Subject newEntityValues) {
		// TODO Auto-generated method stub
		return null;
	}

}
