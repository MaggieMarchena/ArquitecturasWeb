

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
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
import services.EMF;
import services.PaperDAO;
import services.SubjectDAO;
import services.UserDAO;

public class TestApp {
	
	private static App app;
	
	private static List<User> users;
	private static List<Paper> papers;
	private static List<Subject> subjects;
	private static List<Evaluation> evaluations;
	
	@BeforeClass
	public static void openEntityManagerFactory() {
		app = new App();
		subjects = new ArrayList<>();
		users = new ArrayList<>();
		papers = new ArrayList<>();
		evaluations = new ArrayList<>();
		app.initialize();
	}
	
	//Test methods start with a letter to set order!!

	@Test
	public void aCreateSubjects() {
		
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
		
		app.createSubject(s1);
		app.createSubject(s2);
		app.createSubject(s3);
		app.createSubject(s4);
		app.createSubject(s5);
		app.createSubject(s6);
		app.createSubject(s7);
		app.createSubject(s8);
		
		subjects.add(s1);
		subjects.add(s2);
		subjects.add(s3);
		subjects.add(s4);
		subjects.add(s5);
		subjects.add(s6);
		subjects.add(s7);
		subjects.add(s8);
		
		//test subject 5: "FrontEnd", expert = false
		Subject testSubject = app.getSubjectByName(subjects.get(4).getName());
		assertTrue(testSubject.getName().equals("FrontEnd"));
		assertTrue(!testSubject.isExpert());

	}
	
	//Inciso b) Crear 10 usuarios
	//Inciso d)i) Consulta de todos los datos de un autor/revisor
	@Test
	public void bCreateUsers() {
		
		//Get Subjects
		
		Subject s1 = app.getSubjectById(subjects.get(0).getId());
		Subject s2 = app.getSubjectById(subjects.get(1).getId());
		Subject s3 = app.getSubjectById(subjects.get(2).getId());
		Subject s4 = app.getSubjectById(subjects.get(3).getId());
		Subject s5 = app.getSubjectById(subjects.get(4).getId());
		Subject s6 = app.getSubjectById(subjects.get(5).getId());
		Subject s7 = app.getSubjectById(subjects.get(6).getId());
		Subject s8 = app.getSubjectById(subjects.get(7).getId());
		
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
		
		app.createUser(u1);
		app.createUser(u2);
		app.createUser(u3);
		app.createUser(u4);
		app.createUser(u5);
		app.createUser(u6);
		app.createUser(u7);
		app.createUser(u8);
		app.createUser(u9);
		app.createUser(u10);
		
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
		User testUser = app.getUserByDni(7);
		assertTrue(testUser.getName().equals("Tyler, Steven"));
		assertTrue(testUser.isEvaluator());
		assertTrue(testUser.getWorkPlace().equals("Aerosmith"));
	}
	
//	Inciso c) Crear 10 trabajos de investigación
//	Inciso e) Crear 10 trabajos de investigación
	@Test
	public void cCreatePapers() {
	
		//Create Papers
		
		Paper p1 = new Article();
		p1.setName("Guitarra1");

		Paper p2 = new Article();
		p2.setName("Guitarra2");

		Paper p3 = new Article();
		p3.setName("Guitarra3");

		Paper p4 = new Poster();
		p4.setName("Guitarra4");

		Paper p5 = new Poster();
		p5.setName("Guitarra5");

		Paper p6 = new Poster();
		p6.setName("Guitarra6");

		Paper p7 = new Summary();
		p7.setName("Guitarra7");

		Paper p8 = new Summary();
		p8.setName("Guitarra8");

		Paper p9 = new Summary();
		p9.setName("Guitarra9");

		Paper p10 = new Summary();
		p10.setName("Guitarra10");
	
		//Persist
		
		app.createPaper(p1);	
		app.createPaper(p2);
		app.createPaper(p3);
		app.createPaper(p4);
		app.createPaper(p5);
		app.createPaper(p6);
		app.createPaper(p7);
		app.createPaper(p8);
		app.createPaper(p9);
		app.createPaper(p10);
		
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
	
		//Test p5: "Guitarra5"
		Paper paper = app.getPaperById(p5.getId());
		assertTrue(paper.getName().equals("Guitarra5"));	
	}
	
