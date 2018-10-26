package com.practicoJersey;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import app.App;
import entities.Article;
import entities.Evaluation;
import entities.Paper;
import entities.Poster;
import entities.Subject;
import entities.Summary;
import entities.User;
import services.EvaluationDAO;
import services.PaperDAO;
import services.SubjectDAO;
import services.UserDAO;

public class TestApp {
	
	private App app;
	
	private EntityManagerFactory emf;
	
	private List<User> users;
	private List<Paper> papers;
	private List<Subject> subjects;
	private List<Evaluation> evaluations;
	
	@BeforeClass
	public void setEntityManeger() {
		this.emf = Persistence.createEntityManagerFactory("TPE");
		this.app = new App();
		this.users = new ArrayList<>();
		this.papers = new ArrayList<>();
		this.evaluations = new ArrayList<>();
	}
	
	@Test
	public void createSubjects() {
		EntityManager entityManager = emf.createEntityManager();
		
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
		
		SubjectDAO.getInstance().persist(s1, entityManager);
		SubjectDAO.getInstance().persist(s2, entityManager);
		SubjectDAO.getInstance().persist(s3, entityManager);
		SubjectDAO.getInstance().persist(s4, entityManager);
		SubjectDAO.getInstance().persist(s5, entityManager);
		SubjectDAO.getInstance().persist(s6, entityManager);
		SubjectDAO.getInstance().persist(s7, entityManager);
		SubjectDAO.getInstance().persist(s8, entityManager);
		
		subjects.add(s1);
		subjects.add(s2);
		subjects.add(s3);
		subjects.add(s4);
		subjects.add(s5);
		subjects.add(s6);
		subjects.add(s7);
		subjects.add(s8);
		
		//test subject 5: "FrontEnd", expert = false
		Subject testSubject = this.app.getSubjectByName(this.subjects.get(4).getName(), entityManager);
		assertTrue(testSubject.getName().equals("FrontEnd"));
		assertTrue(!testSubject.isExpert());
		
		entityManager.close();
	}
	
	@Test
	public void createEntitiesAndAssingRoles() {
		EntityManager entityManager = emf.createEntityManager();
		
		//Get Subjects
		
		Subject s1 = this.app.getSubjectByName(this.subjects.get(0).getName(), entityManager);
		Subject s2 = this.app.getSubjectByName(this.subjects.get(1).getName(), entityManager);
		Subject s3 = this.app.getSubjectByName(this.subjects.get(2).getName(), entityManager);
		Subject s4 = this.app.getSubjectByName(this.subjects.get(3).getName(), entityManager);
		Subject s5 = this.app.getSubjectByName(this.subjects.get(4).getName(), entityManager);
		Subject s6 = this.app.getSubjectByName(this.subjects.get(5).getName(), entityManager);
		Subject s7 = this.app.getSubjectByName(this.subjects.get(6).getName(), entityManager);
		Subject s8 = this.app.getSubjectByName(this.subjects.get(7).getName(), entityManager);
		
		//Create Users and add known subjects
		
		User u1 = new User();
		u1.setDni(1);
		u1.setName("Grohl, Dave");
		u1.setWorkPlace("Foo Fighters");
		u1.addKnownSubject(s1);
		u1.addKnownSubject(s2);
		u1.addKnownSubject(s7);
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
		u3.addKnownSubject(s5);
		u3.addKnownSubject(s7);
		
		User u4 = new User();
		u4.setDni(4);
		u4.setName("Smear, Pat");
		u4.setWorkPlace("Foo Fighters");
		u4.addKnownSubject(s4);
		
		User u5 = new User();
		u5.setDni(5);
		u5.setName("Jaffee, Rami");
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
		u7.addKnownSubject(s1);
		u7.addKnownSubject(s3);
		u7.addKnownSubject(s4);
		u7.addKnownSubject(s6);
		u7.addKnownSubject(s8);
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
		u10.addKnownSubject(s4);
		u10.addKnownSubject(s6);
		u10.setEvaluator(true);
		
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
		
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
		users.add(u5);
		users.add(u6);
		users.add(u7);
		users.add(u8);
		users.add(u9);
		users.add(u10);
		
		//Test u7: "Tyler, Steven", evaluator = true, workplace = "Aerosmith"
		User testUser = this.app.getUserByDni(7, entityManager);
		assertTrue(testUser.getName().equals("Tyler, Steven"));
		assertTrue(testUser.isEvaluator());
		assertTrue(testUser.getWorkPlace().equals("Aerosmith"));
		
		entityManager.close();
	}
	
