package com.example.jpetstore.controller;

import java.io.Serializable;

import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Item;

@SuppressWarnings("serial")
public class AuctionForm implements Serializable{
	private Item auctionItem;
	
	public AuctionForm() {
		this.auctionItem = auctionItem;
		auctionItem.setAuction(1);
	}

	public Item getAuctionItem() {
		return auctionItem;
	}

	public void setDeposit(Item auctionItem) {
		this.auctionItem.setDeposit(auctionItem.getListPrice()/10);
	}

}
