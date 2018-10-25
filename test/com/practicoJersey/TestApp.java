package com.practicoJersey;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import app.App;
import entities.Article;
import entities.Paper;
import entities.Poster;
import entities.Subject;
import entities.Summary;
import entities.User;
import services.PaperDAO;
import services.SubjectDAO;
import services.UserDAO;

public class TestApp {
	
	private static EntityManagerFactory emf;
	
	@BeforeClass
	public static void setEntityManeger() {
		emf = Persistence.createEntityManagerFactory("TPE");
	}
	
	@Test
	public void createEntities() {
		EntityManager entityManager = emf.createEntityManager();
		
		//Subjects
		
		Subject s1 = new Subject();
		s1.setName("Java");
		s1.setExpert(true);
		
		Subject s2 = new Subject();
		s2.setName("JavaScript");
		s2.setExpert(true);
		
		Subject s3 = new Subject();
		s3.setName("Web");
		
		Subject s4 = new Subject();
		s4.setName("BackEnd");
		
		Subject s5 = new Subject();
		s5.setName("FrontEnd");
		
		Subject s6 = new Subject();
		s6.setName("OOP");
		
		Subject s7 = new Subject();
		s7.setName("Ember");
		s7.setExpert(true);
		
		Subject s8 = new Subject();
		s8.setName("Phyton");
		s8.setExpert(true);
		
		//Users
		
		User u1 = new User();
		u1.setDni(1);
		u1.setName("Grohl, Dave");
		u1.setWorkPlace("Foo Fighters");
		u1.addKnownSubject(s1);
		u1.addKnownSubject(s2);
		u1.setEvaluator(true);
		
		User u2 = new User();
		u2.setDni(2);
		u2.setName("Hawkings, Taylor");
		u2.setWorkPlace("Foo Fighters");
		u2.addKnownSubject(s2);
		
		User u3 = new User();
		u3.setDni(3);
		u3.setName("Shiflett, Chris");
		u3.setWorkPlace("Foo Fighters");
		u3.addKnownSubject(s3);
		
		User u4 = new User();
		u4.setDni(4);
		u4.setName("Smear, Pat");
		u4.setWorkPlace("Foo Fighters");
		u4.addKnownSubject(s4);
		
		User u5 = new User();
		u5.setDni(5);
		u5.setName("Grohl, Dave");
		u5.setWorkPlace("Foo Fighters");
		u5.addKnownSubject(s5);
		
		User u6 = new User();
		u6.setDni(6);
		u6.setName("Mendel, Nate");
		u6.setWorkPlace("Foo Fighters");
		u6.addKnownSubject(s2);
		u6.addKnownSubject(s6);
		u6.addKnownSubject(s5);
		
		User u7 = new User();
		u7.setDni(7);
		u7.setName("Tyler, Steven");
		u7.setWorkPlace("Aerosmith");
		u7.addKnownSubject(s6);
		u7.setEvaluator(true);
		
		User u8 = new User();
		u8.setDni(8);
		u8.setName("Bouvier, Pierre");
		u8.setWorkPlace("Simple Plan");
		u8.addKnownSubject(s7);
		
		User u9 = new User();
		u9.setDni(9);
		u9.setName("Bower, Jamie");
		u9.setWorkPlace("Counterfeit");
		u9.addKnownSubject(s5);
		u9.addKnownSubject(s8);
		u9.addKnownSubject(s1);
		
		User u10 = new User();
		u10.setDni(10);
		u10.setName("Holland, Dexter");
		u10.setWorkPlace("The Offspring");
		u10.addKnownSubject(s7);
		u10.addKnownSubject(s7);
		
		//Papers
		
		Paper p1 = new Article();
		p1.setName("Guitarra1");
		p1.addAuthor(u1);
		p1.addAuthor(u3);
		u1.addPaperAuthored(p1);
		u3.addPaperAuthored(p1);
		p1.addKeyWord(s1);
		p1.addKeyWord(s2);
		
		Paper p2 = new Article();
		p2.setName("Guitarra2");
		p2.addAuthor(u2);
		u2.addPaperAuthored(p2);
		p2.addKeyWord(s3);
		
		Paper p3 = new Article();
		p3.setName("Guitarra3");
		p3.addAuthor(u4);
		u4.addPaperAuthored(p3);
		p3.addKeyWord(s4);
		
		Paper p4 = new Poster();
		p4.setName("Guitarra4");
		p4.addAuthor(u7);
		u7.addPaperAuthored(p4);
		p4.addKeyWord(s5);
		
		Paper p5 = new Poster();
		p5.setName("Guitarra5");
		p5.addAuthor(u6);
		u6.addPaperAuthored(p5);
		p5.addKeyWord(s8);
		
		Paper p6 = new Poster();
		p6.setName("Guitarra6");
		p6.addAuthor(u10);
		u10.addPaperAuthored(p6);
		p6.addKeyWord(s7);
		p6.addKeyWord(s3);
		p6.addKeyWord(s5);
		
		Paper p7 = new Summary();
		p7.setName("Guitarra7");
		p7.addAuthor(u5);
		u5.addPaperAuthored(p7);
		p7.addKeyWord(s4);
		
		Paper p8 = new Summary();
		p8.setName("Guitarra8");
		p8.addAuthor(u1);
		u1.addPaperAuthored(p8);
		p8.addKeyWord(s3);
		p8.addKeyWord(s2);
		
		Paper p9 = new Summary();
		p9.setName("Guitarra9");
		p9.addAuthor(u4);
		u4.addPaperAuthored(p9);
		p9.addKeyWord(s1);
		
		Paper p10 = new Summary();
		p10.setName("Guitarra10");
		p10.addAuthor(u7);
		u7.addPaperAuthored(p10);
		p10.addKeyWord(s7);
		p10.addKeyWord(s5);
		
		SubjectDAO.getInstance().persist(s1, entityManager);
		SubjectDAO.getInstance().persist(s2, entityManager);
		SubjectDAO.getInstance().persist(s3, entityManager);
		SubjectDAO.getInstance().persist(s4, entityManager);
		SubjectDAO.getInstance().persist(s5, entityManager);
		SubjectDAO.getInstance().persist(s6, entityManager);
		SubjectDAO.getInstance().persist(s7, entityManager);
		SubjectDAO.getInstance().persist(s8, entityManager);
		
		UserDAO.getInstance().persist(u1, entityManager);
		UserDAO.getInstance().persist(u2, entityManager);
		UserDAO.getInstance().persist(u3, entityManager);
		UserDAO.getInstance().persist(u4, entityManager);
		UserDAO.getInstance().persist(u5, entityManager);
		UserDAO.getInstance().persist(u6, entityManager);
		UserDAO.getInstance().persist(u7, entityManager);
		UserDAO.getInstance().persist(u8, entityManager);
		UserDAO.getInstance().persist(u9, entityManager);
		UserDAO.getInstance().persist(u10, entityManager);
		
		PaperDAO.getInstance().persist(p1, entityManager);
		PaperDAO.getInstance().persist(p2, entityManager);
		PaperDAO.getInstance().persist(p3, entityManager);
		PaperDAO.getInstance().persist(p4, entityManager);
		PaperDAO.getInstance().persist(p5, entityManager);
		PaperDAO.getInstance().persist(p6, entityManager);
		PaperDAO.getInstance().persist(p7, entityManager);
		PaperDAO.getInstance().persist(p8, entityManager);
		PaperDAO.getInstance().persist(p9, entityManager);
		PaperDAO.getInstance().persist(p10, entityManager);
	}
	
	@Test
	public void getUser() {
		EntityManager entityManager = emf.createEntityManager();
		User user = App.getUserByDni(7,entityManager);
		assertTrue(user.getName().equals("Tyler, Steven"));
		assertTrue(user.isEvaluator());
		assertTrue(user.getWorkPlace().equals("Aerosmith"));
		entityManager.close();
	}
	
}
