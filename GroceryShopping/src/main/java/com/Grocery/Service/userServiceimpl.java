package com.Grocery.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Grocery.Repository.userrepo;
import com.Grocery.model.User;

@Service
@Transactional
public class userServiceimpl implements userService{

	@Autowired
	private userrepo repo;
	
	@Override
	public User save(User u) {
		// TODO Auto-generated method stub
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
