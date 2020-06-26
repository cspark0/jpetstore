package com.example.jpetstore.controller;

import java.io.Serializable;

import com.example.jpetstore.domain.Account;
import com.example.jpetstore.domain.Item;

@SuppressWarnings("serial")
public class ItemForm implements Serializable{
	private Item item;
	
	
	public ItemForm(Item item) {
		this.item =  item;
	}
	
	public ItemForm() {
		this.item = new Item();
		
	}

	public Item getItem() {
		return item;
	}


}