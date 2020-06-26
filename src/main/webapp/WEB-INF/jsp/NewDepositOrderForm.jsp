<%@ include file="IncludeTop.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="targetUrl"><c:url value="/shop/confirmDepositOrder.do" /></c:set>

<div align="center">
<form:form modelAttribute="auctionForm" action="${targetUrl}" method="post">
  <form:errors cssClass="error" /> <br><br>
  
  <table class="n13">
    <tr>
      <td colspan="2">
        <font color="green" size="4"><b>Payment Details</b></font></td>
    
      <td>Card Type:</td>
      <td><form:select path="order.cardType" items="${creditCardTypes}" />
        <form:errors path="order.cardType" /></td>
    </tr>
    <tr>
      <td>Card Number:</td>
      <td><form:input path="order.creditCard" /> 
        <font color="red" size="2">* Use a fake number!</font>
        <form:errors path="order.creditCard" /></td>
    </tr>
    <tr>
      <td>Expiry Date (MM/YYYY):</td>
      <td><form:input path="order.expiryDate" /> 
        <form:errors path="order.expiryDate" /></td>
    </tr>

   
    <tr>
      <td>입찰가격</td>
      <td><form:input path="auction.biddingPrice" />
      <%--   <form:errors path="order.deposit" /></td> --%>
    </tr>
    
   
  </table>
  <p>
    <input type="image" src="../images/button_submit.gif">
  </p>
</form:form>
</div>

<%@ include file="IncludeBottom.jsp"%>
