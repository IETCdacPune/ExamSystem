package com.ietpune.model;

import java.util.List;
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
	private int  paperCode;
	private String moduleName;
	private String paperTiming;
	private boolean  enabled;
	
	// this mapping between student and paper
	@ManyToMany(mappedBy = "paperSetObj")
	private List<Student> studentSetObj;
// this mapping between paper and question

	@OneToMany(mappedBy = "paperObj")
	private List<PaperQuestion> quesSetObj;
	
	public Paper() {}

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Student> getStudentSetObj() {
		return studentSetObj;
	}

	public void setStudentSetObj(List<Student> studentSetObj) {
		this.studentSetObj = studentSetObj;
	}

	public List<PaperQuestion> getQuesSetObj() {
		return quesSetObj;
	}

	public void setQuesSetObj(List<PaperQuestion> quesSetObj) {
		this.quesSetObj = quesSetObj;
	}

	@Override
	public String toString() {
		return "Paper [paperId=" + paperId + ", paperCode=" + paperCode + ", moduleName=" + moduleName
				+ ", paperTiming=" + paperTiming + ", enabled=" + enabled + ", studentSetObj=" + studentSetObj
				+ ", quesSetObj=" + quesSetObj + "]";
	}

	
}
