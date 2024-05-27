package com.example.jpetstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.example.jpetstore.domain.Product;

import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, String> {

	// public Product getProduct(String productId) --> JpaRepository#getReferenceByID() 이용
	
	// public List<Product> getProductListByCategory(String categoryId) -> Using query method below: 
	List<Product> findByCategoryId(String categoryId);
	
	List<Product> findByName(String productName);

	long countByCategoryId(String categoryId);
	
	long deleteByCategoryId(String categoryId);
			
/*
	@Query("select p from Product p where p.name like '%?1%'")	// JPQL
	List<Product> searchProductList(String keywords);
	--> --> 아래의 query method 이용 가능 
*/
	List<Product> findByNameIgnoreCaseContaining(String keywords);
}
