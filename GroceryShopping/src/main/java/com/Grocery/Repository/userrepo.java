package com.Grocery.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Grocery.model.*;
@Repository
public interface userrepo extends JpaRepository<User, Integer>{
	
	User save(User u);
	boolean existsByEmail(String email);
	boolean existsByMobile(String mobile);
	User findByEmail(String email);
	List<User> findByRole(String role);
    User findById(int id);
    
   
    @Transactional
    @Query("UPDATE User u SET u.isEnabled = :status WHERE u.id = :id")
    @Modifying
    void updateStatus(@Param("status") boolean status, @Param("id") int id);
    
    @Transactional
    @Query("UPDATE User u SET u.token = :string WHERE u.id = :id")
    @Modifying
    void updateToken(@Param("string") String string, @Param("id") int id);
    
    User findByToken(String token);
    
    @Transactional
    @Query("UPDATE User u SET u.password = :string WHERE u.id = :id")
    @Modifying
    void updatePassword(@Param("string") String password, @Param("id") int id);
	
}
