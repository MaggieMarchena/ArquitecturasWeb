package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import app.App;

@Entity
@Table(name="Paper")
public class Paper {
	
	@Id
	@GeneratedValue
	protected int id;
	
	@Column(nullable = false)
	protected String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "paper_keyWord",
			joinColumns = { @JoinColumn(name = "Paper_id") },
			inverseJoinColumns = { @JoinColumn(name = "Subject_id") }
		)
	protected List<Subject> keyWords;
	//protected Set<Subject> keyWords;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(
			name = "paper_author",
			joinColumns = { @JoinColumn(name = "Paper_id") },
			inverseJoinColumns = { @JoinColumn(name = "User_id") }
		)
	//protected List<User> authors;
	protected Set<User> authors;
	
	@ManyToMany(cascade=CascadeType.ALL)//(fetch = FetchType.EAGER)
	@JoinTable(
			name = "paper_evaluator",
			joinColumns = { @JoinColumn(name = "Paper_id") },
			inverseJoinColumns = { @JoinColumn(name = "User_id") }
		)
	protected List<User> evaluators;
	
	@OneToMany(mappedBy = "paper") //fetch = FetchType.EAGER)
	protected List<Evaluation> evaluations;
	
	public Paper() {
		super();
		//this.keyWords = new HashSet<>();
		this.keyWords = new ArrayList<>();
		this.authors = new HashSet<>();
		//this.authors = new ArrayList<>();
		this.evaluations = new ArrayList<>();
	}
	
	public Paper(String name) {
		super();
		this.name = name;
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
	 * @return the keyWords
	 */
	public List<Subject> getKeyWords() {
		//return Collections.unmodifiableSet(keyWords);
		return Collections.unmodifiableList(keyWords);
	}

	/**
	 * @param keyWord the keyWords to set
	 */
	public void addKeyWord(Subject keyWord) {
		this.keyWords.add(keyWord);
	}

	/**
	 * @return the authors
	 */
	public Set<User> getAuthors() {
		return Collections.unmodifiableSet(authors);
		//return Collections.unmodifiableList(authors);
	}
	
	public void addAuthor(User author) {
		this.authors.add(author);
	}

	/**
	 * @return the evaluators
	 */
	public List<User> getEvaluators() {
		return Collections.unmodifiableList(evaluators);
	}

	/**
	 * @param evaluators the evaluators to set
	 */
	public void setEvaluators(List<User> evaluators) {
		this.evaluators = evaluators;
	}
	
	public void addEvaluator(User evaluator) {
		this.evaluators.add(evaluator);
	}

	/**
	 * @return the evaluation
	 */
	public List<Evaluation> getEvaluations() {
		return this.evaluations;
	}

	/**
	 * @param evaluation the evaluation to set
	 */
	public void addEvaluation(Evaluation evaluation) {
		this.evaluations.add(evaluation);
	}
	
	public boolean evaluatorsNotFull() {
		return (this.evaluators.size() <= App.MAX_EVALUATORS);
	}
	
	public boolean isEvaluatorApt (User evaluator) {
		boolean result = true;
		List<Subject> evaluatorKnownSubjects = evaluator.getKnownSubjects();
		Iterator<Subject> it = this.keyWords.iterator();
		while(it.hasNext() && result) {
			Subject keyWord = it.next();
			if(!evaluatorKnownSubjects.contains(keyWord)) {
				result = false;
			}
		}
		return result;
	}

	public int getId() {
		return id;
	}

}
