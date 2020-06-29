<%@ include file="IncludeTop.jsp"%>
 
<table>
  <tr style="vertical-align:top">
    <td style="text-align:left;width:20%;">
      <table id="main-menu">
        <tr>
          <td><a href='<c:url value="/shop/index.do"/>'>
            <b><font color="black" size="2">&lt;&lt; Main Menu</font></b></a></td>
        </tr>
      </table>
    </td>
    <td style="text-align:center">
      <h2>deposit Cart</h2>
      <form action='<c:url value="/shop/updateCartQuantities.do"/>' method="post">
        <table id="cart">
          <tr bgcolor="#cccccc">
            <td><b>Item ID</b></td>
            <td><b>Product ID</b></td>
            <td><b>Description</b></td>
            <td><b>In Stock?</b></td>
            <td><b>Quantity</b></td>
            <td><b>List Price</b></td>
            <td><b>Total Cost</b></td>
            <td>&nbsp;</td>
          </tr>

          <c:if test="${depositCart.numberOfItems == 0}">
            <tr bgcolor="#FFFF88">
              <td colspan="8"><b>Your cart is empty.</b></td>
            </tr>
          </c:if>

          <c:forEach var="depositCartItem" items="${depositCart.depositCartItemList.pageList}">
            <tr bgcolor="#FFFF88">
              <td><b>
                <a href='<c:url value="/shop/viewItem.do">
                  <c:param name="itemId" value="${depositCartItem.item.itemId}"/></c:url>'>
                  <c:out value="${depositCartItem.item.itemId}" />
                </a></b></td>
              <td><c:out value="${depositCartItem.item.productId}" /></td>
              <td><c:out value="${depositCartItem.item.attribute1}" /> 
                <c:out value="${depositCartItem.item.attribute2}" /> 
                <c:out value="${depositCartItem.item.attribute3}" />
                <c:out value="${depositCartItem.item.attribute4}" />
                <c:out value="${depositCartItem.item.attribute5}" />
                <c:out value="${depositCartItem.item.product.name}" /></td>
              <td style="text-align:center"><c:out value="${depositCartItem.inStock}" /></td>
              <td style="text-align:center">
                <input type="text" size="3"
                  name='<c:out value="${depositCartItem.item.itemId}"/>'
                  value='<c:out value="${depositCartItem.quantity}"/>' /></td>
              <td style="text-align:right"><fmt:formatNumber
                  value="${depositCartItem.item.listPrice/10}" pattern="$#,##0.00" /></td>
              <td style="text-align:right"><fmt:formatNumber
                  value="${depositCartItem.totalPrice/10}" pattern="$#,##0.00" /></td>
              <td><a href='<c:url value="/shop/removeDepositItemFromCart.do">
                    <c:param name="workingItemId" value="${depositCartItem.item.itemId}"/></c:url>'>
                    <img border="0" src="../images/button_remove.gif" alt="" /></a>
              </td>
            </tr>
          </c:forEach>
          <tr bgcolor="#FFFF88">
            <td colspan="7" align="right">
              <b>Sub Total: <fmt:formatNumber value="${depositCart.subTotal/10}" pattern="$#,##0.00" /></b><br><br> 
             <!--  <input type="image" src="../images/button_update_cart.gif" name="update" />
           -->  </td>
            <td>&nbsp;</td>
          </tr>
        </table>
        <div style="text-align:center">
          <c:if test="${!depositCart.depositCartItemList.firstPage}">
            <a href='<c:url value="viewDepositCart.do?page=previousCart"/>'>
              <font color="green"><B>&lt;&lt; Prev</B></font></a>
          </c:if>
          <c:if test="${!depositCart.depositCartItemList.lastPage}">
            <a href='<c:url value="viewDepositCart.do?page=nextCart"/>'>
              <font color="green"><B>Next &gt;&gt;</B></font></a>
          </c:if>
        </div>
      </form> 
      <c:if test="${depositCart.numberOfItems > 0}">
        <br />
        <div style="text-align:center">
         <%--  <a href='<c:url value="/shop/depositCheckout.do"/>'> --%>
           <a href='<c:url value="/shop/newDepositOrder.do"/>'>
            <img border="0" src="../images/button_checkout.gif" alt="" /></a>
        </div>
      </c:if>
    </td>
    <td style="text-align:right;width:20%;">
      <c:if test="${!empty userSession.account.username}">
        <c:if test="${userSession.account.listOption}">
          <%@ include file="IncludeMyList.jsp"%>
        </c:if>
      </c:if>
    </td>
  </tr>
</table>

<%@ include file="IncludeBanner.jsp"%>

<%@ include file="IncludeBottom.jsp"%>
