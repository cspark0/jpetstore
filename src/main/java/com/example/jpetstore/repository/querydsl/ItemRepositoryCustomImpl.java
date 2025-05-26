package com.example.jpetstore.repository.querydsl;

import java.util.List;

import org.springframework.stereotype.Repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;

//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;

@Repository
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {
	
	//@PersistenceContext
    //private EntityManager em;

	//JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
	
	@Autowired JPAQueryFactory jpaQueryFactory;    // JPAQueryFactory bean 설정 및 이용 시

	@Override
	public List<Item> findFemaleItemsWithPriceInRange(double minVal, double maxVal) {
		QItem qItem = QItem.item;

		List<Item> itemList = jpaQueryFactory.selectFrom(qItem)
				.where(qItem.attribute1.containsIgnoreCase("female")
						.and(qItem.listPrice.between(minVal, maxVal)))
				.orderBy(qItem.listPrice.asc())
				.fetch();

		return itemList;
	}
	
	@Override
	public List<Item> search(List<String> keywords) {		
		List<Item> itemList = null;		
		
		// search items containing the given keywords using QueryDSL ...  
		
		return itemList;
	}

}
