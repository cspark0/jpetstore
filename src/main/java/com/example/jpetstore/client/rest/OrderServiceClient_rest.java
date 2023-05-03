package com.example.jpetstore.client.rest;

import java.util.Iterator;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.jpetstore.domain.LineItem;
import com.example.jpetstore.domain.Order;

public class OrderServiceClient_rest {

	private static RestTemplate restTemplate = new RestTemplate();
	private static String host = "localhost";
	private static String port = "8088";
	private static String orderSvcUrl = "http://" + host + ":" + port + "/rest";				

	public static void main(String[] args) {		
		getOrderInfo("j2ee");	
		getOrderInfo(1002);
		deleteOrderInfo(1007);
	}

	private static void getOrderInfo(int orderId) {
		System.out.println("Calling getOrderInfo() with order ID " + orderId);
		
		Order order = null;
		try {
			order = restTemplate.getForObject(
					orderSvcUrl + "/order/{orderId}", Order.class, orderId);
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {	// 404 Not Found	
				System.out.println("Order with ID " + orderId + " not found");
			}
		} catch (RestClientException e) {
			e.printStackTrace();
			return;
		}		
		if (order != null) printOrder(order);		
		
		System.out.println();
	}

	private static void getOrderInfo(String username) {
		System.out.println("Calling getOrderInfo() with user's name " + username);
		
		Order[] orders = null;
		try {
			orders = restTemplate.getForObject(
					orderSvcUrl + "/ordersBy/{username}", Order[].class, username);
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {	// 404 Not Found	
				System.out.println("Orders by " + username + " not found");
			}
		} catch (RestClientException e) {
			e.printStackTrace();
			return;
		} 	
		if (orders != null) printOrders(orders, username);
		
		System.out.println();
	}

	private static void deleteOrderInfo(int orderId) {
		System.out.println("Calling deleteOrder() with order ID " + orderId);
		
		try {
			restTemplate.delete(orderSvcUrl + "/order/{orderId}", orderId);
		} catch (HttpStatusCodeException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {	// 404 Not Found	
				System.out.println("Order with ID " + orderId + " not found");
			}
			return;
		} catch (RestClientException e) {
			e.printStackTrace();
			return;
		}			
		
		System.out.println("order " + orderId + " has been deleted.");
	}
	
	private static void printOrder(Order order) {
		System.out.println("Got order with order ID " + order.getOrderId() +
				" and order date " + order.getOrderDate());
		System.out.println("User name: " + order.getUsername());
		System.out.println("Shipping address: " + order.getShipAddress1() + ", " + order.getShipAddress2() + ", " + order.getShipCity());
		for (Iterator<LineItem> lineItems = order.getLineItems().iterator(); lineItems.hasNext();) {
			LineItem lineItem = (LineItem) lineItems.next();
			System.out.println("LineItem " + lineItem.getLineNumber() + ": " + lineItem.getQuantity() +
					" piece(s) of item " + lineItem.getItemId());
		}
		System.out.println("Total prices: " + order.getTotalPrice());
	}
	
	private static void printOrders(Order[] orders, String username) {		
		System.out.println("Number of orders by " + username + ": "+ orders.length);
		for (int i = 0; i < orders.length; i++) {
			Order order = orders[i];
			System.out.println("-------------------------------");
			System.out.println("Order ID: " + order.getOrderId());
			System.out.println("Order Date: " + order.getOrderDate());
			System.out.println("Shipping address: " + order.getShipAddress1() + ", " + order.getShipAddress2() + ", " + order.getShipCity());
			System.out.println("Total prices: " + order.getTotalPrice());
		}
	}
}
