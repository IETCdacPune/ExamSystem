package com.ietpune.model.dto;

import javax.validation.constraints.NotNull;

import com.ietpune.model.Course;

public class PaperDTO {
	private int paperId;
	@NotNull
	private int paperCode;
	@NotNull
	private String paperTiming;
	private boolean enabled;
	private Course course;
	private String subject;
	
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
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
