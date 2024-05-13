package com.example.artspectrum.services.admin;

import com.example.artspectrum.dto.UserDTO;
import com.example.artspectrum.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAdminUsersService extends IAdminService<User, UserDTO> {
	User getUserByLogin(String login);
	void blockUser(Long userId);
	void unblockUser(Long userId);
	boolean isUsernameAvailable(String username);
	UserDetails getCurrentAdmin();
	boolean isAdmin(Long userId);
	long countUsers();
}
