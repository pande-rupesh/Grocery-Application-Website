package com.Grocery.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Grocery.model.Product;

@Repository
public interface productrepo extends JpaRepository<Product, Integer> {

	Product save(Product p);

	List<Product> findAll();
	
	@Transactional
	@Query(value = "delete from Product where id=?1",nativeQuery = true)
	@Modifying
	 int deleteById(int id);

	Product findById(int id);
}
