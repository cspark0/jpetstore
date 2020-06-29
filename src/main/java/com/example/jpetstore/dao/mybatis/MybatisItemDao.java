package com.example.jpetstore.dao.mybatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.jpetstore.dao.ItemDao;
import com.example.jpetstore.dao.mybatis.mapper.ItemMapper;
import com.example.jpetstore.domain.Auction;
import com.example.jpetstore.domain.Item;
import com.example.jpetstore.domain.LineItem;
import com.example.jpetstore.domain.Order;

@Repository
public class MybatisItemDao implements ItemDao {
	@Autowired
	private ItemMapper itemMapper;
	
	public void updateQuantity(Order order) throws DataAccessException {
		for (int i = 0; i < order.getLineItems().size(); i++) {
			LineItem lineItem = (LineItem) order.getLineItems().get(i);
			String itemId = lineItem.getItemId();
			Integer increment = new Integer(lineItem.getQuantity());
			Map<String, Object> param = new HashMap<String, Object>(2);
			param.put("itemId", itemId);
			param.put("increment", increment);
			itemMapper.updateInventoryQuantity(param);
		}
	}

	public void insertQuantity(String itemId, int qty) {
		// TODO Auto-generated method stub
		itemMapper.insertQuantity(itemId, qty);
	}
	public void closeEvent(Date curTime) {
		itemMapper.closeEvent(curTime);		
	}

	@Override
	public void insertItem(Item item) {
		// TODO Auto-generated method stub
		itemMapper.insertItem(item);
	}

	@Override
	public List<Item> getItemListIsAuction() {
		// TODO Auto-generated method stub
		return itemMapper.getItemListIsAuction();
	}

	public boolean isItemInStock(String itemId) throws DataAccessException {
		return (itemMapper.getInventoryQuantity(itemId) > 0);
	}

	public List<Item> getItemListByProduct(String productId) 
			throws DataAccessException {
		return itemMapper.getItemListByProduct(productId);
	}

	public Item getItem(String itemId) throws DataAccessException {
		return itemMapper.getItem(itemId);
	}

	@Override
	public int getIsAuction(String itemId) throws DataAccessException {
		// TODO Auto-generated method stub
		System.out.println("MyBatisItemDao:isAuction: "+ itemMapper.getIsAuction(itemId));
		return (itemMapper.getIsAuction(itemId));
	}

	@Override
	public void insertAuctionItem(Item item) {
		// TODO Auto-generated method stub
		itemMapper.insertAuctionItem(item);
	}

	@Override
	public void updateAuctionItem(Item item) {
		// TODO Auto-generated method stub
		itemMapper.updateAuctionItem(item);
	}
	
	public void updateAuctionId(Auction auction) {
		itemMapper.updateAuctionId(auction);
	}

	public List<Item> getItemListByUsername(String username) {
		// TODO Auto-generated method stub
		return itemMapper.getItemListByUsername(username);

	}

	@Override
	public List<Item> getAuctionItemListByUsername(String username) {
		// TODO Auto-generated method stub
		return itemMapper.getAuctionItemListByUsername(username);
	}

	@Override
	public void updateItem(Item item) {
		// TODO Auto-generated method stub
		itemMapper.updateItem(item);
	}

	@Override
	public void deleteItem(String itemId) {
		// TODO Auto-generated method stub
		itemMapper.deleteItem(itemId);
	}

}
