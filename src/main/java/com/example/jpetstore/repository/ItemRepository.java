package com.example.jpetstore.repository;

//import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jpetstore.domain.Item;

import java.util.List;

public interface ItemRepository
        extends JpaRepository<Item, String> {

	List<Item> getByProductId(String productId);
	// 주의: 위 메소드는 Item entity에서 참조하는 Product entity도 같이 검색함
	// (@ManyToOne의 default fetchType = EAGER)
	
/* 
  	Item getItem(String itemId) throws DataAccessException; --> JpaRepository#getById() 이용
*/
	
/* 
	boolean isItemInStock(String itemId) throws DataAccessException;  
*/
	boolean existsByItemIdAndQuantityGreaterThan(String itemId, int qty);

	@Query("update Item i " + 
			"set i.quantity = i.quantity - :inc " +
			"where i.itemId = :itemId")			// JPQL 이용
	void updateInventoryQuantity(@Param("itemId") String itemId, @Param("inc") int increment);
}
