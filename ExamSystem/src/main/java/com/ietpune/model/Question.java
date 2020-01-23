package com.ietpune.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ietpune.model.Options;;

@Entity
@Table
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int queId;
	@Column(length = 1000)
	private String fullQuestion;
	@Column(length = 1000)
	private String description;
	private char correctOption;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "paperId")
	private Paper paper;

	@OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
	private List<Options> optionList=new ArrayList<>();

	public int getQueId() {
		return queId;
	}

	public void setQueId(int queId) {
		this.queId = queId;
	}

	public String getFullQuestion() {
		return fullQuestion;
	}

	public void setFullQuestion(String fullQuestion) {
		this.fullQuestion = fullQuestion;
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

	public List<Options> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Options> optionList) {
		this.optionList = optionList;
	}

	@Override
	public String toString() {
		return "Question [queId=" + queId + ", fullQuestion=" + fullQuestion + ", description=" + description
				+ ", correctOption=" + correctOption + ", paper=" + paper + ", optionList=" + optionList + "]";
	}	
	
}
