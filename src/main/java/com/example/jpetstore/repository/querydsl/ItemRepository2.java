package com.example.jpetstore.repository.querydsl;

import java.util.List;

//import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.jpetstore.domain.Item;

public interface ItemRepository2
        extends JpaRepository<Item, String>, QuerydslPredicateExecutor<Item>,
        		ItemRepositoryCustom {
				
	// Query Methods based on Spring Data JPA
	
	List<Item> getByProductId(String productId);
	
	boolean existsByItemIdAndQuantityGreaterThan(String itemId, int qty);
	
	// Methods are also inherited from ItemRepositoryCustom interface  
}
