package com.example.jpetstore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.dao.DataAccessException;

@SuppressWarnings("serial")
public class Order implements Serializable {

  /* Private Fields */

  private int orderId;
  private String username;
  private Date orderDate;
  private String shipAddress1;
  private String shipAddress2;
  private String shipCity;
  private String shipState;
  private String shipZip;
  private String shipCountry;
  private String billAddress1;
  private String billAddress2;
  private String billCity;
  private String billState;
  private String billZip;
  private String billCountry;
  private String courier;
  private double totalPrice;
  private String billToFirstName;
  private String billToLastName;
  private String shipToFirstName;
  private String shipToLastName;
  private String creditCard;
  private String expiryDate;
  private String cardType;
  private String locale;
  private String status;
  private List<LineItem> lineItems = new ArrayList<LineItem>();
  private double deposit;
  
  
  /* JavaBeans Properties */

  public int getOrderId() { return orderId; }
  public void setOrderId(int orderId) { this.orderId = orderId; }

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public Date getOrderDate() { return orderDate; }
  public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

  public String getShipAddress1() { return shipAddress1; }
  public void setShipAddress1(String shipAddress1) { this.shipAddress1 = shipAddress1; }

  public String getShipAddress2() { return shipAddress2; }
  public void setShipAddress2(String shipAddress2) { this.shipAddress2 = shipAddress2; }

  public String getShipCity() { return shipCity; }
  public void setShipCity(String shipCity) { this.shipCity = shipCity; }

  public String getShipState() { return shipState; }
  public void setShipState(String shipState) { this.shipState = shipState; }

  public String getShipZip() { return shipZip; }
  public void setShipZip(String shipZip) { this.shipZip = shipZip; }

  public String getShipCountry() { return shipCountry; }
  public void setShipCountry(String shipCountry) { this.shipCountry = shipCountry; }

  public String getBillAddress1() { return billAddress1; }
  public void setBillAddress1(String billAddress1) { this.billAddress1 = billAddress1; }

  public String getBillAddress2() { return billAddress2; }
  public void setBillAddress2(String billAddress2) { this.billAddress2 = billAddress2; }

  public String getBillCity() { return billCity; }
  public void setBillCity(String billCity) { this.billCity = billCity; }

  public String getBillState() { return billState; }
  public void setBillState(String billState) { this.billState = billState; }

  public String getBillZip() { return billZip; }
  public void setBillZip(String billZip) { this.billZip = billZip; }

  public String getBillCountry() { return billCountry; }
  public void setBillCountry(String billCountry) { this.billCountry = billCountry; }

  public String getCourier() { return courier; }
  public void setCourier(String courier) { this.courier = courier; }

  public double getTotalPrice() { return totalPrice; }
  public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

  public String getBillToFirstName() { return billToFirstName; }
  public void setBillToFirstName(String billToFirstName) { this.billToFirstName = billToFirstName; }

  public String getBillToLastName() { return billToLastName; }
  public void setBillToLastName(String billToLastName) { this.billToLastName = billToLastName; }

  public String getShipToFirstName() { return shipToFirstName; }
  public void setShipToFirstName(String shipFoFirstName) { this.shipToFirstName = shipFoFirstName; }

  public String getShipToLastName() { return shipToLastName; }
  public void setShipToLastName(String shipToLastName) { this.shipToLastName = shipToLastName; }

  public String getCreditCard() { return creditCard; }
  public void setCreditCard(String creditCard) { this.creditCard = creditCard; }

