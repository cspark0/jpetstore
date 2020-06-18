<%@ include file="IncludeTop.jsp"%>

<div align="center">
  <p>
    <font size="4"><b>Bidding List</b></font>
  </p>
  <table class="n23">
    <tr bgcolor="#CCCCCC">
      <td><b>Item Name</b></td> 
      <td><b>Item ID</b></td> 
      <td><b>Auction ID</b></td> 
      <td><b>Bidding Date</b></td> 
      <td><b>Bidding Price</b></td> 
      <td><b>Progress</b></td> 
      <td><b>Cancel Bid</b></td>
    </tr>
    <c:forEach var="bid" items="${BiddingList}">
      <tr bgcolor="#FFFF88">
        <td>
          <b><a href='<c:url value="/shop/viewItem.do">
              <c:param name="ItemId" value="${bid.itemId}"/></c:url>'>
              <font color="black"><c:out value="${bid.itemId}" /></font>
            </a></b></td>
        <td>
          <b><c:param name="AuctionId" value="${bid.auctionId}"/>
            </b></td>
        <td><fmt:formatDate value="${bid.biddingDate}"
            pattern="yyyy/MM/dd hh:mm:ss" /></td>
        <td><fmt:formatNumber value="${bid.biddingPrice}"
            pattern="$#,##0.00" /></td>
         <td>
          <a href='<c:url value="/shop/order.do">
            <c:param name="Progress" value="${bid.isSuccessful}"/></c:url>'>
              <img border="0" src="" alt="" />//
          </a></td>    
          
      </tr>
    </c:forEach>
  </table>
</div>

<%@ include file="IncludeBottom.jsp"%>
