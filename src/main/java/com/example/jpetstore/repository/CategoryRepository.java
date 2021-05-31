package com.example.jpetstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.example.jpetstore.domain.Category;

public interface CategoryRepository extends Repository<Category, String> {
	List<Category> findAll();
	Optional<Category> findById(String categoryId);
}
