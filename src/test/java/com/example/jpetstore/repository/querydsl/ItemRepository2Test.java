package com.example.jpetstore.repository.querydsl;

import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.QItem;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemRepository2Test {

    @Autowired
    ItemRepository2 itemRepository;

    @Test
    public void queryDSLTest1() {
        System.out.println("queryDSLTest(): find female items with a price in range (10, 99)");
        
        Iterable<Item> itemList = itemRepository.findFemaleItemsWithPriceInRange(10, 99);        

        for (Item item : itemList) {           
            System.out.println(item.getItemId());
            System.out.println(item.getAttribute1());
            System.out.println(item.getProductId()  + " " + item.getProduct().getName());
            System.out.println("$" + item.getListPrice());
            System.out.println(item.getUnitCost());
            System.out.println("------------------");
        }
    }
    
    @Test
    public void queryDSLTest2() {
    	System.out.println("queryDSLTest(): find female items with a price in range (100, 500) ordered by price");
    	
        QItem qItem = QItem.item;
        Predicate predicate = qItem.attribute1.containsIgnoreCase("female")
                .and(qItem.listPrice.between(100, 500));
        OrderSpecifier<Double> orderSpecifier = new OrderSpecifier<>(Order.ASC, qItem.listPrice);
        Iterable<Item> itemList = itemRepository.findAll(predicate, orderSpecifier);

        for (Item item : itemList) {            
            System.out.println(item.getItemId());
            System.out.println(item.getAttribute1());
            System.out.println(item.getProductId()  + " " + item.getProduct().getName());
            System.out.println("$" + item.getListPrice());
            System.out.println(item.getUnitCost());
            System.out.println("------------------");
        }        
    }
    
}