package com.Grocery.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Grocery.model.Cart;

@Repository
public interface cartRepository extends JpaRepository<Cart, Integer>{
	List<Cart> findByUserId(Integer uid);
	
	public Cart findByUserIdAndProductId(Integer uid,Integer pid);

	@Transactional
	@Query(value = "delete from Cart where id=?1",nativeQuery = true)
	@Modifying
	 int deleteById(int id);
	
	@Transactional
	@Query(value = "delete from Cart where user_id=?1",nativeQuery = true)
	@Modifying
	 int deleteByUser(int user_id);

}
