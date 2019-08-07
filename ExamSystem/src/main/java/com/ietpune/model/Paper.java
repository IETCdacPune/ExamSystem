package com.ietpune.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Paper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paperId;
	private String paperName;
	private String moduleName;
	private String paperTiming;

	public Paper() {
		// TODO Auto-generated constructor stub
	}

	// this mapping between student and paper
	@ManyToMany(mappedBy = "paperSetObj")
	private Set<Student> studentSetObj;
// this mapping between paper and question

	@OneToMany(mappedBy = "paperObj")
	Set<PaperQuestion> quesSetObj;

	public int getPaperId() {
		return paperId;
	}

	public void setPaperId(int paperId) {
		this.paperId = paperId;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getPaperTiming() {
		return paperTiming;
	}

	public void setPaperTiming(String paperTiming) {
		this.paperTiming = paperTiming;
	}

	public Set<Student> getStudentSetObj() {
		return studentSetObj;
	}

	public void setStudentSetObj(Set<Student> studentSetObj) {
		this.studentSetObj = studentSetObj;
	}

	public Set<PaperQuestion> getQuesSetObj() {
		return quesSetObj;
	}

	public void setQuesSetObj(Set<PaperQuestion> quesSetObj) {
		this.quesSetObj = quesSetObj;
	}

	@Override
	public String toString() {
		return "Paper [paperId=" + paperId + ", paperName=" + paperName + ", moduleName=" + moduleName
				+ ", paperTiming=" + paperTiming + "]";
	}

}
