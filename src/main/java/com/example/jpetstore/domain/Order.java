package com.example.jpetstore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@SecondaryTable(name="ORDERSTATUS",
	pkJoinColumns=@PrimaryKeyJoinColumn(
		name="orderid", referencedColumnName="orderid"))
public class Order implements Serializable {

  /* Private Fields */
  @Id
  private int orderId;
  private String username;
  
  @Temporal(TemporalType.DATE)
  private Date orderDate;
/*
  private String shipAddress1;
  private String shipAddress2;
  private String shipCity;
  private String shipState;
  private String shipZip;
  private String shipCountry;
*/
  @Embedded
  @AttributeOverrides({
	  @AttributeOverride(name="addr1", column=@Column(name="shipAddress1")),
	  @AttributeOverride(name="addr2", column=@Column(name="shipAddress2")),
	  @AttributeOverride(name="city", column=@Column(name="shipCity")),
	  @AttributeOverride(name="state", column=@Column(name="shipState")),
	  @AttributeOverride(name="zip", column=@Column(name="shipZip")),
	  @AttributeOverride(name="country", column=@Column(name="shipCountry")) 
  })
  private Address shippingAddress;
/*
  private String billAddress1;
  private String billAddress2;
  private String billCity;
  private String billState;
  private String billZip;
  private String billCountry;
*/
  @Embedded
  @AttributeOverrides({
	  @AttributeOverride(name="addr1", column=@Column(name="billAddress1")),
	  @AttributeOverride(name="addr2", column=@Column(name="billAddress2")),
	  @AttributeOverride(name="city", column=@Column(name="billCity")),
	  @AttributeOverride(name="state", column=@Column(name="billState")),
	  @AttributeOverride(name="zip", column=@Column(name="billZip")),
	  @AttributeOverride(name="country", column=@Column(name="billCountry")) 
  })
  private Address billingAddress;
  
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
  
  @Column(name="status", table="ORDERSTATUS") 
  private String status;
  
  @OneToMany(cascade = CascadeType.ALL)
  private List<LineItem> lineItems = new ArrayList<LineItem>();

  /* JavaBeans Properties */

  public int getOrderId() { return orderId; }
  public void setOrderId(int orderId) { this.orderId = orderId; }

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public Date getOrderDate() { return orderDate; }
  public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }

  public Address getShippingAddress() { return shippingAddress; }
  public void setShipAddress1(Address shipAddress) { this.shippingAddress = shipAddress; }

  public Address getBillingAddress() { return billingAddress; }
  public void setBillingAddress(Address billAddress) { this.billingAddress = billAddress; }

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
    shippingAddress = account.getAddress();
    
    billToFirstName = account.getFirstName();
    billToLastName = account.getLastName();
    billingAddress = account.getAddress();
    
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

  public void addLineItem(CartItem cartItem) {
    LineItem lineItem = new LineItem(lineItems.size() + 1, cartItem);
    addLineItem(lineItem);
  }

  public void addLineItem(LineItem lineItem) {
    lineItems.add(lineItem);
  }
}