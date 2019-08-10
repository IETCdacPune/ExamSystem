package com.ietpune.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Option {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int optionId;
	private char option;
	private String answer;

	@ManyToOne
	@JoinColumn
	private Question question;
	public Option() {}
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public char getOption() {
		return option;
	}
	public void setOption(char option) {
		this.option = option;
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
		return "Option [optionId=" + optionId + ", option=" + option + "]";
	}
	
	
	
	

}
