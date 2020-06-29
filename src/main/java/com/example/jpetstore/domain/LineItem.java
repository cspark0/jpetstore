package com.example.jpetstore.domain;

import java.io.Serializable;

import com.example.jpetstore.dao.AuctionDao;

@SuppressWarnings("serial")
public class LineItem implements Serializable {

  /* Private Fields */

  private int orderId;
  private int lineNumber;
  private int quantity;
  private String itemId;
  private double unitPrice;
  private Item item;

  /* Constructors */

  public LineItem() {}

  public LineItem(int lineNumber, CartItem cartItem) {
    this.lineNumber = lineNumber;
    this.quantity = cartItem.getQuantity();
    this.itemId = cartItem.getItem().getItemId();
    this.unitPrice = cartItem.getItem().getListPrice();
    this.item = cartItem.getItem();
  }

  /* JavaBeans Properties */

  public LineItem(Item item, double biddingPrice) {
	    this.lineNumber = 1;
	    this.quantity = 1;
	    this.itemId = item.getItemId();
	    this.unitPrice = biddingPrice;
	    this.item = item;
}

  public LineItem(int i, DepositCartItem cartItem) {
	// TODO Auto-generated constructor stub
	    this.lineNumber = lineNumber;
	    this.quantity = cartItem.getQuantity();
	    this.itemId = cartItem.getItem().getItemId();
	    this.unitPrice = cartItem.getItem().getListPrice();
	    this.item = cartItem.getItem();
}

  public int getOrderId() { return orderId; }
  public void setOrderId(int orderId) { this.orderId = orderId; }

  public int getLineNumber() { return lineNumber; }
  public void setLineNumber(int lineNumber) { this.lineNumber = lineNumber; }

  public String getItemId() { return itemId; }
  public void setItemId(String itemId) { this.itemId = itemId; }

  public double getUnitPrice() { return unitPrice; }
  public void setUnitPrice(double unitprice) { this.unitPrice = unitprice; }

  public Item getItem() { return item; }
  public void setItem(Item item) {
    this.item = item;
  }

  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getTotalPrice() {
	return this.unitPrice * this.quantity;
  }
}
