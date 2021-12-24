package com.trinhquycong.reviewcenter.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	@NotBlank
	private String email;

	private String displayName;

	private String provider;

	private byte[] avatar;

	private Boolean active;

	private Date createdAt;
	
	private Date updatedAt;

	@NotBlank
	private Set<String> roles = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public UserDto(@NotBlank String email, String displayName, String provider, byte[] avatar, Boolean active,
			Date createdAt, Date updatedAt, @NotBlank Set<String> roles) {
		super();
		this.email = email;
		this.displayName = displayName;
		this.provider = provider;
		this.avatar = avatar;
		this.active = active;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.roles = roles;
	}

	public UserDto() {
		super();
	}
}
