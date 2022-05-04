package com.example.jpetstore.service;

import java.util.List;

import com.example.jpetstore.domain.Product;

/**
 * @author Chang-Sup Park
 */

public interface ProductService {
	public Product getProduct(String prodId);
	public List<Product> getProductListByCategory(String categoryId);
	public Product createProduct(Product product);
	public void updateProduct(String prodId, Product product);  
	public void removeProduct(String productId);	   
}
