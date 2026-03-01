package com.Grocery.Service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.Grocery.model.User;

public interface userService {

	User save(User u);
	boolean existsByEmail(String email);
	boolean existsByMobile(String mobile);
	User findByEmail(String email);
	List<User> findByRole(String role);
	User findById(int id);
	void updateStatus(boolean status, int id);
	void updateToken(String string,int id);
	 User findByToken(String token);
	 void updatePassword(String password,int id);
}
