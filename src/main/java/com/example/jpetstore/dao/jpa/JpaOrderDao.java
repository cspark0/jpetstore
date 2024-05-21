package com.example.jpetstore.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.example.jpetstore.dao.OrderDao;
import com.example.jpetstore.dao.SequenceDao;
import com.example.jpetstore.domain.LineItem;
import com.example.jpetstore.domain.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * @author Changsup Park
 */
@Repository
public class JpaOrderDao implements OrderDao {
	
	@PersistenceContext
    private EntityManager em;

	@Autowired
	private SequenceDao sequenceDao;

	public List<Order> getOrdersByUsername(String username) 
			throws DataAccessException {
		TypedQuery<Order> query = em.createQuery(
                "select order from Order order " +
                "where order.username=?1",
                Order.class);
		query.setParameter(1, username);	
        return query.getResultList();
	}
	
	public Order getOrder(int orderId) throws DataAccessException {
	/*
		Order order = orderMapper.getOrder(orderId);
		if (order != null) {
			order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));
		}
	    return order;
	*/
        return em.find(Order.class, orderId); 
	}
	
	public void insertOrder(Order order) throws DataAccessException {
	/*
    	orderMapper.insertOrder(order);
    	orderMapper.insertOrderStatus(order);
    	for (int i = 0; i < order.getLineItems().size(); i++) {
    		LineItem lineItem = (LineItem) order.getLineItems().get(i);
    		lineItem.setOrderId(order.getOrderId());
    	}
    */
		int newOrderId = sequenceDao.getNextId("ordernum");
    	order.setOrderId(newOrderId);
    	for (int i = 0; i < order.getLineItems().size(); i++) {
    		LineItem lineItem = (LineItem) order.getLineItems().get(i);
    		lineItem.setOrderId(newOrderId);    		
    	}
        em.persist(order);
	}
}