	@Test
	public void dAddSubjectToPaper() {
		
		// Get Subjects

		Subject s1 = app.getSubjectById(subjects.get(0).getId());
		Subject s2 = app.getSubjectById(subjects.get(1).getId());
		Subject s3 = app.getSubjectById(subjects.get(2).getId());
		Subject s4 = app.getSubjectById(subjects.get(3).getId());
		Subject s5 = app.getSubjectById(subjects.get(4).getId());
		Subject s7 = app.getSubjectById(subjects.get(6).getId());
		Subject s8 = app.getSubjectById(subjects.get(7).getId());
		
		// Get Papers

		Paper p1 = app.getPaperById(papers.get(0).getId());
		Paper p2 = app.getPaperById(papers.get(1).getId());
		Paper p3 = app.getPaperById(papers.get(2).getId());
		Paper p4 = app.getPaperById(papers.get(3).getId());
		Paper p5 = app.getPaperById(papers.get(4).getId());
		Paper p6 = app.getPaperById(papers.get(5).getId());
		Paper p7 = app.getPaperById(papers.get(6).getId());
		Paper p8 = app.getPaperById(papers.get(7).getId());
		Paper p9 = app.getPaperById(papers.get(8).getId());
		Paper p10 = app.getPaperById(papers.get(9).getId());
		
		//Assign
		
		app.addSubjectToPaper(p1.getId(), s1.getId());
		app.addSubjectToPaper(p1.getId(), s2.getId());
		
		app.addSubjectToPaper(p2.getId(), s3.getId());
		
		app.addSubjectToPaper(p3.getId(), s4.getId());
		
		app.addSubjectToPaper(p4.getId(), s5.getId());
		
		app.addSubjectToPaper(p5.getId(), s8.getId());
		
		app.addSubjectToPaper(p6.getId(), s3.getId());
		app.addSubjectToPaper(p6.getId(), s5.getId());
		app.addSubjectToPaper(p6.getId(), s7.getId());
		
		app.addSubjectToPaper(p7.getId(), s4.getId());
		
		app.addSubjectToPaper(p8.getId(), s2.getId());
		app.addSubjectToPaper(p8.getId(), s3.getId());

		app.addSubjectToPaper(p9.getId(), s1.getId());

		app.addSubjectToPaper(p10.getId(), s5.getId());
		app.addSubjectToPaper(p10.getId(), s7.getId());
		
		//Test: p5 with s8
		Paper paper = app.getPaperById(p5.getId());
		Subject keyWord = app.getSubjectById(s8.getId());
		assertTrue(paper.getKeyWords().get(0).getName().equals(keyWord.getName()));
		
	}
	