	@Test
	public void createPapers() {
		EntityManager entityManager = emf.createEntityManager();
		
		//Get Subjects
		
		Subject s1 = this.app.getSubjectByName(this.subjects.get(0).getName(), entityManager);
		Subject s2 = this.app.getSubjectByName(this.subjects.get(1).getName(), entityManager);
		Subject s3 = this.app.getSubjectByName(this.subjects.get(2).getName(), entityManager);
		Subject s4 = this.app.getSubjectByName(this.subjects.get(3).getName(), entityManager);
		Subject s5 = this.app.getSubjectByName(this.subjects.get(4).getName(), entityManager);
		Subject s7 = this.app.getSubjectByName(this.subjects.get(6).getName(), entityManager);
		Subject s8 = this.app.getSubjectByName(this.subjects.get(7).getName(), entityManager);
		
		//Get Users
		User u1 = this.app.getUserByDni(this.users.get(0).getDni(), entityManager);
		User u2 = this.app.getUserByDni(this.users.get(1).getDni(), entityManager);
		User u3 = this.app.getUserByDni(this.users.get(2).getDni(), entityManager);
		User u4 = this.app.getUserByDni(this.users.get(3).getDni(), entityManager);
		User u5 = this.app.getUserByDni(this.users.get(4).getDni(), entityManager);
		User u6 = this.app.getUserByDni(this.users.get(5).getDni(), entityManager);
		User u7 = this.app.getUserByDni(this.users.get(6).getDni(), entityManager);
		User u10 = this.app.getUserByDni(this.users.get(9).getDni(), entityManager);
		
		//Create Papers
		
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
		p6.addAuthor(u3);
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
		
		papers.add(p1);
		papers.add(p2);
		papers.add(p3);
		papers.add(p4);
		papers.add(p5);
		papers.add(p6);
		papers.add(p7);
		papers.add(p8);
		papers.add(p9);
		papers.add(p10);
		
		//Test p5: "Guitarra5", author = u6, keyWord = s8
		Paper paper = this.app.getPaperByName(papers.get(4).getName(), entityManager);
		assertTrue(paper.getName().equals("Guitarra5"));
		// u6
		User user = this.app.getUserByDni(6, entityManager);
		assertTrue(paper.getAuthors().get(0).equals(user));
		// s8
		Subject keyWord = this.app.getSubjectByName(subjects.get(9).getName(), entityManager);
		assertTrue(paper.getKeyWords().get(0).equals(keyWord));
		
		entityManager.close();
	}
	
