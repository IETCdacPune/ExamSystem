package com.ietpune.model;

import java.sql.Date;
import java.util.HashMap;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity

@Table(name = "Student_Paper",uniqueConstraints={
	    @UniqueConstraint(columnNames = {"paperId", "sudentId"})
	})
public class StudentPaper {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private Date paperDate;
	private int marks;
	private String result;
	private boolean present;
	@Column( name = "stud_ans",columnDefinition="blob")
	private HashMap<Integer, Character> studentAnsMap;
	
	@ManyToOne
	@JoinColumn(name = "paperId")
	private Paper paper;
	@ManyToOne
	@JoinColumn(name = "sudentId")
	private Student student;
	
	public StudentPaper() {
		super();
	}
	public StudentPaper(int id, Date paperDate, Paper paper, Student student, int marks, String result,
			HashMap<Integer, Character> studentAnsMap) {
		super();
		this.id = id;
		this.paperDate = paperDate;
		this.paper = paper;
		this.student = student;
		this.marks = marks;
		this.result = result;
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
	public boolean isPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public HashMap<Integer, Character> getStudentAnsMap() {
		return studentAnsMap;
	}
	public void setStudentAnsMap(HashMap<Integer, Character> studentAnsMap) {
		this.studentAnsMap = studentAnsMap;
	}
	@Override
	public String toString() {
		return "StudentPaper [id=" + id + ", paperDate=" + paperDate + ", paper=" + paper + ", student=" + student
				+ ", marks=" + marks + ", result=" + result + ", studentAnsMap=" + studentAnsMap + "]";
	}
	
}
