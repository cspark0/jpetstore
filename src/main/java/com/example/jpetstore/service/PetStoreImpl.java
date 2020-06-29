package com.example.jpetstore.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.jpetstore.dao.AccountDao;
import com.example.jpetstore.dao.AuctionDao;
import com.example.jpetstore.dao.CategoryDao;
import com.example.jpetstore.dao.EventDao;
import com.example.jpetstore.dao.ItemDao;
import com.example.jpetstore.dao.OrderDao;
import com.example.jpetstore.dao.ProductDao;
import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Auction;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Order;
import com.example.jpetstore.domain.Product;

/**
 * JPetStore primary business object.
 * 
 * <p>This object makes use of five DAO objects, decoupling it
 * from the details of working with persistence APIs. So
 * although this application uses iBATIS for data access,
 * a different persistence tool could be dropped in without
 * breaking this class.
 *
 * <p>The DAOs are made available to the instance of this object
 * using Dependency Injection. (The DAOs are in turn configured using
 * Dependency Injection themselves.) We use Setter Injection here,
 * exposing JavaBean setter methods for each DAO. This means there is
 * a JavaBean property for each DAO. In the present case, the properties
 * are write-only: there are no corresponding getter methods. Getter
 * methods for configuration properties are optional: Implement them
 * only if you want to expose those properties to other business objects.
 *
 * <p>There is one instance of this class in the JPetStore application.
 * In Spring terminology, it is a "singleton", referring to a
 * per-Application Context singleton. The factory creates a single
 * instance; there is no need for a private constructor, static
 * factory method etc as in the traditional implementation of
 * the Singleton Design Pattern. 
 *
 * <p>This is a POJO. It does not depend on any Spring APIs.
 * It's usable outside a Spring container, and can be instantiated
 * using new in a JUnit test. However, we can still apply declarative
 * transaction management to it using Spring AOP.
 *
 * <p>This class defines a default transaction annotation for all methods.
 *
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified by Changsup Park
 */
@Service
@Transactional
public class PetStoreImpl implements PetStoreFacade { 
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private AuctionDao auctionDao;
	@Autowired
	private OrderDao orderDao;

	private EventDao eventDao;
	@Autowired		// applicationContext.xml占쎈퓠 占쎌젟占쎌벥占쎈쭆 scheduler 揶쏆빘猿쒐몴占� 雅뚯눘�뿯 獄쏆룇�벉
	private ThreadPoolTaskScheduler scheduler;


	//-------------------------------------------------------------------------
	// Operation methods, implementing the PetStoreFacade interface
	//-------------------------------------------------------------------------

	@Override
	public void insertAuctionItem(Item item) {
		// TODO Auto-generated method stub
		itemDao.insertAuctionItem(item);
	}

	@Override
	public void insertItem(Item item) {
		// TODO Auto-generated method stub
		itemDao.insertItem(item);
		
	}

	public List<Item> getItemListIsAuction() {
		// TODO Auto-generated method stub
		return itemDao.getItemListIsAuction();
	}

	public Account getAccount(String username) {
		return accountDao.getAccount(username);
	}

	public Account getAccount(String username, String password) {
		return accountDao.getAccount(username, password);
	}

	public void insertAccount(Account account) {
		accountDao.insertAccount(account);
	}

	public void updateAccount(Account account) {
		accountDao.updateAccount(account);
	}

	public List<String> getUsernameList() {
		return accountDao.getUsernameList();
	}

	public List<Category> getCategoryList() {
		return categoryDao.getCategoryList();
	}

	public Category getCategory(String categoryId) {
		return categoryDao.getCategory(categoryId);
	}

	public List<Product> getProductList() {
		// TODO Auto-generated method stub
		return productDao.getProductList();
	}


	public Product getProductByName(String name) {
		// TODO Auto-generated method stub
		return productDao.getProductByName(name);
	}

	public List<Product> getProductListByCategory(String categoryId) {
		return productDao.getProductListByCategory(categoryId);
	}

	public List<Product> searchProductList(String keywords) {
		return productDao.searchProductList(keywords);
	}

	public Product getProduct(String productId) {
		return productDao.getProduct(productId);
	}

	public List<Item> getItemListByProduct(String productId) {
		return itemDao.getItemListByProduct(productId);
	}
	
	public Item getItem(String itemId) {
		return itemDao.getItem(itemId);
	}

	public boolean isItemInStock(String itemId) {
		return itemDao.isItemInStock(itemId);
	}

