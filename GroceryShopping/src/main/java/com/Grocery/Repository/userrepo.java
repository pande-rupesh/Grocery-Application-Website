package com.Grocery.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Grocery.model.*;
@Repository
public interface userrepo extends JpaRepository<User, Integer>{
	
	User save(User u);
	boolean existsByEmail(String email);
	boolean existsByMobile(String mobile);
	User findByEmail(String email);
}
