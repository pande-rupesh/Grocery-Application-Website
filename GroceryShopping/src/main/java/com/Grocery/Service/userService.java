package com.Grocery.Service;

import com.Grocery.model.User;

public interface userService {

	User save(User u);
	boolean existsByEmail(String email);
	boolean existsByMobile(String mobile);
	User findByEmail(String email);
}
