/**
 * 
 */
package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Evaluation;
import entities.Paper;
import entities.Subject;
import entities.User;
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
	
	private List<User> users;
	private List<Paper> papers;
	private List<User> evaluators;
	
	private static EntityManagerFactory emf;
	
	public App() {
		this.users = new ArrayList<>();
		this.papers = new ArrayList<>();
		this.evaluators = new ArrayList<>();
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
		this.users.add(user);
		if(user.isEvaluator()) {
			this.evaluators.add(user);
		}
	}
	
	public void addPaper(Paper paper) {
		emfON();
		EntityManager entityManager = emf.createEntityManager();
		PaperDAO.getInstance().persist(paper, entityManager);
		this.papers.add(paper);
	}
		
	public List<User> getAptEvaluators(Paper paper){
		List<User> result = new ArrayList<>();
		List<Subject> paperKeyWords = paper.getKeyWords();
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
		return result;
	}
	
	public List<Paper> getPapersAptForEvaluator(int dni, EntityManager entityManager){
		List<Paper> result = new ArrayList<>();
		User evaluator = this.getUserByDni(dni, entityManager);
		List<Paper> papers = this.getPapers();
		Iterator<Paper> it = this.papers.iterator();
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
		return result;
	}
	
	public String getEvaluatorType(int dni, EntityManager entityManager) {
		boolean result = false;
		User user = this.getUserByDni(dni, entityManager);
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
	
	public void assingEvaluatorToPaper(int dni, Paper paper, Calendar date) {
		List<User> allEvaluators = this.getAptEvaluators(paper);
		User evaluator = this.getUserByDniInList(dni, allEvaluators);
		if(evaluator.canEvaluate(paper) && paper.isEvaluatorApt(evaluator)) {
			evaluator.addPaperToEvaluate(paper);
			paper.addEvaluator(evaluator);
			Evaluation evaluation = new Evaluation();
			evaluation.setEvaluator(evaluator);
			evaluation.setPaper(paper);
			evaluation.setDate(date);
			evaluator.addEvaluation(evaluation);
		}
	}
	
	public List<Paper> getAssignedPapers(int dni, EntityManager entityManager) {
		User evaluator = this.getUserByDni(dni, entityManager);
		if (evaluator != null) {
			return evaluator.getPapersToEvaluate();
		}
		return null;
	}
	
	public List<Paper> getAuthoredPapers(int dni, EntityManager entityManager) {
		User evaluator = this.getUserByDni(dni, entityManager);
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
		return result;
	}
	
	public static User getUserByDni(int dni, EntityManager entityManager) {
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

}
