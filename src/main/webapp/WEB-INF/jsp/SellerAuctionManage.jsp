<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="IncludeTop.jsp"%>

<%-- <table id="main-menu">
  <tr>
    <td><a href='<c:url value="/shop/viewCategory.do">
        <c:param name="categoryId" value="${product.categoryId}"/></c:url>'>
        <b><font color="black" size="2">
          &lt;&lt; <c:out value="${product.name}" /></font></b></a>
    </td>
  </tr>
</table> --%>

<div align="center">
  <table class="n23">
    <tr bgcolor="#CCCCCC">
      <td><b>Item ID</b></td>
      <td></td>
    </tr>
    
    <c:forEach var="item" items="${itemList.pageList}">
      <tr bgcolor="#FFFF88">
        <td><b> 
          <a href='<c:url value="/shop/viewItem.do">
            <c:param name="itemId" value="${item.itemId}"/></c:url>'>
              <c:out value="${item.itemId}" />
          </a></b></td>
         <td>
          <a href='<c:url value="/shop/updateAuntionItem.do">
           <c:param name="itemId2" value="${item.itemId}"/></c:url>'> 
             	상품정보수정</a></td>
        </tr>
    </c:forEach>
     <tr>
      <td>
        <c:if test="${!itemList.firstPage}">
          <a href='<c:url value="/shop/SellerAuctionManage2.do">
            <c:param name="page" value="previous"/></c:url>'>
              <font color="white"><B>&lt;&lt; Prev</B></font></a>
        </c:if> 
        <c:if test="${!itemList.lastPage}">
          <a href='<c:url value="/shop/SellerAuctionManage2.do">
            <c:param name="page" value="next"/></c:url>'>
              <font color="white"><B>Next &gt;&gt;</B></font></a>
        </c:if>
      </td>
    </tr>
  </table>
</div>

<%@ include file="IncludeBottom.jsp"%>