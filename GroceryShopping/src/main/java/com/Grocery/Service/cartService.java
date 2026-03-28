package com.Grocery.Service;

import java.util.List;

import com.Grocery.model.Cart;

public interface cartService {

	Cart saveCart(int prodcutId, int Userid);
	
	List<Cart> findByUserId(Integer uid);
	
	int deleteById(int id);
	public Cart findByUserIdAndProductId(Integer uid,Integer pid);

	void save(Cart cart);
	
	int deleteByUser(int user_id);
}
