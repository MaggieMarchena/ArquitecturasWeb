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

	public Subject findById(Integer id, EntityManager entityManager) {		
		Subject subject=entityManager.find(Subject.class, id);
		return subject;
	
	}
	
	public Subject findByName(String name,EntityManager entityManager) {
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("SELECT * FROM Subject WHERE name= :name", Subject.class);
		query.setParameter("name", name);
		entityManager.getTransaction().commit();
		Subject subject=(Subject)query.getSingleResult();
		return subject;
	}

	public Subject persist(Subject subject, EntityManager entityManager) {
		entityManager.getTransaction().begin();
		entityManager.persist(subject);
		entityManager.getTransaction().commit();
		return subject;
	}
	
	public List<Subject> findAll(EntityManager entityManager) {
		entityManager.getTransaction().begin();
		Query query = entityManager.createNativeQuery("SELECT * FROM Subject", Subject.class);
		entityManager.getTransaction().commit();
		List<Subject> subjects=query.getResultList();
		return subjects;
	}

	@Override
	public List<Subject> findAll() {
		throw new UnsupportedOperationException();
	}

	
	public boolean delete(Integer id, EntityManager entityManager) {
		Subject subject= this.findById(id, entityManager);
		if(subject!=null) {
			entityManager.getTransaction().begin();
			entityManager.remove(subject);
			entityManager.getTransaction().commit();
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Subject update(Integer id, Subject entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Subject persist(Subject entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
