package com.Grocery.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.Grocery.Repository.cartRepository;
import com.Grocery.Repository.productrepo;
import com.Grocery.Repository.userrepo;
import com.Grocery.model.Cart;
import com.Grocery.model.Product;
import com.Grocery.model.User;

@Service
@Transactional
public class cartServiceImpl implements cartService{

	@Autowired
	private cartRepository cartRepository;
	
	@Autowired
	private userrepo user;
	
	@Autowired
	private productrepo product;
	
	 @Override
	    public Cart saveCart(int productId, int userId) {

	        User u = user.findById(userId);
	        Product p = product.findById(productId);

	        // Check if cart already contains this product
	        Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);

	        Cart c;

	        if (ObjectUtils.isEmpty(cart)) {

	            // NEW CART ITEM
	            c = new Cart();
	            c.setUser(u);
	            c.setProduct(p);
	            c.setQuantity(1);
	            c.setTotalPrice(p.getPrice());

	        } else {

	            // UPDATE EXISTING CART ITEM
	            c = cart;
	            int newQty = c.getQuantity() + 1;
	            c.setQuantity(newQty);
	            c.setTotalPrice(newQty * c.getProduct().getPrice());
	        }

	        return cartRepository.save(c);
	    }

	 @Override
	 public List<Cart> findByUserId(Integer uid) {
		// TODO Auto-generated method stub
		return cartRepository.findByUserId(uid);
	 }

	 @Override
	 public int deleteById(int id) {
		// TODO Auto-generated method stub
		return cartRepository.deleteById(id);
	 }

	 @Override
	 public Cart findByUserIdAndProductId(Integer uid, Integer pid) {
		// TODO Auto-generated method stub
		return cartRepository.findByUserIdAndProductId(uid, pid);
	 }

	 @Override
	 public void save(Cart cart) {
		cartRepository.save(cart);
		
	 }

	 @Override
	 public int deleteByUser(int user_id) {
		return  cartRepository.deleteByUser(user_id);
	 }
	

}
