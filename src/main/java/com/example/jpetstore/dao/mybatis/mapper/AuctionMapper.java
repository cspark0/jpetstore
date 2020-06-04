package com.example.jpetstore.dao.mybatis.mapper;

import java.util.List;

import com.example.jpetstore.domain.Auction;

public interface AuctionMapper {
	
	List <Auction> getAuctionByUsername(String username);
	
	int updateBiddingPrice(double price);
	
	int insertAuction(Auction auction);
	
	int deleteAuctionbyAunctionId(Auction auction);
}
