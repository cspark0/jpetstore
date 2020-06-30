<%@ include file="IncludeTop.jsp"%>

<table id="main-menu">
  <tr>
    <td><a href='<c:url value="/shop/index.do"/>'><b>
      <font color="black" size="2">&lt;&lt; Main Menu</font></b></a></td>
  </tr>
</table>

<div align="center">
  <table class="n23">
    <tr bgcolor="#CCCCCC">
    
      <td><b>Item ID</b></td>
      <td><b>Product Id</b></td>
      <td><b>Seller</b></td>
    </tr>
    <c:forEach var="item" items="${itemList.pageList}">
      <tr bgcolor="#FFFF88">
        <td><a
          href='<c:url value="/shop/viewItem.do"><c:param name="itemId" value="${item.itemId}"/></c:url>'>
            <c:out value="${item.itemId}" escapeXml="false" />
        </a></td>
        <td><b><a
            href='<c:url value="/shop/viewProduct.do"><c:param name="productId" value="${item.productId}"/></c:url>'>
              <font color="BLACK"><c:out value="${item.productId}" /></font>
          </a></b></td>
       
        
       <td><b> <a href='<c:url value="/shop/viewSellerItem.do">
          <c:param name="username2" value="${item.username2}"/></c:url>'>
         <c:out value="${item.username2}" escapeXml="false" /></a></b></td></tr>
    </c:forEach>
   
    <tr>
      <td><c:if test="${!productList.firstPage}">
          <a href="?page=previous"><font color="white"><B>&lt;&lt;
                Prev</B></font></a>
        </c:if> <c:if test="${!productList.lastPage}">
          <a href="?page=next"><font color="white"><B>Next
                &gt;&gt;</B></font></a>
        </c:if></td>
    </tr>
  </table>
</div>
<%@ include file="IncludeBottom.jsp"%>
