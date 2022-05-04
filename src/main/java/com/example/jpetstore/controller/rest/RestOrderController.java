package com.example.jpetstore.controller.rest;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpetstore.domain.Order;
import com.example.jpetstore.service.OrderService;

/**
 * @author Changsup Park
 */
@RestController
@RequestMapping("/rest")
public class RestOrderController {
	private OrderService orderSvc;

	@Autowired
	public void setPetStoreSvc(OrderService orderService) {
		this.orderSvc = orderService;
	}
	
	// @ResponseBody         
	@GetMapping(value = "/order/{orderId}", produces = "application/json")
	public Order getOrder(@PathVariable("orderId") int orderId, HttpServletResponse response)
			throws IOException {
		System.out.println("/rest/order/{orderId} request accepted: {orderId} = " + orderId);
		Order order = orderSvc.getOrder(orderId);
		if (order == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return order;   // convert order to JSON text in response body
	}
	
	// @ResponseBody         
	@GetMapping(value = "/ordersBy/{username}", produces = "application/json")
	public List<Order> getOrdersByUsername(@PathVariable("username") String username, HttpServletResponse response)
			throws IOException {
		System.out.println("/rest/order/{username} request accepted: {username} = " + username);
		List<Order> orderList = orderSvc.getOrdersByUsername(username);
		if (orderList == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return orderList;  // convert list of orders to JSON text in response body
	}
	
	// @ResponseBody
	@DeleteMapping("/order/{orderId}")
	@ResponseStatus(HttpStatus.OK)
	public Order deleteOrder(@PathVariable("orderId") int orderId, HttpServletResponse response)
			throws IOException {
		System.out.println("/rest/order/{orderId} request with DELETE method accepted: {orderId} = " + orderId);
		Order order = orderSvc.removeOrder(orderId);
		if (order == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		System.out.println("order " + order.getOrderId() + " deleted.");
		return order;	 	// convert order to JSON text in response body
	}
}
