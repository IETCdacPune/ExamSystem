package com.ietpune.model;

import java.util.ArrayList;
import java.util.List;

public class McqQuestion {
	private int queId;
	private String fullQuestion;
	private boolean read;
	private boolean markedReview;
	private char ans;
	private Paper paper;
	private List<Options> optionList=new ArrayList<>();
	public McqQuestion(){
		read=markedReview=false;
		
	}
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
	
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	public boolean isMarkedReview() {
		return markedReview;
	}
	public void setMarkedReview(boolean markedReview) {
		this.markedReview = markedReview;
	}
	public char getAns() {
		return ans;
	}
	public void setAns(char ans) {
		this.ans = ans;
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
		return "McqQuestion [queId=" + queId + ", fullQuestion=" + fullQuestion + ", read=" + read + ", markedReview="
				+ markedReview + ", ans=" + ans + ", paper=" + paper + ", optionList=" + optionList + "]";
	}	
	
}
