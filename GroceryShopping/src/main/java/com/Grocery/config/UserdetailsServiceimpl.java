package com.Grocery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Grocery.Repository.userrepo;
import com.Grocery.model.User;

@Service
public class UserdetailsServiceimpl implements UserDetailsService{
     @Autowired
	private userrepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=repo.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("User Not found Exception");
		}
		return new Customeuser(user);
	}

}
