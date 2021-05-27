<%@ include file="IncludeTop.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="targetUrl"><c:url value="/shop/newOrderSubmitted.do" /></c:set>

<div align="center">
<form:form modelAttribute="orderForm" action="${targetUrl}" method="post">
  <form:errors cssClass="error" /> <br><br>
  
  <table class="n13">
    <tr>
      <td colspan="2">
        <font color="green" size="4"><b>Payment Details</b></font></td>
    </tr>
    <tr>
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
      <td colspan="2">
        <font color="green" size="4"><b>Billing Address</b></font></td>
    </tr>
    <tr>
      <td>First name:</td>
      <td><form:input path="order.billToFirstName" /> 
        <form:errors path="order.billToFirstName" /></td>
    </tr>
    <tr>
      <td>Last name:</td>
      <td><form:input path="order.billToLastName" />
        <form:errors path="order.billToLastName" /></td>
    </tr>
    <tr>
      <td>Address 1:</td>
      <td><form:input path="order.billingAddress.addr1" />
        <form:errors path="order.billingAddress.addr1" /></td>
    </tr>
    <tr>
      <td>Address 2:</td>
      <td><form:input path="order.billingAddress.addr2" />
        <form:errors path="order.billingAddress.addr2" /></td>
    </tr>
    <tr>
      <td>City:</td>
      <td><form:input path="order.billingAddress.city" /> 
        <form:errors path="order.billingAddress.city" /></td>
    </tr>
    <tr>
      <td>State:</td>
      <td><form:input path="order.billingAddress.state" />
        <form:errors path="order.billingAddress.state" /></td>
    </tr>
    <tr>
      <td>Zip:</td>
      <td><form:input path="order.billingAddress.zip" /> 
        <form:errors path="order.billingAddress.zip" /></td>
    </tr>
    <tr>
      <td>Country:</td>
      <td><form:input path="order.billingAddress.country" /> 
        <form:errors path="order.billingAddress.country" /></td>
    </tr>
    <tr>
      <td colspan="2">
        <form:checkbox path="shippingAddressRequired"
          label="Ship to different address..." /></td>
    </tr>
  </table>
  <p>
    <input type="image" src="../images/button_submit.gif">
  </p>
</form:form>
</div>

<%@ include file="IncludeBottom.jsp"%>
