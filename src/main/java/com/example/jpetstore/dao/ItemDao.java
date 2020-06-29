package com.example.jpetstore.dao;

import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.jpetstore.domain.Auction;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.Order;

public interface ItemDao {

  public void updateQuantity(Order order) throws DataAccessException;

  boolean isItemInStock(String itemId) throws DataAccessException;

  List<Item> getItemListByProduct(String productId) throws DataAccessException;

  Item getItem(String itemId) throws DataAccessException;
 
  int getIsAuction(String auctionId) throws DataAccessException;

  public List<Item> getItemListIsAuction();

public void insertAuctionItem(Item item);

public void insertItem(Item item);

public void insertQuantity(String itemId, int qty);

public void updateAuctionItem(Item item);

public List<Item> getItemListByUsername(String username);
public List<Item> getAuctionItemListByUsername(String username);

public void closeEvent(Date curTime);


public void updateAuctionId(Auction auction);

public void updateItem(Item item);

public void deleteItem(String itemId);




}
