package com.example.jpetstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpetstore.domain.Product;

import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, String> {

	List<Product> findByName(String productName);

	List<Product> findByCategoryId(String categoryId);
	
	long countByCategoryId(String categoryId);
	
	long deleteByCategoryId(String categoryId);
	
/*
	@Query("select p from Product p where p.name like ?1")
	List<Product> searchProductList(String keywords);
*/	
	List<Product> findByNameContaining(String keywords);
}
