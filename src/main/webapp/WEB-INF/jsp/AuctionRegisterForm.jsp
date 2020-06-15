<%@ include file="IncludeTop.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="targetUrl"><c:url value="/shop/auctionRegisterSubmitted.do" /></c:set>

<div align="center">
<form:form modelAttribute="auctionForm" action="${targetUrl}" method="post">
  <form:errors cssClass="error" /> <br><br>
  
  <table class="n13">
    <tr>
      <td colspan="2">
        <font color="green" size="4"><b>상품 등록</b></font></td>
    </tr>
    <tr>
      <td>상품 이름:</td>
      <td><form:input path="auctionItem.itemId" />
       <%--  <form:errors path="order.cardType" /></td> --%>
    </tr>
    <tr>
      <td>가격:</td>
      <td><form:input path="auctionItem.listPrice" /> 
       <%--  <form:errors path="order.creditCard" /></td> --%>
    </tr>
    <tr>
    	<td>단위 가격:</td>
    	<td><form:input path="auctionItem.unitCost" /> 
       <%--  <form:errors path="order.creditCard" /></td> --%>
    <tr>
      <td>경매 마감 기한:</td>
      <td><form:input path="auctionItem.deadline" type="date" /> 
      <%--   <form:errors path="order.expiryDate" /></td> --%>
    </tr>
    <tr>
      <td>카테고리 선택:</td>
      <td><form:select path="auctionItem.product.categoryId" items="${categories}" /> 
       <%--  <form:errors path="order.billToFirstName" /></td> --%>
    </tr>
    <tr>
      <td>상세 카테고리 선택:</td>
      <td><form:select path="auctionItem.productId" items="${products}" />
      <%--   <form:errors path="order.billToLastName" /></td> --%>
    </tr>
    <tr>
      <td>부가 설명:</td>
      <td><form:input path="auctionItem.attribute1" />
        <%-- <form:errors path="order.billAddress2" /></td> --%>
    </tr>

  </table>
  <p>
    <input type="image" src="../images/button_submit.gif">
  </p>
</form:form>
</div>

<%@ include file="IncludeBottom.jsp"%>