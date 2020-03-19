package com.example.jpetstore.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jpetstore.domain.Order;

/**
 * @author Chang-Sup Park
 */
@Component
@WebService(serviceName="OrderService") 
public class OrderServiceEndpoint {
	@Autowired
	OrderService orderService;		// inject orderSeviceImpl
	
	@WebMethod
	public Order getOrder(int orderId) {
		return orderService.getOrder(orderId);
	}

	@WebMethod
	public List<Order> getOrdersByUsername(String username) {
		return orderService.getOrdersByUsername(username);
	}
}
