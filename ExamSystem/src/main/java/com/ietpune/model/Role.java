package com.ietpune.model;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.NaturalId;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int roleId;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 25)
    private RoleName roleName;
    @ManyToMany(mappedBy = "roles")
    private List < User > users;
	public Role() {
		super();
	}

	public Role(int roleId, RoleName role) {
		super();
		this.roleId = roleId;
		this.roleName = role;
	}

	public Role(RoleName role) {
		this.roleName=role;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public RoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + roleName + "]";
	}
    
}
