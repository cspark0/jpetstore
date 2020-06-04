package com.example.jpetstore.dao.mybatis.mapper;

import java.util.List;

import com.example.jpetstore.domain.Auction;

public interface AuctionMapper {
	
	int updateBiddingPrice(double price);
	
	int insertAuction(Auction auction);
	
	int deleteAuctionbyAunctionId(Auction auction);
	
	List <String> getAuctionByUsername(String username);
}
