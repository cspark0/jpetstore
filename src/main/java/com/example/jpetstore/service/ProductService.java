package com.example.jpetstore.service;

import com.example.jpetstore.domain.Product;

public interface ProductService {
	Product getProduct(String prodId);
	void createProduct(Product product);
	void updateProduct(String prodId, Product product);  
	public Product removeProduct(String productId);	   
}
