package com.example.jpetstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpetstore.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
/*
	List<Category> getCategoryList() throws DataAccessException; --> JpaRepository#findAll() 이용

	Category getCategory(String categoryId) throws DataAccessException; --> JpaRepository#getByID() 이용 
*/
}
