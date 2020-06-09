package com.example.jpetstore.controller.rest;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.jpetstore.domain.Order;
import com.example.jpetstore.service.OrderService;

/**
 * @author Changsup Park
 */
@Controller
public class RestfulOrderController {
	private OrderService orderSvc;

	@Autowired
	public void setPetStoreSvc(OrderService orderService) {
		this.orderSvc = orderService;
	}
	
	@RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody         
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
	
	@RequestMapping(value = "/ordersBy/{username}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody         
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
	
	@RequestMapping(value = "/order/{orderId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Order deleteOrder(@PathVariable("orderId") int orderId, HttpServletResponse response)
			throws IOException {
		System.out.println("/rest/order/{orderId} request with DELETE method accepted: {orderId} = " + orderId);
		Order order = orderSvc.removeOrder(orderId);
		if (order == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		System.out.println("order " + order.getOrderId() + " deleted.");
		return order;	 // convert order to JSON text in response body
	}
}
