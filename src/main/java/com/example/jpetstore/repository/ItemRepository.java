package com.example.jpetstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpetstore.domain.Item;

public interface ItemRepository
        extends JpaRepository<Item, String> {
    /*List<User> findByNameStartingWithOrderByNameAscCreateDateDesc(String name);

    List<User> findByNameStartingWith(String name, Sort sort);

    List<User> findByNameStartingWith(String name, Pageable pageable);

    User findByName(String name);*/
}
