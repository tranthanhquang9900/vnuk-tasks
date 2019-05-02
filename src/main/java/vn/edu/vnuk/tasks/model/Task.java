package vn.edu.vnuk.tasks.model;

import java.util.Calendar;

public class Task {
	private Long id;
	private String description;
	private Boolean isComplete;
	private Calendar dateOfcompletion;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIsComplete() {
		return isComplete;
	}
	public void setIsComplete(Boolean is_complete) {
		this.isComplete = is_complete;
	}
	public Calendar getDateOfcompletion() {
		return dateOfcompletion;
	}
	public void setDateOfcompletion(Calendar dateOfcompletion) {
		this.dateOfcompletion = dateOfcompletion;
	}
	
	
}
