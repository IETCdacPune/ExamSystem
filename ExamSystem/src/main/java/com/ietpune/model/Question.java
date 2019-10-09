package com.ietpune.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int queId;
	private String question;
	private String description;
	private char correctOption;

	@ManyToOne
	@JoinColumn
	private Paper paper;

	@OneToMany(mappedBy = "question",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Option> optionList;
	
	public Question() {
		super();
	}

	public Question(int queId, String question, String description, char correctOption, Paper paper,
			List<Option> optionList) {
		super();
		this.queId = queId;
		this.question = question;
		this.description = description;
		this.correctOption = correctOption;
		this.paper = paper;
		this.optionList = optionList;
	}


	public int getQueId() {
		return queId;
	}


	public void setQueId(int queId) {
		this.queId = queId;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public char getCorrectOption() {
		return correctOption;
	}


	public void setCorrectOption(char correctOption) {
		this.correctOption = correctOption;
	}


	public Paper getPaper() {
		return paper;
	}


	public void setPaper(Paper paper) {
		this.paper = paper;
	}


	public List<Option> getOptionList() {
		return optionList;
	}


	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}


	@Override
	public String toString() {
		return "Question [queId=" + queId + ", question=" + question + ", description=" + description
				+ ", correctOption=" + correctOption + "]";
	}
	
	
	
	
	
}
