package com.example.jpetstore.dao.mybatis.mapper;

import java.util.List;

import com.example.jpetstore.domain.Auction;

public interface AuctionMapper {
	
	void updateBiddingPrice(double price);
	
	void insertAuction(Auction auction);
	
	void deleteAuctionbyAunctionId(String auctionId);
	
	List <Auction> getAuctionByUsername(String username);
}
