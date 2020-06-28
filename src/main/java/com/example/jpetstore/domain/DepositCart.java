package com.example.jpetstore.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.support.PagedListHolder;

@SuppressWarnings("serial")
public class DepositCart implements Serializable {

  /* Private Fields */

  private final Map<String, DepositCartItem> itemMap = Collections.synchronizedMap(new HashMap<String, DepositCartItem>());
	
  private final PagedListHolder<DepositCartItem> itemList = new PagedListHolder<DepositCartItem>();

  /* JavaBeans Properties */

	public DepositCart() {
		this.itemList.setPageSize(4);
	}

 public Iterator<DepositCartItem> getDepositAllCartItems() { return itemList.getSource().iterator(); }
  public PagedListHolder<DepositCartItem> getDepositCartItemList() { return itemList; }
  public int getNumberOfItems() { return itemList.getSource().size(); }

  /* Public Methods */

  public boolean containsItemId(String itemId) {
    return itemMap.containsKey(itemId);
  }

  public void addItem(Item item, boolean isInStock) {
    DepositCartItem cartItem = itemMap.get(item.getItemId());
    if (cartItem == null) {
      cartItem = new DepositCartItem();
      cartItem.setItem(item);
      cartItem.setQuantity(0);
      cartItem.setInStock(isInStock);
      itemMap.put(item.getItemId(), cartItem);
      itemList.getSource().add(cartItem);
    }
    cartItem.incrementQuantity();
  }


  public Item removeItemById(String itemId) {
    DepositCartItem cartItem = itemMap.remove(itemId);
    if (cartItem == null) {
      return null;
    }
		else {
      itemList.getSource().remove(cartItem);
      return cartItem.getItem();
    }
  }

  public void incrementQuantityByItemId(String itemId) {
	  DepositCartItem cartItem = itemMap.get(itemId);
    cartItem.incrementQuantity();
  }

  public void setQuantityByItemId(String itemId, int quantity) {
	  DepositCartItem cartItem = itemMap.get(itemId);
    cartItem.setQuantity(quantity);
  }

  public double getSubTotal() {
    double subTotal = 0;
    Iterator<DepositCartItem> items = getDepositAllCartItems();
    while (items.hasNext()) {
      DepositCartItem cartItem = (DepositCartItem) items.next();
      Item item = cartItem.getItem();
      double listPrice = item.getListPrice();
      int quantity = cartItem.getQuantity();
      subTotal += listPrice * quantity;
    }
    return subTotal;
  }

}
