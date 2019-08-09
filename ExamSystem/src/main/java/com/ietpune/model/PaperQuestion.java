package com.ietpune.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PaperQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int queId;
	private String question;
	private String description;
	private char correctOption;

	public PaperQuestion() {
		// TODO Auto-generated constructor stub
	}

	// this mapping between paper and question
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paperId")
	private Paper paperObj;

	// this Mapping between Questin and opetion
	@OneToMany(mappedBy = "quesObj")
	private List<QuestionOption> optionList;

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

	public List<QuestionOption> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<QuestionOption> options) {
		this.optionList = options;
	}

	public Paper getPaperObj() {
		return paperObj;
	}

	public void setPaperObj(Paper paperObj) {
		this.paperObj = paperObj;
	}

	@Override
	public String toString() {
		return "\nPaperQuestion [queId=" + queId + ", question=" + question + ", description=" + description
				+ ", correctOption=" + correctOption + ", optionList=" + optionList + "]";
	}


}
