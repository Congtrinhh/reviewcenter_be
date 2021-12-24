package com.trinhquycong.reviewcenter.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.trinhquycong.reviewcenter.validator.RoleMatches;

@RoleMatches
public class AdminRegisterDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String email;
	
	@Size(min = 8)
	private String password;

	@NotEmpty
	private String displayName;

	private byte[] avatar;

	@NotEmpty
	private Set<String> roles = new HashSet<>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public AdminRegisterDto(@NotEmpty String email, @Size(min = 8) String password, @NotEmpty String displayName,
			byte[] avatar, @NotEmpty Set<String> roles) {
		super();
		this.email = email;
		this.password = password;
		this.displayName = displayName;
		this.avatar = avatar;
		this.roles = roles;
	}

	public AdminRegisterDto() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(avatar);
		result = prime * result + Objects.hash(displayName, email, password, roles);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminRegisterDto other = (AdminRegisterDto) obj;
		return Arrays.equals(avatar, other.avatar) && Objects.equals(displayName, other.displayName)
				&& Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(roles, other.roles);
	}
}
