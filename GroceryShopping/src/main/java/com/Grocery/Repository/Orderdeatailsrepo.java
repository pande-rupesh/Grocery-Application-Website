package com.Grocery.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Grocery.model.Orderdeatails;

@Repository
public interface Orderdeatailsrepo extends JpaRepository<Orderdeatails, Integer>{

	  Orderdeatails findByOrderId(String OrderId);
}
