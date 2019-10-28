package com.ietpune.model.dto;

import java.util.List;

import com.ietpune.model.Option;
import com.ietpune.model.Paper;

public class QuestionDTO {
	private int queId;
	private String fullQuestion;
	private String description;
	private char correctOption;
	private Paper paper;
	private List<Option> optionList;
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
	public List<Option> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}
	
}
