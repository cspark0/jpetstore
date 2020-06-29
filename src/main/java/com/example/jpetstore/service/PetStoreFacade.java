package com.example.jpetstore.service;

import java.util.Date;
import java.util.List;

import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Auction;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Order;
import com.example.jpetstore.domain.Product;

/**
 * JPetStore's central business interface.
 *
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
public interface PetStoreFacade {

	Account getAccount(String username);

	Account getAccount(String username, String password);

	void insertAccount(Account account);

	void updateAccount(Account account);

	List<String> getUsernameList();


	List<Category> getCategoryList();

	Category getCategory(String categoryId);
	

	List<Product> getProductListByCategory(String categoryId);

	List<Product> searchProductList(String keywords);

	Product getProduct(String productId);


	List<Item> getItemListByProduct(String productId);

	Item getItem(String itemId);

	boolean isItemInStock(String itemId);


	void insertOrder(Order order);

	Order getOrder(int orderId);

	List<Order> getOrdersByUsername(String username);
	

	List<Item> getItemListIsAuction();

	void testScheduler(Date closingTime);
	
	void insertAuctionItem(Item item);
	void insertAuction(Auction auction);
	
	void insertItem(Item item);

	List<Product> getProductList();

	Product getProductByName(String name);

	void insertQuantity(String itemId, int qty);

	List<Auction> getAuctionByUsername(String username);

	void updateAuctionItem(Item item);
	void updateAuctionId(Auction auction);

	int getMaxAuctionId(String itemId);
	
	List<Item> getItemListByUsername(String username);

	List<Item> getAuctionItemListByUsername(String username);

	void updateItem(Item item);

	void deleteItem(String itemId);

}