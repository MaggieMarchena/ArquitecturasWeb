package entities;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import app.App;

@Entity
@Table(name="Paper")
public class Paper {
	
	@Id
	protected String name;
	
	@Column(nullable = false)
	@OneToMany
	protected List<Subject> keyWords;
	
	@Column(nullable = false)
	@ManyToMany(mappedBy = "papersToEvaluate")
	protected List<User> authors;
	
	@Column(nullable = false)
	@ManyToMany(mappedBy = "papersToEvaluate")
	protected List<User> evaluators;
	
	@Column(nullable = false)
	protected boolean evaluated;
	
	@Column(nullable = false)
	@OneToOne
	protected Evaluation evaluation;
	
	public Paper() {
		super();
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
		return Collections.unmodifiableList(keyWords);
	}

	/**
	 * @param keyWords the keyWords to set
	 */
	public void addKeyWord(Subject keyWord) {
		this.keyWords.add(keyWord);
	}

	/**
	 * @return the authors
	 */
	public List<User> getAuthors() {
		return Collections.unmodifiableList(authors);
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List<User> authors) {
		this.authors = authors;
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
	 * @return the evaluated
	 */
	public boolean isEvaluated() {
		return evaluated;
	}

	/**
	 * @param evaluated the evaluated to set
	 */
	public void setEvaluated() {
		this.evaluated = true;
	}

	/**
	 * @return the evaluation
	 */
	public Evaluation getEvaluation() {
		return evaluation;
	}

	/**
	 * @param evaluation the evaluation to set
	 */
	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
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

}
