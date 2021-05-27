package com.example.jpetstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpetstore.domain.Category;

public interface CategoryRepository
        extends JpaRepository<Category, String> {
    /*List<User> findByNameStartingWithOrderByNameAscCreateDateDesc(String name);

    List<User> findByNameStartingWith(String name, Sort sort);

    List<User> findByNameStartingWith(String name, Pageable pageable);

    User findByName(String name);*/
}
