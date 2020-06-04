package com.example.jpetstore.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.jpetstore.domain.Auction;
import com.example.jpetstore.domain.Order;

public interface AuctionDao {
	/*
	 * 마이페이지에 나의 입찰내역(getAuctionListByUserId)
	 * 옥션에서 아이템db으로 가격 갱신
	 * insert, update, (delete), 
	 *  
	 *  
	 *  */
	 List<Order> getAuctionByUsername(String username) throws DataAccessException;
	 int updateBiddingPrice(double price)throws DataAccessException;
	 int insertAuction(Auction auction)throws DataAccessException;
	 int deleteAuctionbyAuctionId(Auction auction)throws DataAccessException; // 경매후에 모두 삭제
}
