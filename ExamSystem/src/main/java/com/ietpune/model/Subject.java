package com.ietpune.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"name" })})
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Size(min=2,max=30,message = "subject lenght must in between 2 to 30 char...")
	private String name;
	
	
	@ManyToOne()
	@JoinColumn
	private Course course;
	
	

	@OneToMany(mappedBy = "subject",cascade = {CascadeType.MERGE,
            CascadeType.REFRESH})
	private List<Paper> paperList;
	public Subject() {
		super();
	}
	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public List<Paper> getPaperList() {
		return paperList;
	}


	public void setPaperList(List<Paper> paperList) {
		this.paperList = paperList;
	}


	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + "]";
	}
	
}
