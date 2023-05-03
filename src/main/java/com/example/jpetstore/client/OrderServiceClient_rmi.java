package com.example.jpetstore.client;

import java.util.Iterator;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StopWatch;
import com.example.jpetstore.domain.LineItem;
import com.example.jpetstore.domain.Order;
import com.example.jpetstore.service.OrderService;

/**
 * Demo client class for remote OrderServices, to be invoked as standalone
 * program from the command line, e.g. via "client.bat" or "run.xml".
 *
 * <p>You need to specify an order ID and optionally a number of calls,
 * e.g. for order ID 1000: 'client 1000' for a single call per service or
 * 'client 1000 10' for 10 calls each".
 *
 * <p>Reads in the application context from a "clientContext.xml" file in
 * the VM execution directory, calling all OrderService proxies defined in it.
 * See that file for details.
 *
 * @author Juergen Hoeller
 * @since 26.12.2003
 * @see org.springframework.samples.jpetstore.service.OrderService
 */
public class OrderServiceClient_rmi {

	private static final String CLIENT_CONTEXT_CONFIG_LOCATION = "client/clientContext.xml";
	private static final String orderServiceBeanName = "rmiOrderService";
	
	private final ListableBeanFactory beanFactory;
	public OrderServiceClient_rmi(ListableBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public void invokeOrderServices(int orderId) {
		OrderService orderService = (OrderService) beanFactory.getBean(orderServiceBeanName);
		StopWatch stopWatch = new StopWatch(orderServiceBeanName + " call");
		System.out.println("Calling rmiOrderService with order ID " + orderId);
		stopWatch.start(orderServiceBeanName);
		Order order = orderService.getOrder(orderId);
		stopWatch.stop();
		if (order != null) {
			printOrder(order);
		}
		else {
			System.out.println("Order with ID " + orderId + " not found");
		}
		System.out.println();
		System.out.println(stopWatch.prettyPrint());
	}

	protected void printOrder(Order order) {
		System.out.println("Got order with order ID " + order.getOrderId() +
				" and order date " + order.getOrderDate());
		System.out.println("Shipping address is: " + order.getShipAddress1());
		for (Iterator<LineItem> lineItems = order.getLineItems().iterator(); lineItems.hasNext();) {
			LineItem lineItem = (LineItem) lineItems.next();
			System.out.println("LineItem " + lineItem.getLineNumber() + ": " + lineItem.getQuantity() +
					" piece(s) of item " + lineItem.getItemId());
		}
	}

	public static void main(String[] args) {
		if (args.length == 0 || "".equals(args[0])) {
			System.out.println(
				"You need to specify an order ID, e.g. for order ID 1000: 'client 1000'");
		}
		else {
			int orderId = Integer.parseInt(args[0]);
			ListableBeanFactory beanFactory = (ListableBeanFactory) new ClassPathXmlApplicationContext(CLIENT_CONTEXT_CONFIG_LOCATION);
			OrderServiceClient_rmi client = new OrderServiceClient_rmi(beanFactory);
			client.invokeOrderServices(orderId); 
		}
	}
}