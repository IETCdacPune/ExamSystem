package com.ietpune.model;

import java.sql.Date;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class Student_Paper {
@Id 
@GeneratedValue
private int id;
private Date paperDate;

@ManyToOne
@JoinColumn(name = "paperId")
private Paper paper;
@ManyToOne
@JoinColumn(name = "")
private Student student;
HashMap<Integer, Character>studentAnsMap;
public Student_Paper() {
	super();
}
public Student_Paper(int id, Date paperDate, Paper paper, Student student, HashMap<Integer, Character> studentAnsMap) {
	super();
	this.id = id;
	this.paperDate = paperDate;
	this.paper = paper;
	this.student = student;
	this.studentAnsMap = studentAnsMap;
}






public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Date getPaperDate() {
	return paperDate;
}
public void setPaperDate(Date paperDate) {
	this.paperDate = paperDate;
}
public Paper getPaper() {
	return paper;
}
public void setPaper(Paper paper) {
	this.paper = paper;
}
public Student getStudent() {
	return student;
}
public void setStudent(Student student) {
	this.student = student;
}
public HashMap<Integer, Character> getStudentAnsMap() {
	return studentAnsMap;
}
public void setStudentAnsMap(HashMap<Integer, Character> studentAnsMap) {
	this.studentAnsMap = studentAnsMap;
}
@Override
public String toString() {
	return "Student_Paper [id=" + id + ", paperDate=" + paperDate + ", paper=" + paper + ", student=" + student
			+ ", studentAnsMap=" + studentAnsMap + "]";
}


}