	@Test
	public void assignEvaluatorToPoster() {
		EntityManager entityManager = emf.createEntityManager();
		
		//Create dates
		
		Calendar d = Calendar.getInstance();
				
		//Test p6 with u10, should not be assigned due to evaluator in same workplace
		
		//Get p6: poster, "Guitarra6", author = u3, keyWords = (s7, s3, s5)
		Paper p6 = this.app.getPaperByName(papers.get(5).getName(), entityManager);
		
		//Get u1: "Grohl, Dave", workplace: "Foo Fighters", knownSubjects: (s1, s2, s7)
		int evaluator1Dni = users.get(0).getDni();
		User evaluator1 = this.app.getUserByDni(evaluator1Dni, entityManager);
		
		//assign
		Evaluation evaluation1 = this.app.assingEvaluatorToPaper(evaluator1Dni, p6, d);
		if(evaluation1 != null) {
			EvaluationDAO.getInstance().persist(evaluation1, entityManager);
			this.evaluations.add(evaluation1);
		}
		
		assertTrue(evaluation1.equals(null));
		assertTrue(p6.getEvaluators().isEmpty());
		assertTrue(evaluator1.getPapersToEvaluate().isEmpty());
		
		//Test p5 with u10, should not be assigned due to evaluator not knowing subject
		
		//Get p5: poster, "Guitarra5", author = u6, keyWords = (s8)
		Paper p5 = this.app.getPaperByName(papers.get(4).getName(), entityManager);
		
		//Get u10: "Holland, Dexter", workplace: "The Offspring", knownSubjects: (s4, s6)
		int evaluator10Dni = users.get(9).getDni();
		User evaluator10 = this.app.getUserByDni(evaluator10Dni, entityManager);
		
		//assign
		Evaluation evaluation2 = this.app.assingEvaluatorToPaper(evaluator10Dni, p5, d);
		if (evaluation2 != null) {
			EvaluationDAO.getInstance().persist(evaluation2, entityManager);
			this.evaluations.add(evaluation2);
		}
		
		assertTrue(evaluation2.equals(null));
		assertTrue(p5.getEvaluators().isEmpty());
		assertTrue(evaluator10.getPapersToEvaluate().isEmpty());
		
		//Test p5 with u7, should be assigned

		// Get u7: "Tyler, Steven", workplace: "Aerosmith", knownSubjects: (s6, s8)
		int evaluator7Dni = users.get(6).getDni();
		User evaluator7 = this.app.getUserByDni(evaluator7Dni, entityManager);

		// assign
		Evaluation evaluation3 = this.app.assingEvaluatorToPaper(evaluator7Dni, p5, d);
		if (evaluation3 != null) {
			EvaluationDAO.getInstance().persist(evaluation3, entityManager);
			this.evaluations.add(evaluation3);
		}
		
		Evaluation evaluation3DataBase = this.app.getEvaluationByPaper(p5, entityManager);
		Paper updatedP5 = this.app.getPaperByName(papers.get(4).getName(), entityManager);
		User updatedEvaluator7 = this.app.getUserByDni(evaluator7Dni, entityManager);

		assertTrue(evaluation3DataBase.getEvaluator().equals(evaluator7));
		assertTrue(updatedP5.getEvaluators().get(0).equals(updatedEvaluator7));
		assertTrue(updatedEvaluator7.getPapersToEvaluate().get(0).equals(updatedP5));
		
		entityManager.close();
	}
	
	@Test
	public void assignEvaluatorToArticle() {
		EntityManager entityManager = emf.createEntityManager();
		
		//Create dates
		
		Calendar d = new GregorianCalendar(2018, 10, 16);
		
		//Test p2 with u10, should not be assigned due to evaluator not knowing subjects
		
		//Get p2: poster, "Guitarra2", author = u2, keyWords = (s3)
		Paper p2 = this.app.getPaperByName(papers.get(1).getName(), entityManager);
		
		//Get u10: "Holland, Dexter", workplace: "The Offspring", knownSubjects: (s4, s6)
		int evaluator10Dni = users.get(9).getDni();
		User evaluator10 = this.app.getUserByDni(evaluator10Dni, entityManager);
		
		//assign
		Evaluation evaluation2 = this.app.assingEvaluatorToPaper(evaluator10Dni, p2, d);
		if (evaluation2 != null) {
			EvaluationDAO.getInstance().persist(evaluation2, entityManager);
			this.evaluations.add(evaluation2);
		}
		
		Paper updatedP2 = this.app.getPaperByName(papers.get(1).getName(), entityManager);
		User updatedU10 = this.app.getUserByDni(evaluator10Dni, entityManager);
		
		assertTrue(evaluation2.equals(null));
		assertTrue(updatedP2.getEvaluators().isEmpty());
		assertTrue(updatedU10.getPapersToEvaluate().isEmpty());
		
		//Test p2 with u7, should be assigned

		// Get u7: "Tyler, Steven", workplace: "Aerosmith", knownSubjects: (s6, s8)
		int evaluator7Dni = users.get(6).getDni();
		User evaluator7 = this.app.getUserByDni(evaluator7Dni, entityManager);

		// assign
		Evaluation evaluation3 = this.app.assingEvaluatorToPaper(evaluator7Dni, updatedP2, d);
		if (evaluation3 != null) {
			EvaluationDAO.getInstance().persist(evaluation3, entityManager);
			this.evaluations.add(evaluation3);
		}
		
		Evaluation evaluation3DataBase = this.app.getEvaluationByPaper(updatedP2, entityManager);
		updatedP2 = this.app.getPaperByName(papers.get(4).getName(), entityManager);
		User updatedEvaluator7 = this.app.getUserByDni(evaluator7Dni, entityManager);

		assertTrue(evaluation3DataBase.getEvaluator().equals(evaluator7));
		assertTrue(updatedP2.getEvaluators().get(0).equals(updatedEvaluator7));
		assertTrue(updatedEvaluator7.getPapersToEvaluate().get(0).equals(updatedP2));
		
		entityManager.close();
	}
	
