package com.ietpune.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Option {
	@Id
	@GeneratedValue
private int optionId;
private char optionNo;
private String answer;



	public Option() {
		// TODO Auto-generated constructor stub
	}
	
	// this mapping between One Question and  Many Paper 
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "quesId")
private Question quesObj;



public int getOptionId() {
	return optionId;
}

public void setOptionId(int optionId) {
	this.optionId = optionId;
}

public char getOptionNo() {
	return optionNo;
}

public void setOptionNo(char optionNo) {
	this.optionNo = optionNo;
}

public String getAnswer() {
	return answer;
}

public void setAnswer(String answer) {
	this.answer = answer;
}

public Question getQuesObj() {
	return quesObj;
}

public void setQuesObj(Question quesObj) {
	this.quesObj = quesObj;
}

@Override
public String toString() {
	return "Option [optionId=" + optionId + ", optionNo=" + optionNo + ", answer=" + answer + ", quesObj=" + quesObj
			+ "]";
}




}
