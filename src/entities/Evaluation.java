package entities;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Evaluation")
public class Evaluation {
	
	@Id
	@GeneratedValue
	int id;
	
	@Column(nullable = false)
	private Calendar date;
	
	@ManyToOne(cascade= CascadeType.ALL)
	private Paper paper;
	
	public Evaluation() {
		super();
	}

	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * @return the paper's name
	 */
	public Paper getPaper() {
		return paper;
	}

	/**
	 * @param paper the paper to set
	 */
	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	
}
