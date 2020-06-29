<%@ include file="IncludeTop.jsp"%>

<table>
  <tr>
    <td style="text-align: left; vertical-align: top; width: 20%">
      <table id="main-menu">
        <tr>
          <td><a href='<c:url value="/shop/viewCart.do"/>'><b>
            <font color="black" size="2">&lt;&lt; deposit Cart check out</font></b></a></td>
        </tr>
      </table>
    </td>

    <td style="text-align: center; vertical-align: top">
      <h2>deposit Checkout Summary</h2>
      <table class="n25">
        <tr bgcolor="#CCCCCC">
          <td><b>Item ID</b></td>
          <td><b>Product ID</b></td>
          <td><b>Description</b></td>
          <td><b>In Stock?</b></td>
          <td><b>Quantity</b></td>
          <td><b>List Price</b></td>
          <td><b>Total Cost</b></td>
        </tr>
        <c:forEach var="depositCartItem" items="${depositCart.depositCartItemList.pageList}">
          <tr bgcolor="#FFFF88">
            <td><b> 
              <a href='<c:url value="/shop/viewItem.do">
                <c:param name="itemId" value="${depositCartItem.itemId}"/></c:url>'>
                  <c:out value="${depositCartItem.itemId}" />
              </a></b>
            </td>
            <td><c:out value="${depositCartItem.productId}" /></td>
            <td>
              <c:out value="${depositCartItem.attribute1}" /> 
              <c:out value="${depositCartItem.attribute2}" /> 
              <c:out value="${depositCartItem.attribute3}" /> 
              <c:out value="${depositCartItem.attribute4}" /> 
              <c:out value="${depositCartItem.attribute5}" /> 
              <c:out value="${depositCartItem.product.name}" />
            </td>
            <td align="center"><c:out value="${depositCartItem.inStock}" /></td>
            <td align="center"><c:out value="${depositCartItem.quantity}" /></td>
            <td align="right"><fmt:formatNumber
                value="${depositCartItem.listPrice}" pattern="$#,##0.00" /></td>
            <td align="right"><fmt:formatNumber
                value="${depositCartItem.totalPrice}" pattern="$#,##0.00" /></td>
          </tr>
        </c:forEach>
        <tr bgcolor="#FFFF88">
          <td colspan="7" align="right"><b>Sub Total: <fmt:formatNumber
                value="${depositCart.subTotal}" pattern="$#,##0.00" /></b><br /></td>
        </tr>
      </table>

      <c:if test="${!depositCart.cartItemList.firstPage}">
        <a href="checkout.do?page=previousCart"><font color="green">
          <B>&lt;&lt; Prev</B></font></a>
      </c:if>
      <c:if test="${!depositCart.cartItemList.lastPage}">
        <a href="checkout.do?page=nextCart"><font color="green">
          <B>Next &gt;&gt;</B></font></a>
      </c:if>
      <br> 
      <a href='<c:url value="/shop/newDepositOrder.do"/>'>
        <img border="0" src="../images/button_continue.gif" alt="" /></a>
    </td>
    <td style="text-align: right; vertical-align: top; width: 20%">&nbsp;</td>
  </tr>
</table>

<%@ include file="IncludeBottom.jsp"%>
