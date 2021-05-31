package com.example.jpetstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jpetstore.domain.Item;

import java.util.List;

public interface ItemRepository
        extends JpaRepository<Item, String> {

	List<Item> getByProductId(String productId);
	
//	boolean findByItemIdAndQuantityGreaterThan(String itemId, int qty);

	@Query("update Item i " + 
			"set i.quantity = i.quantity - :inc " +
			"where i.itemId = :itemId")
	void updateInventoryQuantity(@Param("itemId") String itemId, @Param("inc") int increment);
}
