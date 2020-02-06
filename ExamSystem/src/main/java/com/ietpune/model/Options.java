package com.ietpune.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Options {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int optionId;
	private char opt;
	private String answer;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "queId")
	private Question question;
	
	public Options() {
		super();
	}

	public Options(int optionId, char option, String answer, Question question) {
		super();
		this.optionId = optionId;
		this.opt = option;
		this.answer = answer;
		this.question = question;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public char getOpt() {
		return opt;
	}

	public void setOpt(char option) {
		this.opt = option;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Option [optionId=" + optionId + ", option=" + opt + ", answer=" + answer + "]";
	}
}
