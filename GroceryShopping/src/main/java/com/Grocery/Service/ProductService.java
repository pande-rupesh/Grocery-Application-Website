package com.Grocery.Service;

import java.util.List;

import com.Grocery.model.Product;

public interface ProductService {
	Product save(Product p);
	List<Product> findAll();
	int deleteById(int id);
}
