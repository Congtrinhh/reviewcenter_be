package com.trinhquycong.reviewcenter.entity;
// Generated Dec 11, 2021, 7:48:03 AM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Resource generated by hbm2java
 */
@Entity
@Table(name = "resource", catalog = "reviewcenter")
public class Resource implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Boolean active;
	private Date createdAt;
	private Date updatedAt;
	private Set<Role> roles = new HashSet<Role>(0);


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "active")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 19)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 19)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@ManyToMany(mappedBy = "resources", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<Role> getRoles() {
		return this.roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		role.getResources().add(this);
		this.roles.add(role);
	}
	
	public void deleteRole(Role role) {
		role.getResources().remove(this);
		this.roles.remove(role);
	}

	public Resource(Long id, String name, Boolean active, Date createdAt, Date updatedAt, Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.roles = roles;
	}

	public Resource() {
		super();
		active=true;
	}
	
	

}
