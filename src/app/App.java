/**
 * 
 */
package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Evaluation;
import entities.Paper;
import entities.Subject;
import entities.User;
import services.EMF;
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
	
	public App() {
		
	}
	
	public void initialize() {
		EMF.contextInitialized();
	}
	
	public void terminate() {
		EMF.contextDestroyed();
	}
	
	public void createSubject(Subject subject) {
		SubjectDAO.getInstance().persist(subject);
	}
	
	public void createUser(User user) {
		UserDAO.getInstance().persist(user);
	}
	
	public void createPaper(Paper paper) {
		PaperDAO.getInstance().persist(paper);
	}
	
	public void assignAuthorToPaper(int id_paper, int id_author) {
		PaperDAO.getInstance().assignAuthorToPaper(id_paper, id_author);
	}
	
	public void addSubjectToPaper(int id_paper, int id_subject) {
		PaperDAO.getInstance().addSubjectToPaper(id_paper, id_subject);
	}
	
//	public List<User> getEvaluatorsOfPaper(int id_paper){
//		
//	}
		
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
		List<User> result = UserDAO.getInstance().findAll();
		return result;
	}
	
	public List<Paper> getPapersAptForEvaluator(int dni){
		List<Paper> result = new ArrayList<>();
		User evaluator = getUserByDni(dni);
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
		result = PaperDAO.getInstance().findAll();
		return result;
	}
	
	public String getEvaluatorType(int dni) {
		boolean result = false;
		User user = getUserByDni(dni);
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
	
	public void assingEvaluatorToPaper(int dni, int id_paper) {
		Paper paper = this.getPaperById(id_paper);
		List<User> allEvaluators = this.getAptEvaluators(paper);
		User evaluator = this.getUserByDniInList(dni, allEvaluators);
		if(evaluator.canEvaluate(paper) && paper.isEvaluatorApt(evaluator)) {
			UserDAO.getInstance().assignPaperToEvaluate(dni);
			PaperDAO.getInstance().assignEvaluatorToPaper(id_paper, dni);
		}
	}
	
	//refactor con query!
	public List<Paper> getAssignedPapers(int dni) {
		List<Paper> result = new ArrayList<>();
		User evaluator = getUserByDni(dni);
		if (evaluator != null) {
			List <Paper> papers = getPapers();
			Iterator<Paper> it = papers.iterator();
			while(it.hasNext()) {
				Paper aux = it.next();
				List<User> authors = aux.getAuthors();
				Iterator<User> it2 = authors.iterator();
				while(it2.hasNext() && !result.contains(evaluator)) {
					User aux2 = it2.next();
					if(aux2.equals(evaluator)) {
						result.add(aux);
					}
				}
				
			}
		}
		if(!result.isEmpty()) {
			return result;
		}
		return null;
	}
	
	//refactor con query!
	public List<Paper> getAuthoredPapers(int dni) {
		User evaluator = getUserByDni(dni);
		List<Paper> papers = this.getPapers();
		List<Paper> result = new ArrayList<>();
		if (evaluator != null) {
			Iterator<Paper> it = papers.iterator();
			while(it.hasNext()) {
				Paper aux = it.next();
				if(aux.getAuthors().contains(evaluator)) {
					result.add(aux);
				}
			}
			if(!result.isEmpty()) {
				return result;
			}
		}
		return null;
	}
	
	public List<Evaluation> getEvaluationsByDate(int dni, Calendar from, Calendar to) {
		User evaluator = getUserByDni(dni);
		if (evaluator != null) {
			return UserDAO.getInstance().findEvaluationsByDate(dni, from, to);
		}
		return null;
	}
	
	public List<Subject> getSubjects(){
		List<Subject> result;
		result = SubjectDAO.getInstance().findAll();
		return result;
	}
	
	public User getUserByDni(int dni) {
		User user = UserDAO.getInstance().findById(dni);
		return user;
	}
	
	public Paper getPaperById(int id) {
		Paper paper = PaperDAO.getInstance().findById(id);
		return paper;
	}
	
	public Subject getSubjectById(int id) {
		Subject subject = SubjectDAO.getInstance().findById(id);
		return subject;
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
	
	public User updateUser(int dni, User newEntityValues) {
		User user = UserDAO.getInstance().update(dni, newEntityValues);
		return user;
	}
	
	public Paper updatePaper(String name, Paper newEntityValues) {
		Paper paper = PaperDAO.getInstance().update(name, newEntityValues);
		return paper;
	}
	
	public Evaluation updateEvaluation(Paper paper, Evaluation newEntityValues) {
		Evaluation evaluation = EvaluationDAO.getInstance().update(paper, newEntityValues);
		return evaluation;
	}
	
	public Paper getPaperByName(String name) {
		Paper paper = PaperDAO.getInstance().findByName(name);
		return paper;
	}
	
	public Evaluation getEvaluationByPaper(Paper paper) {
		Evaluation evaluation = EvaluationDAO.getInstance().findByPaper(paper);
		return evaluation;
	}
	
	public Subject getSubjectByName(String name) {
		Subject subject = SubjectDAO.getInstance().findByName(name);
		return subject;
	}
	
	public User getUserInSet(Set<User> users, User user) {
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User aux = it.next();
			if(aux.getDni() == user.getDni()) {
				return aux;
			}
		}
		return null;
	}

}
