<%@ include file="IncludeTop.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<table id="main-menu">
  <tr>
    <td>
      <a href='<c:url value="/shop/viewProduct.do">
        <c:param name="productId" value="${product.productId}"/></c:url>'>
        <b><font color="black" size="2">
          &lt;&lt; <c:out value="${product.name}" /></font></b></a>
    </td>
  </tr>
</table>
<p>
<div align="center">
  <table id="item">
    <tr>
      <td bgcolor="#FFFFFF">
        <c:out value="${product.description}" escapeXml="false" /></td>
    </tr>
    <tr>
      <td width="100%" bgcolor="#CCCCCC"><b><c:out value="${item.itemId}" /></b></td>
    </tr>
    <tr>
      <td><b><font size="4"> 
        <c:out value="${item.attribute1}" />
        <c:out value="${item.attribute2}" /> 
        <c:out value="${item.attribute3}" />
        <c:out value="${item.attribute4}" /> 
        <c:out value="${item.attribute5}" />
        <c:out value="${product.name}" />
        </font></b></td>
    </tr>
    <tr>
      <td><font size="3"><i><c:out value="${product.name}" /></i></font></td>
    </tr>
    <tr>
      <td>
      <c:if test="${item.quantity <= 0}">
        <font color="red" size="2"><i>Back ordered.</i></font>
      </c:if> 
      <c:if test="${item.quantity > 0}">
        <font size="2"><fmt:formatNumber value="${item.quantity}" /> in stock.</font>
      </c:if>
      </td>
    </tr>
    <tr>
      <td><fmt:formatNumber value="${item.listPrice}" pattern="$#,##0.00" /></td>
    </tr>
    <tr>
     <c:if test="${item.isAuction == 0}">
      <td>
     
        <a href='<c:url value="/shop/addItemToCart.do">
          <c:param name="workingItemId" value="${item.itemId}"/></c:url>'>
          add to cart</a>
           
      </td>
       </c:if>
       <c:if test="${item.isAuction == 1}">
        <td>
       
        <a href='<c:url value="/shop/addItemToCart.do">
          <c:param name="workingItemId" value="${item.itemId}"/></c:url>'>
         	 경매 참여(보증금계산)</a>
           </td> </c:if>
    </tr>
  </table>
</div>

<%@ include file="IncludeBottom.jsp"%>