	@Test
	public void assignMoreThan3() {
		EntityManager entityManager = emf.createEntityManager();
		
		//Create dates
		
		Calendar d = new GregorianCalendar(2018, 9, 5);
		
		//Get u7
		int evaluator7Dni = users.get(6).getDni();
		
		//Get p9: poster, "Guitarra9", author = u4, keyWords = (s1)
		Paper p9 = this.app.getPaperByName(papers.get(8).getName(), entityManager);
		
		//assign
		Evaluation evaluation1 = this.app.assingEvaluatorToPaper(evaluator7Dni, p9, d);
		if (evaluation1 != null) {
			EvaluationDAO.getInstance().persist(evaluation1, entityManager);
			this.evaluations.add(evaluation1);
		}
		
		Evaluation evaluation1DataBase = this.app.getEvaluationByPaper(p9, entityManager);
		Paper updatedP9 = this.app.getPaperByName(papers.get(8).getName(), entityManager);
		User updatedEvaluator7 = this.app.getUserByDni(evaluator7Dni, entityManager);

		assertTrue(evaluation1DataBase.getEvaluator().equals(updatedEvaluator7));
		assertTrue(updatedP9.getEvaluators().get(0).equals(updatedEvaluator7));
		assertTrue(updatedEvaluator7.getPapersToEvaluate().get(0).equals(updatedP9));
		
		//Test p7 with u7, should not be assigned
		
		//Get p7: poster, "Guitarra7", author = u5, keyWords = (s4)
		Paper p7 = this.app.getPaperByName(papers.get(6).getName(), entityManager);
		
		//Get u7
		updatedEvaluator7 = this.app.getUserByDni(evaluator7Dni, entityManager);
		
		//assign
		Evaluation evaluation2 = this.app.assingEvaluatorToPaper(evaluator7Dni, p7, d);
		if (evaluation2 != null) {
			EvaluationDAO.getInstance().persist(evaluation2, entityManager);
			this.evaluations.add(evaluation2);
		}

		Evaluation evaluation2DataBase = this.app.getEvaluationByPaper(p7, entityManager);
		Paper updatedP7 = this.app.getPaperByName(papers.get(6).getName(), entityManager);
		updatedEvaluator7 = this.app.getUserByDni(evaluator7Dni, entityManager);

		assertTrue(evaluation2DataBase.getEvaluator().equals(updatedEvaluator7));
		assertTrue(updatedP7.getEvaluators().get(0).equals(updatedEvaluator7));
		assertTrue(updatedEvaluator7.getPapersToEvaluate().get(0).equals(updatedP7));
		
		entityManager.close();
	}
	
	@Test
	public void getAssignedPapers() {
		EntityManager entityManager = emf.createEntityManager();
		
		//Get p2, 95, p9
		Paper p2 = this.app.getPaperByName(this.papers.get(1).getName(), entityManager);
		Paper p5 = this.app.getPaperByName(this.papers.get(4).getName(), entityManager);
		Paper p9 = this.app.getPaperByName(this.papers.get(8).getName(), entityManager);
		
		// Get u7: "Tyler, Steven", workplace: "Aerosmith", knownSubjects: (s6, s8), papersToEvaluate: (p2, p5, p9)
		int evaluator7Dni = users.get(6).getDni();
		User evaluator7 = this.app.getUserByDni(evaluator7Dni, entityManager);
		
		List<Paper> papersToEvaluate = this.app.getAssignedPapers(evaluator7Dni, entityManager);
		
		assertTrue(papersToEvaluate.get(0).equals(p2));
		assertTrue(papersToEvaluate.get(1).equals(p5));
		assertTrue(papersToEvaluate.get(2).equals(p9));
		
		entityManager.close();
	}
	
	
	
}
