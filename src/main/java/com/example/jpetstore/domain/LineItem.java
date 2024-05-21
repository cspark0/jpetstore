package com.example.jpetstore.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="LINEITEM") // 생략 가능
@IdClass(LineItemPK.class)		// composite key class
public class LineItem implements Serializable {

	/* Private Fields */
	@Id
	private int orderId;
	
	@Id 
	@Column(name="linenum")
	private int lineNumber;
	
	private int quantity;
	private double unitPrice;
	private String itemId;

	@ManyToOne
	@JoinColumn(name="itemId", insertable=false, updatable=false)
	private Item item;

	/* Constructors */
	public LineItem() {
	}

	public LineItem(int lineNumber, CartItem cartItem) {
		this.lineNumber = lineNumber;
		this.quantity = cartItem.getQuantity();
		this.itemId = cartItem.getItem().getItemId();
		this.unitPrice = cartItem.getItem().getListPrice();
		this.item = cartItem.getItem();
	}

	/* JavaBeans Properties */

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitprice) {
		this.unitPrice = unitprice;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return this.unitPrice * this.quantity;
	}
}
