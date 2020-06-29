<%@ include file="IncludeTop.jsp"%>

<table>
  <tr>
   <td style="text-align: left; vertical-align: top; width: 20%">
      <table id="main-menu">
        <tr>
          <td><a href='<c:url value="/shop/biddingList.do"/>'><b>
            <font color="black" size="2">&lt;&lt; Bidding List</font></b></a></td>
        </tr>
      </table>
    </td>

    <td style="text-align: center; vertical-align: top">
      <h2>Successful Bid Checkout</h2>
      <table class="n25">
        <tr bgcolor="#CCCCCC">
           <td><b>Item ID</b></td> 
           <td><b>Auction ID</b></td> 
           <td><b>Bidding Price</b></td>
        </tr>

          <tr bgcolor="#FFFF88">
            <td><b> 
              <a href='<c:url value="/shop/viewItem.do">
                <c:param name="itemId" value="${auction.itemId}"/></c:url>'>
                  <c:out value="${auction.itemId}" />
              </a></b>
            </td>     
            <td><b><c:out value="${auction.auctionId}"/>
            </b></td>
                  
            <td align="right"><fmt:formatNumber
                value="${auction.biddingPrice}" pattern="$#,##0.00" /></td>
          </tr>
       <%--  </c:forEach> --%>
      </table>
      
      <br> 
      <a href='<c:url value="/shop/newOrder2.do?totalPrice=biddingPrice">
      		<c:param name="auctionId" value="${auction.auctionId}"/>
      		<c:param name="itemId" value="${auction.itemId}"/>
      		<c:param name="totalPrice" value="${auction.biddingPrice}"/></c:url>'>
        <img border="0" src="../images/button_continue.gif" alt="" /></a>
    </td>
    <td style="text-align: right; vertical-align: top; width: 20%">&nbsp;</td>
  </tr>
</table>

<%@ include file="IncludeBottom.jsp"%>
