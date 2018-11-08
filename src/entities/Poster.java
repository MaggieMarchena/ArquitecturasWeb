package entities;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Poster extends Paper{
	
	public Poster() {
		super();
	}
	
	public boolean isEvaluatorApt (User evaluator) {
		boolean result = false;
		List<Subject> evaluatorKnownSubjects = evaluator.getKnownSubjects();
		Iterator<Subject> it = this.keyWords.iterator();
		while(it.hasNext() && !result) {
			Subject keyWord = it.next();
			if(evaluatorKnownSubjects.contains(keyWord)) {
				result = true;
			}
		}
		return result;
	}

}
