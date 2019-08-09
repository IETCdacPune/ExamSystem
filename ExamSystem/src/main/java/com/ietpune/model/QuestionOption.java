package com.ietpune.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class QuestionOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int optionId;
	private char option;
	private String answer;

	public QuestionOption() {
		// TODO Auto-generated constructor stub
	}

	// this mapping between One Question and Many Paper
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quesId")
	private PaperQuestion quesObj;

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public char getOption() {
		return option;
	}

	public void setOption(char optionNo) {
		this.option = optionNo;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public PaperQuestion getQuesObj() {
		return quesObj;
	}

	public void setQuesObj(PaperQuestion quesObj) {
		this.quesObj = quesObj;
	}

	@Override
	public String toString() {
		return "Option [optionId=" + optionId + ", optionNo=" + option + ", answer=" + answer + ", quesObj=" + quesObj
				+ "]";
	}

}
