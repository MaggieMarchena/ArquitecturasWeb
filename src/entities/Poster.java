package entities;

import java.util.Iterator;
import java.util.List;

public class Poster extends Paper{
	
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
