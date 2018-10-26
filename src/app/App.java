/**
 * 
 */
package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Evaluation;
import entities.Paper;
import entities.Subject;
import entities.User;
import services.EvaluationDAO;
import services.PaperDAO;
import services.SubjectDAO;
import services.UserDAO;

/**
 * @author Magui
 *
 */
public class App {
	
	public static final int MAX_PAPERS = 3;
	public static final int MAX_EVALUATORS = 3;
	public static final String EXPERT = "Expert";
	public static final String GENERAL = "General";
	
	
	
	private static EntityManagerFactory emf;
	
	public App() {
		
	}
	
	private void emfON() {
		emf = Persistence.createEntityManagerFactory("TPE");
	}
	private void emfOFF() {
		emf.close();
	}
	
	public void addUser(User user) {
		emfON();
		EntityManager entityManager = emf.createEntityManager();
		UserDAO.getInstance().persist(user, entityManager);
		emfOFF();
	}
	
	public void addPaper(Paper paper) {
		emfON();
		EntityManager entityManager = emf.createEntityManager();
		PaperDAO.getInstance().persist(paper, entityManager);
		emfOFF();
	}
		
	public List<User> getAptEvaluators(Paper paper){
		List<User> result = new ArrayList<>();
		List<User> users = this.getUsers();
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User evaluator = it.next();
			if(evaluator.canEvaluate(paper) && paper.isEvaluatorApt(evaluator)) {
				result.add(evaluator);
			}
		}
		return result;
	}
	
	private List<User> getUsers() {
		List<User> result;
		emfON();
		EntityManager entityManager = emf.createEntityManager();
		result = UserDAO.getInstance().findAll(entityManager);
		emfOFF();
		return result;
	}
	
	public List<Paper> getPapersAptForEvaluator(int dni, EntityManager entityManager){
		List<Paper> result = new ArrayList<>();
		User evaluator = getUserByDni(dni, entityManager);
		List<Paper> papers = this.getPapers();
		Iterator<Paper> it = papers.iterator();
		if(evaluator != null) {
			while(it.hasNext()) {
				Paper paper = it.next();
				if(paper.evaluatorsNotFull()) {
					if(evaluator.canEvaluate(paper)) {
						result.add(paper);
					}
				}
			}
		}
		return result;
	}
	
	private List<Paper> getPapers() {
		List<Paper> result;
		emfON();
		EntityManager entityManager = emf.createEntityManager();
		result = PaperDAO.getInstance().findAll(entityManager);
		emfOFF();
		return result;
	}
	
	public String getEvaluatorType(int dni, EntityManager entityManager) {
		boolean result = false;
		User user = getUserByDni(dni, entityManager);
		if(user != null) {
			List<Subject> evaluatorKnownSubjects = user.getKnownSubjects();
			Iterator<Subject> it = evaluatorKnownSubjects.iterator();
			while(it.hasNext() && !result) {
				Subject subject = it.next();
				if(subject.isExpert()) {
					result = true;
				}
			}
		}
		if(result) {
			return EXPERT;
		}
		return GENERAL;
	}
	
	public Evaluation assingEvaluatorToPaper(int dni, Paper paper, Calendar date) {
		Evaluation evaluation = null;
		List<User> allEvaluators = this.getAptEvaluators(paper);
		User evaluator = this.getUserByDniInList(dni, allEvaluators);
		if(evaluator.canEvaluate(paper) && paper.isEvaluatorApt(evaluator)) {
			evaluator.addPaperToEvaluate(paper);
			paper.addEvaluator(evaluator);
			evaluation = new Evaluation();
			evaluation.setEvaluator(evaluator);
			evaluation.setPaper(paper);
			evaluation.setDate(date);
			evaluator.addEvaluation(evaluation);
			UserDAO.getInstance().persist(evaluator);
			PaperDAO.getInstance().persist(paper);
		}
		return evaluation;
	}
	
	public List<Paper> getAssignedPapers(int dni, EntityManager entityManager) {
		User evaluator = getUserByDni(dni, entityManager);
		if (evaluator != null) {
			return evaluator.getPapersToEvaluate();
		}
		return null;
	}
	
	public List<Paper> getAuthoredPapers(int dni, EntityManager entityManager) {
		User evaluator = getUserByDni(dni, entityManager);
		if (evaluator != null) {
			return evaluator.getPapersAuthored();
		}
		return null;
	}
	
	public List<Subject> getSubjects(){
		List<Subject> result;
		emfON();
		EntityManager entityManager = emf.createEntityManager();
		result = SubjectDAO.getInstance().findAll(entityManager);
		emfOFF();
		return result;
	}
	
	public User getUserByDni(int dni, EntityManager entityManager) {
		User user = UserDAO.getInstance().findById(dni, entityManager);
		return user;
	}
	
	private User getUserByDniInList(int dni, List<User> list) {
		User user = null;
		Iterator<User> it = list.iterator();
		while(it.hasNext() && user == null) {
			User aux = it.next();
			if(aux.getDni() == dni) {
				user = aux;
			}
		}
		return user;
	}
	
	public User updateUser(int dni, User newEntityValues, EntityManager entityManager) {
		User user = UserDAO.getInstance().update(dni, newEntityValues, entityManager);
		return user;
	}
	
	public Paper updatePaper(String name, Paper newEntityValues, EntityManager entityManager) {
		Paper paper = PaperDAO.getInstance().update(name, newEntityValues, entityManager);
		return paper;
	}
	
	public Evaluation updateEvaluation(Paper paper, Evaluation newEntityValues, EntityManager entityManager) {
		Evaluation evaluation = EvaluationDAO.getInstance().update(paper, newEntityValues, entityManager);
		return evaluation;
	}
	
	public Paper getPaperByName(String name, EntityManager entityManager) {
		Paper paper = PaperDAO.getInstance().findByName(name, entityManager);
		return paper;
	}
	
	public Evaluation getEvaluationByPaper(Paper paper, EntityManager entityManager) {
		Evaluation evaluation = EvaluationDAO.getInstance().findByPaper(paper, entityManager);
		return evaluation;
	}
	
	public Subject getSubjectByName(String name, EntityManager entityManager) {
		Subject subject = SubjectDAO.getInstance().findByName(name, entityManager);
		return subject;
	}

}
