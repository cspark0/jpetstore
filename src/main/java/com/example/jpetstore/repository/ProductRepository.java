		package com.example.jpetstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jpetstore.domain.Product;

import java.util.List;

/**
 * @author Chang-Sup Park
 */

public interface ProductRepository extends JpaRepository<Product, String> {
	List<Product> findByCategoryId(String categoryId);
}
