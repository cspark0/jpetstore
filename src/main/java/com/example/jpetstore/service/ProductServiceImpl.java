package com.example.jpetstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpetstore.domain.Product;
import com.example.jpetstore.repository.ProductRepository;

/**
 * @author Chang-Sup Park
 */

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepo;

	@Override
	public Product getProduct(String prodId) {
		Optional<Product> exist = productRepo.findById(prodId);
        if(exist.isPresent()) {
        	return exist.get();
        }
        return null;
	}

	public List<Product> getProductListByCategory(String categoryId) {
		return productRepo.findByCategoryId(categoryId);
	}
	
	@Override
	public Product createProduct(Product product) {
		return productRepo.save(product);		
	}

	@Override
	public void updateProduct(String prodId, Product product) {
		productRepo.save(product);				
	}

	@Override
	public void removeProduct(String prodId) {
        productRepo.deleteById(prodId);
	}
}
