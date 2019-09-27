package com.ietpune.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	private String name;
//Mapping between subject and Course
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Subject> subjectList;

	public Course() {
		super();
	}

	public Course(int courseId, String name, List<Subject> subjectList) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.subjectList = subjectList;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", name=" + name + "]";
	}
}
