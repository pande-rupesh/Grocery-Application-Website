package com.Grocery.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Grocery.Repository.Orderdeatailsrepo;
import com.Grocery.Repository.cartRepository;
import com.Grocery.Repository.orderItemrepo;

import com.Grocery.Util.OderId;
import com.Grocery.Util.Sendmail;
import com.Grocery.model.Cart;
import com.Grocery.model.OrderItem;
import com.Grocery.model.Orderdeatails;

import com.Grocery.model.Shipping_Address;

@Service
@Transactional
public class orderItemserviceImpl implements orderItemsservice {

	@Autowired
	private cartRepository cartRepository;

	@Autowired
	private orderItemrepo orderItemrepo;

	@Autowired
	private OderId oderId;

	@Autowired
	private Orderdeatailsrepo orderdeatailsrepo; 
	
	@Autowired
	private Sendmail sendmail;

	@Override
	public void saveOrder(Integer uid, Shipping_Address address) {

	    List<Cart> carts = cartRepository.findByUserId(uid);

	    String orderId = oderId.generateOrderId();
	    
	   Orderdeatails orderdeatails=new Orderdeatails();
	   
	   Shipping_Address newAddress = new Shipping_Address();
	    newAddress.setName(address.getName());
	    newAddress.setEmail(address.getEmail());
	    newAddress.setMobile(address.getMobile());
	    newAddress.setStreet(address.getStreet());
	    newAddress.setCity(address.getCity());
	    newAddress.setPincode(address.getPincode());
	    newAddress.setPaymentType(address.getPaymentType());
	   
	   orderdeatails.setOrderId(orderId);
	   orderdeatails.setStatus("PLACED");
	   orderdeatails.setAddress(newAddress);
	   orderdeatails.setUser(carts.get(0).getUser());
	   orderdeatailsrepo.save(orderdeatails);

	   double totalPrice=0.0;
	   int  totalQty=0;
	   
	    for (Cart c : carts) {

	    	OrderItem o=new OrderItem();
	        o.setOrderId(orderId);
	        o.setPrice(c.getProduct().getPrice());
	        o.setQuantity(c.getQuantity());
	        o.setStatus("Placed");
	        o.setPaymentType(address.getPaymentType());
	        o.setUser(c.getUser());
	        o.setProduct(c.getProduct());
	        
	        totalPrice += c.getProduct().getPrice() * c.getQuantity();
	        totalQty += c.getQuantity();


	        orderItemrepo.save(o);
	    }
	    
	    orderdeatails.setTotalPrice(totalPrice);
	    orderdeatails.setTotalQuantity(totalQty);
	    orderdeatailsrepo.save(orderdeatails);
	    
	    cartRepository.deleteByUser(carts.get(0).getUser().getId());
	   
	}

}
