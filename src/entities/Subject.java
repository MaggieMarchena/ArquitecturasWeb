package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Subject")
public class Subject {
	
	@Id
	@GeneratedValue
	int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private boolean expert;
	
	public Subject() {
		this.expert = false;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
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
	 * @return the expert
	 */
	public boolean isExpert() {
		return expert;
	}

	/**
	 * @param expert the expert to set
	 */
	public void setExpert(boolean expert) {
		this.expert = expert;
	}
	
	

}