  public String getExpiryDate() { return expiryDate; }
  public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }

  public String getCardType() { return cardType; }
  public void setCardType(String cardType) { this.cardType = cardType; }

  public String getLocale() { return locale; }
  public void setLocale(String locale) { this.locale = locale; }

  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }

  public void setLineItems(List<LineItem> lineItems) { this.lineItems = lineItems; }
  public List<LineItem> getLineItems() { return lineItems; }

  /* Public Methods */

  public void initOrder(Account account, Cart cart) {
    username = account.getUsername();
    orderDate = new Date();

    shipToFirstName = account.getFirstName();
    shipToLastName = account.getLastName();
    shipAddress1 = account.getAddress1();
    shipAddress2 = account.getAddress2();
    shipCity = account.getCity();
    shipState = account.getState();
    shipZip = account.getZip();
    shipCountry = account.getCountry();

    billToFirstName = account.getFirstName();
    billToLastName = account.getLastName();
    billAddress1 = account.getAddress1();
    billAddress2 = account.getAddress2();
    billCity = account.getCity();
    billState = account.getState();
    billZip = account.getZip();
    billCountry = account.getCountry();

    totalPrice = cart.getSubTotal();

    creditCard = "999 9999 9999 9999";
    expiryDate = "12/03";
    cardType = "Visa";
    courier = "UPS";
    locale = "CA";
    status = "P";

    Iterator<CartItem> i = cart.getAllCartItems();
    while (i.hasNext()) {
      CartItem cartItem = (CartItem) i.next();
      addLineItem(cartItem);
    }
  }
  
  public void initOrder(Account account, Item item, double biddingPrice) {
	    username = account.getUsername();
	    orderDate = new Date();

	    shipToFirstName = account.getFirstName();
	    shipToLastName = account.getLastName();
	    shipAddress1 = account.getAddress1();
	    shipAddress2 = account.getAddress2();
	    shipCity = account.getCity();
	    shipState = account.getState();
	    shipZip = account.getZip();
	    shipCountry = account.getCountry();

	    billToFirstName = account.getFirstName();
	    billToLastName = account.getLastName();
	    billAddress1 = account.getAddress1();
	    billAddress2 = account.getAddress2();
	    billCity = account.getCity();
	    billState = account.getState();
	    billZip = account.getZip();
	    billCountry = account.getCountry();

	    totalPrice = biddingPrice;

	    creditCard = "999 9999 9999 9999";
	    expiryDate = "12/03";
	    cardType = "Visa";
	    courier = "UPS";
	    locale = "CA";
	    status = "P";
	    
		addLineItem2(item, biddingPrice);
	  }

  public void addLineItem2(Item item, double biddingPrice) {
		LineItem lineItem = new LineItem(item, biddingPrice);
	  	addLineItem(lineItem);
  }
  
  public void addLineItem(CartItem cartItem) {
	  if(cartItem != null) {
		LineItem lineItem = new LineItem(lineItems.size() + 1, cartItem);
	  	addLineItem(lineItem);} 
  }

  public void addLineItem(LineItem lineItem) {
    lineItems.add(lineItem);
  }

  public void initOrder(Account account, DepositCart cart) {
	// TODO Auto-generated method stub
	  username = account.getUsername();
	    orderDate = new Date();

	    shipToFirstName = account.getFirstName();
	    shipToLastName = account.getLastName();
	    shipAddress1 = account.getAddress1();
	    shipAddress2 = account.getAddress2();
	    shipCity = account.getCity();
	    shipState = account.getState();
	    shipZip = account.getZip();
	    shipCountry = account.getCountry();

	    billToFirstName = account.getFirstName();
	    billToLastName = account.getLastName();
	    billAddress1 = account.getAddress1();
	    billAddress2 = account.getAddress2();
	    billCity = account.getCity();
	    billState = account.getState();
	    billZip = account.getZip();
	    billCountry = account.getCountry();

	    totalPrice = cart.getSubTotal();

	    creditCard = "999 9999 9999 9999";
	    expiryDate = "12/03";
	    cardType = "Visa";
	    courier = "UPS";
	    locale = "CA";
	    status = "P";

	    Iterator<DepositCartItem> i = cart.getDepositAllCartItems();
	    while (i.hasNext()) {
	      DepositCartItem cartItem = (DepositCartItem) i.next();
	      addLineItem(cartItem);
	    }

	
}
private void addLineItem(DepositCartItem cartItem) {
	// TODO Auto-generated method stub
	 LineItem lineItem = new LineItem(lineItems.size() + 1, cartItem);
	    addLineItem(lineItem);
	
}
public double getDeposit() {
	return deposit;
}
public void setDeposit(double deposit) {
	this.deposit = deposit;
}

}