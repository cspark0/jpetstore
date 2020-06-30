package com.example.jpetstore.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Item implements Serializable {
  /* Private Fields */
  private String itemId;
  private String productId;
  private double listPrice;
  private double unitCost;
  private int supplierId;
  private String status;
  private String attribute1;
  private String attribute2;
  private String attribute3;
  private String attribute4;
  private String attribute5;
  private Product product;
  private int quantity;
  private String timeStatus;
  private int isAuction;
 
  private double deposit;
  private int auctionId;
  
  //�߰�
  private String username2;

  private Date closingTime;


    public String getUsername2() {
    	System.out.println("?" + username2);
    	return username2;
    }
    public void setUsername2(String username) {
    	this.username2 = username;
    }
    
    
	public int getIsAuction() {
    	System.out.println("is Auction?" + isAuction);
	    return isAuction;
	}
	public void setAuction(int isAuction) {
		this.isAuction = isAuction;
	}
	
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	
	
/* JavaBeans Properties */
  public String getItemId() { return itemId; }
  public void setItemId(String itemId) { this.itemId = itemId.trim(); }

  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) { this.quantity = quantity; }

  public Product getProduct() { return product; }
  public void setProduct(Product product) { this.product = product; }

  public String getProductId() { return productId; }
  public void setProductId(String productId) { this.productId = productId; }

  public int getSupplierId() { return supplierId; }
  public void setSupplierId(int supplierId) { this.supplierId = supplierId; }

  public double getListPrice() { return listPrice; }
  public void setListPrice(double listPrice) { this.listPrice = listPrice; }

  public double getUnitCost() { return unitCost; }
  public void setUnitCost(double unitCost) { this.unitCost = unitCost; }

  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }

  public String getAttribute1() { return attribute1; }
  public void setAttribute1(String attribute1) { this.attribute1 = attribute1; }
  public String getAttribute2() { return attribute2; }
  public void setAttribute2(String attribute2) { this.attribute2 = attribute2; }
  public String getAttribute3() { return attribute3; }
  public void setAttribute3(String attribute3) { this.attribute3 = attribute3; }
  public String getAttribute4() { return attribute4; }
  public void setAttribute4(String attribute4) { this.attribute4 = attribute4; }
  public String getAttribute5() { return attribute5; }
  public void setAttribute5(String attribute5) { this.attribute5 = attribute5; }

  public String toString() {
    return "(" + getItemId().trim() + "-" + getProductId().trim() + ")";
  }
public Date getClosingTime() {
	return closingTime;
}
public void setClosingTime(Date closingTime) {
	this.closingTime = closingTime;
}
public String getTimeStatus() {
	return timeStatus;
}
public void setTimeStatus(String timeStatus) {
	this.timeStatus = timeStatus;
}
}