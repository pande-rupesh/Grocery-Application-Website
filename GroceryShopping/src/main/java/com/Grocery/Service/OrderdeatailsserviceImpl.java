package com.Grocery.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Grocery.Repository.Orderdeatailsrepo;
import com.Grocery.model.Orderdeatails;

@Service
@Transactional
public class OrderdeatailsserviceImpl implements Orderdeatailsservice{

	@Autowired
	private Orderdeatailsrepo orderdeatailsrepo;
	
	@Override
	public Orderdeatails findByOrderId(String OrderId) {
		// TODO Auto-generated method stub
		return  orderdeatailsrepo.findByOrderId(OrderId);
	}

}
