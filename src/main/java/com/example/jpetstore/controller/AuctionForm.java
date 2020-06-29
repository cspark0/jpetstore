package com.example.jpetstore.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Auction;
import com.example.jpetstore.domain.DepositCart;
import com.example.jpetstore.domain.DepositCartItem;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Order;

@SuppressWarnings("serial")
public class AuctionForm implements Serializable{
	@Autowired
	private Item auctionItem;
	private Auction auction = new Auction();
	private final Order order = new Order();
	private DepositCart depositCart = new DepositCart();
	private DepositCartItem depositCartItem = new DepositCartItem();
	
	public AuctionForm(Item auctionItem) {
		this.auctionItem =  auctionItem;
		auctionItem.setAuction(1);
	}
	
	public AuctionForm() {
		this.auctionItem = new Item();
		
	}

	public Item getAuctionItem() {
		return auctionItem;
	}
	
	public void setDeposit(Item auctionItem) {
		this.auctionItem.setDeposit(auctionItem.getListPrice()/10);
	}
	

	public Order getOrder() {
		// DO Auto-generated method stub
		return order;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public DepositCart getDepositCart() {
		return depositCart;
	}

	public void setDepositCart(DepositCart depositCart) {
		this.depositCart = depositCart;
	}

	public DepositCartItem getDepositCartItem() {
		return depositCartItem;
	}

	public void setDepositCartItem(DepositCartItem depositCartItem) {
		this.depositCartItem = depositCartItem;
	}

}
