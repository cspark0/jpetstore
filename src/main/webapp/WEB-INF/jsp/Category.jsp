<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ include file="IncludeTop.jsp"%>

<table id="main-menu">
  <tr>
    <td><a href='<c:url value="/shop/index.do"/>'><b>
       <font color="black" size="2">&lt;&lt; Main Menu</font></b></a>
    </td>
  </tr>
</table>

<div align="center">
  <h2><c:out value="${category.name}" /></h2>
  <table class="n23">
    <tr bgcolor="#CCCCCC">
      <td><b>Product ID</b></td>
      <td><b>Name</b></td>
    </tr>
    <c:forEach var="product" items="${productList.pageList}">
      <tr bgcolor="#FFFF88">
        <td><b><a href='<c:url value="/shop/viewProduct.do">
          <c:param name="productId" value="${product.productId}"/></c:url>'>
            <font color="black"><c:out value="${product.productId}" /></font>
          </a></b></td>
        <td><c:out value="${product.name}" /></td>
      </tr>
    </c:forEach>
    <tr>
      <td>
        <c:if test="${!productList.firstPage}">
          <a href='<c:url value="/shop/viewCategory2.do">
            <c:param name="page" value="previous"/></c:url>'>
              <font color="white"><B>&lt;&lt; Prev</B></font></a>
        </c:if> 
        <c:if test="${!productList.lastPage}">
          <a href='<c:url value="/shop/viewCategory2.do">
            <c:param name="page" value="next"/></c:url>'>
              <font color="white"><B>Next &gt;&gt;</B></font></a>
        </c:if>
      </td>
    </tr>
  </table>
</div>

<%@ include file="IncludeBottom.jsp"%>
