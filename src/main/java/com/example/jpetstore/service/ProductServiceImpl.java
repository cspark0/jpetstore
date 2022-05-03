package com.example.jpetstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpetstore.dao.ProductDao;
import com.example.jpetstore.domain.Product;

/**
 * @author Chang-Sup Park
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	public Product getProduct(String prodId) {
		return productDao.getProduct(prodId);
	}

	@Override
	public void createProduct(Product product) {
		productDao.createProduct(product);		
	}

	@Override
	public void updateProduct(String prodId, Product product) {
		productDao.updateProduct(prodId, product);				
	}

	@Override
	public Product removeProduct(String prodId) {
        return productDao.removeProduct(prodId);
	}
}
