package com.Grocery.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Grocery.Repository.userrepo;
import com.Grocery.model.User;

@Service
@Transactional
public class userServiceimpl implements userService{

	@Autowired
	private userrepo repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User save(User u) {
		u.setRole("ROLE_USER");
		String encodedpassword=passwordEncoder.encode(u.getPassword());
		u.setPassword(encodedpassword);
		return repo.save(u);
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return repo.existsByEmail(email);
	}

	@Override
	public boolean existsByMobile(String mobile) {
		// TODO Auto-generated method stub
		return repo.existsByMobile(mobile);
	}

}
