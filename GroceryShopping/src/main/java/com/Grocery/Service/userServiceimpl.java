package com.Grocery.Service;

import java.util.List;

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
		u.setEnabled(true);
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

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return repo.findByEmail(email);
	}

	@Override
	public List<User> findByRole(String role) {
		// TODO Auto-generated method stub
		return repo.findByRole(role);
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public void updateStatus(boolean status, int id) {
		repo.updateStatus(status, id);
		
	}

	@Override
	public void updateToken(String string, int id) {
		repo.updateToken(string, id);
		
	}

	@Override
	public User findByToken(String token) {
		return repo.findByToken(token);
	}

	@Override
	public void updatePassword(String password, int id) {
		repo.updatePassword(password, id);
		
	}



}
