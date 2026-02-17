package com.Grocery.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Grocery.Repository.productrepo;
import com.Grocery.model.Product;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private productrepo repo;
	@Override
	public Product save(Product p) {
		// TODO Auto-generated method stub
		return repo.save(p);
	}
	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return repo.deleteById(id);
	}

}
