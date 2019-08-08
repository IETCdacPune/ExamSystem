package com.ietpune.model;

import java.util.Set;

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
	private char question;
	private String description;
	private char correctOption;

	public Question() {
		// TODO Auto-generated constructor stub
	}

	// this mapping between paper and question
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paperId")
	private Paper paperObj;

	// this Mapping between Questin and opetion
	@OneToMany(mappedBy = "quesObj")
	private Set<Option> optionSet;

	public int getQueId() {
		return queId;
	}

	public void setQueId(int queId) {
		this.queId = queId;
	}

	public char getQuestion() {
		return question;
	}

	public void setQuestion(char question) {
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

	public Set<Option> getOptionSet() {
		return optionSet;
	}

	public void setOptionSet(Set<Option> optionSet) {
		this.optionSet = optionSet;
	}

	public Paper getPaperObj() {
		return paperObj;
	}

	public void setPaperObj(Paper paperObj) {
		this.paperObj = paperObj;
	}

	@Override
	public String toString() {
		return "Question [QueId=" + queId + ", question=" + question + ", description=" + description
				+ ", correctOption=" + correctOption + "]";
	}

}
