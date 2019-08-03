package com.ietpune.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Paper {

	@Id
	@GeneratedValue
	private int  paperId;
	private String paperName;
	private String moduleName;
	private String paperTiming;
	
	public Paper() {
		// TODO Auto-generated constructor stub
	}

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

	@Override
	public String toString() {
		return "Paper [paperId=" + paperId + ", paperName=" + paperName + ", moduleName=" + moduleName
				+ ", paperTiming=" + paperTiming + "]";
	}

}