	@Test
	public void eAssignAuthorToPaper() {

		// Get Users
		User u1 = app.getUserByDni(users.get(0).getDni());
		User u2 = app.getUserByDni(users.get(1).getDni());
		User u3 = app.getUserByDni(users.get(2).getDni());
		User u4 = app.getUserByDni(users.get(3).getDni());
		User u5 = app.getUserByDni(users.get(4).getDni());
		User u6 = app.getUserByDni(users.get(5).getDni());
		User u7 = app.getUserByDni(users.get(6).getDni());
		
		// Get Papers

		Paper p1 = app.getPaperById(papers.get(0).getId());
		Paper p2 = app.getPaperById(papers.get(1).getId());
		Paper p3 = app.getPaperById(papers.get(2).getId());
		Paper p4 = app.getPaperById(papers.get(3).getId());
		Paper p5 = app.getPaperById(papers.get(4).getId());
		Paper p6 = app.getPaperById(papers.get(5).getId());
		Paper p7 = app.getPaperById(papers.get(6).getId());
		Paper p8 = app.getPaperById(papers.get(7).getId());
		Paper p9 = app.getPaperById(papers.get(8).getId());
		Paper p10 = app.getPaperById(papers.get(9).getId());
		
		app.assignAuthorToPaper(p1.getId(), u1.getDni());
		app.assignAuthorToPaper(p1.getId(), u3.getDni());
		
		app.assignAuthorToPaper(p2.getId(), u2.getDni());
		
		app.assignAuthorToPaper(p3.getId(), u4.getDni());
		
		app.assignAuthorToPaper(p4.getId(), u7.getDni());
		
		app.assignAuthorToPaper(p5.getId(), u6.getDni());
		
		app.assignAuthorToPaper(p6.getId(), u3.getDni());
		
		app.assignAuthorToPaper(p7.getId(), u5.getDni());
		
		app.assignAuthorToPaper(p8.getId(), u1.getDni());
		
		app.assignAuthorToPaper(p9.getId(), u4.getDni());
		
		app.assignAuthorToPaper(p10.getId(), u7.getDni());
		
		//Test: p5 with u6
		Paper paper = app.getPaperById(p5.getId());
		User author = app.getUserInSet(paper.getAuthors(), u6);
		assertTrue(author.getDni() == u6.getDni());
		
	}
	
//	@Test
//	public void assignEvaluatorToPoster() {
//		EntityManager entityManager = emf.createEntityManager();
//		
//		//Create dates
//		
//		Calendar d = Calendar.getInstance();
//				
//		//Test p6 with u10, should not be assigned due to evaluator in same workplace
//		
//		//Get p6: poster, "Guitarra6", author = u3, keyWords = (s7, s3, s5)
//		Paper p6 = app.getPaperByName(papers.get(5).getName(), entityManager);
//		
//		//Get u1: "Grohl, Dave", workplace: "Foo Fighters", knownSubjects: (s1, s2, s7)
//		int evaluator1Dni = users.get(0).getDni();
//		User evaluator1 = app.getUserByDni(evaluator1Dni, entityManager);
//		
//		//assign
//		Evaluation evaluation1 = app.assingEvaluatorToPaper(evaluator1Dni, p6, d);
//		if(evaluation1 != null) {
//			EvaluationDAO.getInstance().persist(evaluation1, entityManager);
//			evaluations.add(evaluation1);
//		}
//		
//		assertTrue(evaluation1.equals(null));
//		assertTrue(p6.getEvaluators().isEmpty());
//		assertTrue(evaluator1.getPapersToEvaluate().isEmpty());
//		
//		//Test p5 with u10, should not be assigned due to evaluator not knowing subject
//		
//		//Get p5: poster, "Guitarra5", author = u6, keyWords = (s8)
//		Paper p5 = app.getPaperByName(papers.get(4).getName(), entityManager);
//		
//		//Get u10: "Holland, Dexter", workplace: "The Offspring", knownSubjects: (s4, s6)
//		int evaluator10Dni = users.get(9).getDni();
//		User evaluator10 = app.getUserByDni(evaluator10Dni, entityManager);
//		
//		//assign
//		Evaluation evaluation2 = app.assingEvaluatorToPaper(evaluator10Dni, p5, d);
//		if (evaluation2 != null) {
//			EvaluationDAO.getInstance().persist(evaluation2, entityManager);
//			evaluations.add(evaluation2);
//		}
//		
//		assertTrue(evaluation2.equals(null));
//		assertTrue(p5.getEvaluators().isEmpty());
//		assertTrue(evaluator10.getPapersToEvaluate().isEmpty());
//		
//		//Test p5 with u7, should be assigned
//
//		// Get u7: "Tyler, Steven", workplace: "Aerosmith", knownSubjects: (s6, s8)
//		int evaluator7Dni = users.get(6).getDni();
//		User evaluator7 = app.getUserByDni(evaluator7Dni, entityManager);
//
//		// assign
//		Evaluation evaluation3 = app.assingEvaluatorToPaper(evaluator7Dni, p5, d);
//		if (evaluation3 != null) {
//			EvaluationDAO.getInstance().persist(evaluation3, entityManager);
//			evaluations.add(evaluation3);
//		}
//		
//		Evaluation evaluation3DataBase = app.getEvaluationByPaper(p5, entityManager);
//		Paper updatedP5 = app.getPaperByName(papers.get(4).getName(), entityManager);
//		User updatedEvaluator7 = app.getUserByDni(evaluator7Dni, entityManager);
//
//		assertTrue(evaluation3DataBase.getEvaluator().equals(evaluator7));
//		assertTrue(updatedP5.getEvaluators().get(0).equals(updatedEvaluator7));
//		assertTrue(updatedEvaluator7.getPapersToEvaluate().get(0).equals(updatedP5));
//		
//		entityManager.close();
//	}
//	
//	@Test
//	public void assignEvaluatorToArticle() {
//		EntityManager entityManager = emf.createEntityManager();
//		
//		//Create dates
//		
//		Calendar d = new GregorianCalendar(2018, 10, 16);
//		
//		//Test p2 with u10, should not be assigned due to evaluator not knowing subjects
//		
//		//Get p2: poster, "Guitarra2", author = u2, keyWords = (s3)
//		Paper p2 = app.getPaperByName(papers.get(1).getName(), entityManager);
//		
//		//Get u10: "Holland, Dexter", workplace: "The Offspring", knownSubjects: (s4, s6)
//		int evaluator10Dni = users.get(9).getDni();
//		
//		//assign
//		Evaluation evaluation2 = app.assingEvaluatorToPaper(evaluator10Dni, p2, d);
//		if (evaluation2 != null) {
//			EvaluationDAO.getInstance().persist(evaluation2, entityManager);
//			evaluations.add(evaluation2);
//		}
//		
//		Paper updatedP2 = app.getPaperByName(papers.get(1).getName(), entityManager);
//		User updatedU10 = app.getUserByDni(evaluator10Dni, entityManager);
//		
//		assertTrue(evaluation2.equals(null));
//		assertTrue(updatedP2.getEvaluators().isEmpty());
//		assertTrue(updatedU10.getPapersToEvaluate().isEmpty());
//		
//		//Test p2 with u7, should be assigned
//
//		// Get u7: "Tyler, Steven", workplace: "Aerosmith", knownSubjects: (s6, s8)
//		int evaluator7Dni = users.get(6).getDni();
//		User evaluator7 = app.getUserByDni(evaluator7Dni, entityManager);
//
//		// assign
//		Evaluation evaluation3 = app.assingEvaluatorToPaper(evaluator7Dni, updatedP2, d);
//		if (evaluation3 != null) {
//			EvaluationDAO.getInstance().persist(evaluation3, entityManager);
//			evaluations.add(evaluation3);
//		}
//		
//		Evaluation evaluation3DataBase = app.getEvaluationByPaper(updatedP2, entityManager);
//		updatedP2 = app.getPaperByName(papers.get(4).getName(), entityManager);
//		User updatedEvaluator7 = app.getUserByDni(evaluator7Dni, entityManager);
//
//		assertTrue(evaluation3DataBase.getEvaluator().equals(evaluator7));
//		assertTrue(updatedP2.getEvaluators().get(0).equals(updatedEvaluator7));
//		assertTrue(updatedEvaluator7.getPapersToEvaluate().get(0).equals(updatedP2));
//		
//		entityManager.close();
//	}
//	
//	@Test
//	public void assignMoreThan3() {
//		EntityManager entityManager = emf.createEntityManager();
//		
//		//Create dates
//		
//		Calendar d = new GregorianCalendar(2018, 9, 5);
//		
//		//Get u7
//		int evaluator7Dni = users.get(6).getDni();
//		
//		//Get p9: poster, "Guitarra9", author = u4, keyWords = (s1)
//		Paper p9 = app.getPaperByName(papers.get(8).getName(), entityManager);
//		
//		//assign
//		Evaluation evaluation1 = app.assingEvaluatorToPaper(evaluator7Dni, p9, d);
//		if (evaluation1 != null) {
//			EvaluationDAO.getInstance().persist(evaluation1, entityManager);
//			evaluations.add(evaluation1);
//		}
//		
//		Evaluation evaluation1DataBase = app.getEvaluationByPaper(p9, entityManager);
//		Paper updatedP9 = app.getPaperByName(papers.get(8).getName(), entityManager);
//		User updatedEvaluator7 = app.getUserByDni(evaluator7Dni, entityManager);
//
//		assertTrue(evaluation1DataBase.getEvaluator().equals(updatedEvaluator7));
//		assertTrue(updatedP9.getEvaluators().get(0).equals(updatedEvaluator7));
//		assertTrue(updatedEvaluator7.getPapersToEvaluate().get(0).equals(updatedP9));
//		
//		//Test p7 with u7, should not be assigned
//		
//		//Get p7: poster, "Guitarra7", author = u5, keyWords = (s4)
//		Paper p7 = app.getPaperByName(papers.get(6).getName(), entityManager);
//		
//		//Get u7
//		updatedEvaluator7 = app.getUserByDni(evaluator7Dni, entityManager);
//		
//		//assign
//		Evaluation evaluation2 = app.assingEvaluatorToPaper(evaluator7Dni, p7, d);
//		if (evaluation2 != null) {
//			EvaluationDAO.getInstance().persist(evaluation2, entityManager);
//			evaluations.add(evaluation2);
//		}
//
//		Evaluation evaluation2DataBase = app.getEvaluationByPaper(p7, entityManager);
//		Paper updatedP7 = app.getPaperByName(papers.get(6).getName(), entityManager);
//		updatedEvaluator7 = app.getUserByDni(evaluator7Dni, entityManager);
//
//		assertTrue(evaluation2DataBase.getEvaluator().equals(updatedEvaluator7));
//		assertTrue(updatedP7.getEvaluators().get(0).equals(updatedEvaluator7));
//		assertTrue(updatedEvaluator7.getPapersToEvaluate().get(0).equals(updatedP7));
//		
//		entityManager.close();
//	}
//	
//	//Inciso d)ii) Dado un revisor, retornar todos sus trabajos asignados
//	@Test
//	public void getAssignedPapers() {
//		EntityManager entityManager = emf.createEntityManager();
//		
//		//Get p2, 95, p9
//		Paper p2 = app.getPaperByName(papers.get(1).getName(), entityManager);
//		Paper p5 = app.getPaperByName(papers.get(4).getName(), entityManager);
//		Paper p9 = app.getPaperByName(papers.get(8).getName(), entityManager);
//		
//		// Get u7: "Tyler, Steven", workplace: "Aerosmith", knownSubjects: (s6, s8), papersToEvaluate: (p2, p5, p9)
//		int evaluator7Dni = users.get(6).getDni();
//		
//		List<Paper> papersToEvaluate = app.getAssignedPapers(evaluator7Dni, entityManager);
//		
//		assertTrue(papersToEvaluate.get(0).equals(p2));
//		assertTrue(papersToEvaluate.get(1).equals(p5));
//		assertTrue(papersToEvaluate.get(2).equals(p9));
//		
//		entityManager.close();
//	}
//	
//	//Inciso d)iii) Dado un revisor y un rango de fechas, retornar todas sus revisiones
//	@Test
//	public void getEvaluationsOnDateRange() {
//		EntityManager entityManager = emf.createEntityManager();
//		
//		Calendar from = new GregorianCalendar(2018,9, 15);
//		Calendar to = new GregorianCalendar(2018,10, 20);
//		
//		// Get u7: "Tyler, Steven", workplace: "Aerosmith", knownSubjects: (s6, s8), papersToEvaluate: (p2, p5, p9)
//		int evaluator7Dni = users.get(6).getDni();
//		
//		List<Evaluation> evaluations = app.getEvaluationsByDate(evaluator7Dni, from, to, entityManager);
//		
//		assertTrue(evaluations.get(0).getDate().get(0) == 2018);
//		assertTrue(evaluations.get(0).getDate().get(1) == 10);
//		assertTrue(evaluations.get(0).getDate().get(2) == 16);
//		
//		entityManager.close();
//	}
//	
//	//Inciso d)iv) Dado un autor, retornar todos los trabajos de investigación enviados
//	@Test
//	public void getAuthoredPapers() {
//		EntityManager entityManager = emf.createEntityManager();
//		
//		Paper p2 = app.getPaperByName(papers.get(1).getName(), entityManager);
//		
//		int author2Dni = users.get(1).getDni();
//		
//		List<Paper> papersAuthored = app.getAuthoredPapers(author2Dni, entityManager);
//		
//		assertTrue(papersAuthored.get(0).equals(p2));
//		
//		entityManager.close();		
//	}
	
	//Inciso g) Eliminar todos los datos de la base de datos para realizar otro testeo
	@AfterClass
	public static void closeEntityManagerFactory() {
		app.terminate();
	}
	
}
