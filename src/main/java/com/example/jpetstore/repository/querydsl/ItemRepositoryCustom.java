package com.example.jpetstore.repository.querydsl;

import com.example.jpetstore.domain.Item;

import java.util.List;

public interface ItemRepositoryCustom {
	
	List<Item> findFemaleItemsWithPriceInRange(double minVal, double maxVal);
	
	List<Item> search(List<String> keywords);
}