	public void insertOrder(Order order) {
		itemDao.updateQuantity(order);	    
		orderDao.insertOrder(order);
	}
	
	public Order getOrder(int orderId) {
		return orderDao.getOrder(orderId);
	}

	public List<Order> getOrdersByUsername(String username) {
		return orderDao.getOrdersByUsername(username);
	}
public void testScheduler(Date closingTime) {
		
		Runnable updateTableRunner = new Runnable() {	
			// anonymous class 占쎌젟占쎌벥
			@Override
			public void run() {   // 占쎈뮞�놂옙鴉딅��쑎占쎈퓠 占쎌벥占쎈퉸 沃섎챶�삋占쎌벥 占쎈뱟占쎌젟 占쎈뻻占쎌젎占쎈퓠 占쎈뼄占쎈뻬占쎈쭍 占쎌삂占쎈씜占쎌뱽 占쎌젟占쎌벥				
				Date curTime = new Date();
				// 占쎈뼄占쎈뻬 占쎈뻻占쎌젎占쎌벥 占쎈뻻揶쏄낯�뱽 占쎌읈占쎈뼎占쎈릭占쎈연 域뱄옙 占쎈뻻揶쏉옙 占쎌뵠占쎌읈占쎌벥 closing time 揶쏅�れ뱽 揶쏅쉴�뮉 event占쎌벥 占쎄맒占쎄묶�몴占� 癰귨옙野껓옙 

				//주석eventDao.closeEvent(curTime);	// EVENTS 占쎈�믭옙�뵠�뇡遺우벥 占쎌쟿�굜遺얜굡 揶쏄퉮�뻿	

				itemDao.closeEvent(curTime);	// EVENTS 占쎈�믭옙�뵠�뇡遺우벥 占쎌쟿�굜遺얜굡 揶쏄퉮�뻿	

				System.out.println("updateTableRunner is executed at " + curTime);
			}
		};
		

		//주석HashMap<String, Date> hashMap = new HashMap<String, Date>();
		//주석hashMap.put("curTime", new Date());			// 占쎌겱占쎌삺 占쎈뻻揶쏉옙: PK 揶쏅�れ몵嚥∽옙 占쎄텢占쎌뒠
		//주석hashMap.put("closingTime", closingTime);	// 沃섎챶�삋占쎌벥 �넫�굝利� 占쎈뻻揶쏉옙
		//주석eventDao.insertNewEvent(hashMap);	// EVENTS 占쎈�믭옙�뵠�뇡遺용퓠 占쎌쟿�굜遺얜굡 占쎄땜占쎌뿯

		// 占쎈뮞�놂옙餓ο옙 占쎄문占쎄쉐: closingTime占쎈퓠 updateTableRunner.run() 筌롫뗄�꺖占쎈굡 占쎈뼄占쎈뻬


		scheduler.schedule(updateTableRunner, closingTime);  
		
		System.out.println("updateTableRunner has been scheduled to execute at " + closingTime);
	}


public void insertQuantity(String itemId, int qty) {
	// TODO Auto-generated method stub
	itemDao.insertQuantity(itemId, qty);
}

@Override
public List<Item> getItemListByUsername(String username) {
	// TODO Auto-generated method stub
	return itemDao.getItemListByUsername(username);
}


public void insertAuction(Auction auction) {
	// TODO Auto-generated method stub
	auctionDao.insertAuction(auction);
	
}
public List<Auction> getAuctionByUsername(String username) {
	// TODO Auto-generated method stub
	return auctionDao.getAuctionByUsername(username);
}




@Override
public void updateAuctionItem(Item item) {
	itemDao.updateAuctionItem(item);
	
}

@Override
public List<Item> getAuctionItemListByUsername(String username) {
	// TODO Auto-generated method stub
	return itemDao.getAuctionItemListByUsername(username);
}

@Override
public void updateAuctionId(Auction auction) {
	// TODO Auto-generated method stub
	itemDao.updateAuctionId(auction);
}

@Override
public int getMaxAuctionId(String itemId) {
	// TODO Auto-generated method stub
	return auctionDao.getMaxAuctionId(itemId);
}

public void updateItem(Item item) {
	// TODO Auto-generated method stub
	itemDao.updateItem(item);
}

@Override
public void deleteItem(String itemId) {
	// TODO Auto-generated method stub
	itemDao.deleteItem(itemId);

}

}