package com.example.artspectrum.utils.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Role implements GrantedAuthority {
	ROLE_ADMIN("ROLE_ADMIN", 1),
	ROLE_USER("ROLE_USER", 2),
	ROLE_ARTIST("ROLE_ARTIST", 3);
	
	private final String nameRole;
	private final int idRole;
	
	private Role(String nameRole, int idRole) {
		this.nameRole = nameRole;
		this.idRole = idRole;
	}
	
	public Role getRoleById(int idRole) {
		for (Role role : Role.values()) {
            if (role.getIdRole() == idRole) {
                return role;
            }
        }
        return null;
	}
	
	public static List<Role> getRoles() {
		return Arrays.asList(Role.values());
	}
	
	public static Role getRoleByName(String name) {
		for (Role role : Role.values()) {
			if (role.getNameRole().equalsIgnoreCase(name)) {
				return role;
			}
		}
		return null;
	}
	
	@Override
	public String getAuthority() {
		return nameRole;
	}
}

