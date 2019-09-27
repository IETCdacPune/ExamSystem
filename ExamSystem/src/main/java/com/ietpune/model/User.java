package com.ietpune.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String prn;
	private String password;
	@OneToMany(cascade = {
			CascadeType.MERGE,
            CascadeType.REFRESH},fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	public User() {
		super();
	}
	public User(User user) {
		super();
		this.userId = user.userId;
		this.prn = user.prn;
		this.password = user.password;
		this.roles = user.roles;
	}
	public User( String prn, String password, Set<Role> roles) {
		super();
		this.prn = prn;
		this.password = password;
		this.roles = roles;
	}
	public User(int userId, String prn, String password, Set<Role> roles) {
		super();
		this.userId = userId;
		this.prn = prn;
		this.password = password;
		this.roles = roles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPrn() {
		return prn;
	}

	public void setPrn(String prn) {
		this.prn = prn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", prn=" + prn + ", password=" + password + "]";
	}
	
}
