package com.example.jpetstore.service;

import java.util.List;

import com.example.jpetstore.domain.Order;

/**
 * Separate OrderService interface, implemented by OrderServiceImpl. * 
 *
 * <p>Mainly targeted at usage as remote service interface,
 * just exposing the <code>getOrder</code> method.
 *
 * @author Juergen Hoeller
 * @since 26.12.2003
 * @see PetStoreFacade
 * @see PetStoreImpl
 * @see OrderServiceImpl
 */
//@WebService(name = "OrderService") 
public interface OrderService {

	Order getOrder(int orderId);
	public List<Order> getOrdersByUsername(String username);

}
