package com.example.jpetstore.repository;

//import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpetstore.domain.Item;

import java.util.List;

public interface ItemRepository
        extends JpaRepository<Item, String> {

	// public List<Item> getItemListByProduct(String productId) --> 아래의 query method 이용
	List<Item> getByProductId(String productId);
		// 주의: 위 메소드는 Item entity에서 참조하는 Product entity도 같이 검색함
		// (@ManyToOne의 default fetchType = EAGER)
	
  	// Item getItem(String itemId) --> JpaRepository#getReferenceByID() 이용
	
	// boolean isItemInStock(String itemId) --> 아래의 query method 이용
	boolean existsByItemIdAndQuantityGreaterThan(String itemId, int qty);

	// public void updateQuantity(Order order) 
	// --> service class인 PetStoreImpl의 insertOrder()에서 세부 로직 구현 
	// --> JpaRepository#getReferenceByID(), CRUDRepository#save() 이용
	
}
