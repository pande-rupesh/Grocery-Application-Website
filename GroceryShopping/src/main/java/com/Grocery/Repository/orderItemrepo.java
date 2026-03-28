package com.Grocery.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Grocery.model.OrderItem;


@Repository
public interface orderItemrepo extends JpaRepository<OrderItem, Integer>{

	
}
