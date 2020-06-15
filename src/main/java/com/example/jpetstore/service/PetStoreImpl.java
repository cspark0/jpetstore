package com.example.jpetstore.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.jpetstore.dao.AccountDao;
import com.example.jpetstore.dao.CategoryDao;
import com.example.jpetstore.dao.EventDao;
import com.example.jpetstore.dao.ItemDao;
import com.example.jpetstore.dao.OrderDao;
import com.example.jpetstore.dao.ProductDao;
import com.example.jpetstore.domain.Account;
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
	private OrderDao orderDao;

	private EventDao eventDao;
	@Autowired		// applicationContext.xml�뿉 �젙�쓽�맂 scheduler 媛앹껜瑜� 二쇱엯 諛쏆쓬
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
			// anonymous class �젙�쓽
			@Override
			public void run() {   // �뒪耳�伊대윭�뿉 �쓽�빐 誘몃옒�쓽 �듅�젙 �떆�젏�뿉 �떎�뻾�맆 �옉�뾽�쓣 �젙�쓽				
				Date curTime = new Date();
				// �떎�뻾 �떆�젏�쓽 �떆媛곸쓣 �쟾�떖�븯�뿬 洹� �떆媛� �씠�쟾�쓽 closing time 媛믪쓣 媛뽯뒗 event�쓽 �긽�깭瑜� 蹂�寃� 
				eventDao.closeEvent(curTime);	// EVENTS �뀒�씠釉붿쓽 �젅肄붾뱶 媛깆떊	
				System.out.println("updateTableRunner is executed at " + curTime);
			}
		};
		
		HashMap<String, Date> hashMap = new HashMap<String, Date>();
		hashMap.put("curTime", new Date());			// �쁽�옱 �떆媛�: PK 媛믪쑝濡� �궗�슜
		hashMap.put("closingTime", closingTime);	// 誘몃옒�쓽 醫낅즺 �떆媛�
		eventDao.insertNewEvent(hashMap);	// EVENTS �뀒�씠釉붿뿉 �젅肄붾뱶 �궫�엯

		// �뒪耳�以� �깮�꽦: closingTime�뿉 updateTableRunner.run() 硫붿냼�뱶 �떎�뻾
		scheduler.schedule(updateTableRunner, closingTime);  
		
		System.out.println("updateTableRunner has been scheduled to execute at " + closingTime);
	}
}