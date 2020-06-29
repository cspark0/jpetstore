package com.example.jpetstore.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Auction implements Serializable {
	
	private int auctionId;
	private String itemId;
	private String username;
	private double biddingPrice;
	//private Date biddingDate;
	private boolean isSuccessful;
	private int maxAuctionId;
	
	
	public int getMaxAuctionId() {
		return maxAuctionId;
	}
	public void setMaxAuctionId(int maxAuctionId) {
		this.maxAuctionId = maxAuctionId;
	}
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getBiddingPrice() {
		return biddingPrice;
	}
	public void setBiddingPrice(double biddingPrice) {
		this.biddingPrice = biddingPrice;
	}
	/*public Date getBiddingDate() {
		return biddingDate;
	}
	public void setBiddingDate(Date biddingDate) {
		this.biddingDate = biddingDate;
	}*/
	public boolean isSuccessful() {
		return isSuccessful;
	}
	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
	
	

}