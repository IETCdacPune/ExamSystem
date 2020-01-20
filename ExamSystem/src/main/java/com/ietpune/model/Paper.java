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
import javax.persistence.Table;

import net.bytebuddy.implementation.bind.annotation.Default;

@Entity
@Table
public class Paper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paperId;
	private int paperCode;
	private String paperTiming;
	private boolean enabled;
	
	private boolean newPaper=true;
	
	@ManyToOne
	@JoinColumn
	private Subject subject;
	
	@OneToMany(mappedBy = "paper", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Question> questionList;
	
	@OneToMany(mappedBy = "paper",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Student_Paper> paper_studentlist;;
	public Paper() {
		super();
	}
	public Paper(int paperId, int paperCode, String paperTiming, boolean enabled, boolean newPaper, Subject subject,
			List<Question> questionList, List<Student_Paper> paper_studentlist) {
		super();
		this.paperId = paperId;
		this.paperCode = paperCode;
		this.paperTiming = paperTiming;
		this.enabled = enabled;
		this.newPaper = newPaper;
		this.subject = subject;
		this.questionList = questionList;
		this.paper_studentlist = paper_studentlist;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	public int getPaperId() {
		return paperId;
	}
	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}
	public int getPaperCode() {
		return paperCode;
	}
	public void setPaperCode(int paperCode) {
		this.paperCode = paperCode;
	}
	public String getPaperTiming() {
		return paperTiming;
	}
	public void setPaperTiming(String paperTiming) {
		this.paperTiming = paperTiming;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isNewPaper() {
		return newPaper;
	}
	public void setNewPaper(boolean newPaper) {
		this.newPaper = newPaper;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public List<Question> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}
	public List<Student_Paper> getPaper_studentlist() {
		return paper_studentlist;
	}
	public void setPaper_studentlist(List<Student_Paper> paper_studentlist) {
		this.paper_studentlist = paper_studentlist;
	}
	@Override
	public String toString() {
		return "Paper [paperId=" + paperId + ", paperCode=" + paperCode + ", paperTiming=" + paperTiming + ", enabled="
				+ enabled + ", newPaper=" + newPaper + ", subject=" + subject + "]";
	}
	
	
	
}
