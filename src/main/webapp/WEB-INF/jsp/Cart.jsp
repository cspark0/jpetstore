<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

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
      <h2>Shopping Cart</h2>
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

          <c:if test="${cart.numberOfItems == 0}">
            <tr bgcolor="#FFFF88">
              <td colspan="8"><b>Your cart is empty.</b></td>
            </tr>
          </c:if>

          <c:forEach var="cartItem" items="${cart.cartItemList.pageList}">
            <tr bgcolor="#FFFF88">
              <td><b>
                <a href='<c:url value="/shop/viewItem.do">
                  <c:param name="itemId" value="${cartItem.item.itemId}"/></c:url>'>
                  <c:out value="${cartItem.item.itemId}" />
                </a></b></td>
              <td><c:out value="${cartItem.item.productId}" /></td>
              <td><c:out value="${cartItem.item.attribute1}" /> 
                <c:out value="${cartItem.item.attribute2}" /> 
                <c:out value="${cartItem.item.attribute3}" />
                <c:out value="${cartItem.item.attribute4}" />
                <c:out value="${cartItem.item.attribute5}" />
                <c:out value="${cartItem.item.product.name}" /></td>
              <td style="text-align:center"><c:out value="${cartItem.inStock}" /></td>
              <td style="text-align:center">
                <input type="text" size="3"
                  name='<c:out value="${cartItem.item.itemId}"/>'
                  value='<c:out value="${cartItem.quantity}"/>' /></td>
              <td style="text-align:right"><fmt:formatNumber
                  value="${cartItem.item.listPrice}" pattern="$#,##0.00" /></td>
              <td style="text-align:right"><fmt:formatNumber
                  value="${cartItem.totalPrice}" pattern="$#,##0.00" /></td>
              <td><a href='<c:url value="/shop/removeItemFromCart.do">
                    <c:param name="workingItemId" value="${cartItem.item.itemId}"/></c:url>'>
                    <img border="0" src="../images/button_remove.gif" alt="" /></a>
              </td>
            </tr>
          </c:forEach>
          <tr bgcolor="#FFFF88">
            <td colspan="7" align="right">
              <b>Sub Total: <fmt:formatNumber value="${cart.subTotal}" pattern="$#,##0.00" /></b><br><br> 
              <input type="image" src="../images/button_update_cart.gif" name="update" />
            </td>
            <td>&nbsp;</td>
          </tr>
        </table>
        <div style="text-align:center">
          <c:if test="${!cart.cartItemList.firstPage}">
            <a href='<c:url value="viewCart.do?page=previousCart"/>'>
              <font color="green"><B>&lt;&lt; Prev</B></font></a>
          </c:if>
          <c:if test="${!cart.cartItemList.lastPage}">
            <a href='<c:url value="viewCart.do?page=nextCart"/>'>
              <font color="green"><B>Next &gt;&gt;</B></font></a>
          </c:if>
        </div>
      </form> 
      <c:if test="${cart.numberOfItems > 0}">
        <br />
        <div style="text-align:center">
          <a href='<c:url value="/shop/checkout.do"/>'>
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
