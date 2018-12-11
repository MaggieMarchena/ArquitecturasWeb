package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import app.App;

@Entity
@Table(name="User")
public class User {
	
	@Id
	private int dni;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private boolean evaluator;
	
	@Column(nullable = false)
	private String workPlace;
	
	@Column(nullable = false)
	private int papersToEvaluate;
	
	@ManyToMany
	private List<Subject> knownSubjects;
	
	@OneToMany()
	@JoinTable(
			name = "user_evaluation",
			joinColumns = { @JoinColumn(name = "User_id") },
			inverseJoinColumns = { @JoinColumn(name = "Evaluation_id") }
		)
	private List<Evaluation> evaluations;	

	public User() {
		super();
		this.papersToEvaluate = 0;
		this.knownSubjects = new ArrayList<>();
		this.evaluations = new ArrayList<>();
	}
	
	
	/**
	 * @return the dni
	 */
	public int getDni() {
		return dni;
	}
	
	/**
	 * @param dni the dni to set
	 */
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the evaluator
	 */
	public boolean isEvaluator() {
		return evaluator;
	}
	
	/**
	 * @param evaluator the evaluator to set
	 */
	public void setEvaluator(boolean value) {
		this.evaluator = value;
	}
	
	/**
	 * @return the workPlace
	 */
	public String getWorkPlace() {
		return workPlace;
	}
	
	/**
	 * @param workPlace the workPlace to set
	 */
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	
	public void addPaperToEvaluate() {
		this.papersToEvaluate++;
	}
	
	/**
	 * @return the knownSubjects
	 */
	public List<Subject> getKnownSubjects() {
		return Collections.unmodifiableList(knownSubjects);
	}
	
	/**
	 * @param knownSubjects the knownSubjects to set
	 */
	public void addKnownSubject(Subject knownSubject) {
		this.knownSubjects.add(knownSubject);
	}
	
	/**
	 * @return the evaluation
	 */
	public List<Evaluation> getEvaluation() {
		return Collections.unmodifiableList(evaluations);
	}
	
	/**
	 * @param evaluation the evaluation to set
	 */
	public void addEvaluation(Evaluation evaluation) {
		this.evaluations.add(evaluation);
	}
	
	public boolean canEvaluate(Paper paper) {
		boolean result = true;
		if(this.papersToEvaluate > App.MAX_PAPERS) {
			result = false;
		}
		else {
			List<User> authors = paper.getAuthors();
			if(authors.contains(this)) {
				result = false;
			}
			else {
				Iterator<User> it = authors.iterator();
				while(it.hasNext() && result) {
					User author = it.next();
					if(author.getWorkPlace().equals(this.workPlace)) {
						result = false;
					}
				}
			}
		}
		return result;
	}

}
