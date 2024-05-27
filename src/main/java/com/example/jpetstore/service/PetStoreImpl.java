package com.example.jpetstore.service;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.jpetstore.dao.AccountDao;
import com.example.jpetstore.dao.OrderDao;
import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Category;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.LineItem;
import com.example.jpetstore.domain.Order;
import com.example.jpetstore.domain.Product;
import com.example.jpetstore.repository.CategoryRepository;
import com.example.jpetstore.repository.ItemRepository;
import com.example.jpetstore.repository.ProductRepository;

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
	@Qualifier("jpaAccountDao")
	private AccountDao accountDao;
	
	@Autowired   
	private CategoryRepository caRepository;
	// private CategoryDao categoryDao;

	@Autowired  
	private ProductRepository prodRepository;
	// private ProductDao productDao;
	
	@Autowired
	private ItemRepository itemRepository;
	// private ItemDao itemDao;
	
	@Autowired	
	@Qualifier("jpaOrderDao")
	private OrderDao orderDao;

	//-------------------------------------------------------------------------
	// Operation methods, implementing the PetStoreFacade interface
	//-------------------------------------------------------------------------

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
        return caRepository.findAll();
	}

	public Category getCategory(String categoryId) {
		return caRepository.getReferenceById(categoryId);			
	}
	
	public Category updateCategory(String id, String name) {
		Category ca = caRepository.getReferenceById(id);
		ca.setName(name);	// ca will be flushed into DB when a transaction commits;
		return ca;
	}
	public Product getProduct(String productId) {
		return prodRepository.getReferenceById(productId);			
	}

	public List<Product> getProductListByCategory(String categoryId) {
		return prodRepository.findByCategoryId(categoryId);
	}

	public List<Product> searchProductList(String keywords) {
		return prodRepository.findByNameIgnoreCaseContaining(keywords);
	}

	public List<Item> getItemListByProduct(String productId) {
		return itemRepository.getByProductId(productId);
	}

	public Item getItem(String itemId) {
		return itemRepository.getReferenceById(itemId);
	}

	public boolean isItemInStock(String itemId) {		
		// return itemDao.isItemInStock(itemId);
		return itemRepository.existsByItemIdAndQuantityGreaterThan(itemId, 0);
		
		/* 또는 아래와 같이 구현 가능
		Optional<Item> result = itemRepository.findById(itemId);
		if (result.isPresent() && result.get().getQuantity() > 0) 
			return true;
		return false;
		*/	
	}

	public void insertOrder(Order order) {
		// itemDao.updateQuantity(order); --> 아래에서 로직 구현 	
		for (int i = 0; i < order.getLineItems().size(); i++) {
			LineItem lineItem = (LineItem) order.getLineItems().get(i);
			String itemId = lineItem.getItemId();
			int increment = lineItem.getQuantity();		
			
			Item item = itemRepository.getReferenceById(itemId);
			item.setQuantity(item.getQuantity() - increment);
			itemRepository.save(item);			
		}
		
		orderDao.insertOrder(order);
	}
	
	public Order getOrder(int orderId) {
		return orderDao.getOrder(orderId);
	}

	public List<Order> getOrdersByUsername(String username) {
		return orderDao.getOrdersByUsername(username);
	}

}