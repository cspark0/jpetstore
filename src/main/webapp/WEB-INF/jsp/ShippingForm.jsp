<%@ include file="IncludeTop.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="targetUrl"><c:url value="/shop/newOrderSubmitted.do" /></c:set>

<div align="center">
<form:form modelAttribute="orderForm" action="${targetUrl}" method="post">
  <form:errors cssClass="error" /><br><br>

  <table class="n13">
    <tr>
      <td colspan="2">
        <font color="GREEN" size="4"><b>Shipping Address</b></font></td>
    </tr>
    <tr>
      <td>First name:</td>
      <td><form:input path="order.shipToFirstName" />
        <form:errors path="order.shipToFirstName" /></td>
    </tr>
    <tr>
      <td>Last name:</td>
      <td><form:input path="order.shipToLastName" />
        <form:errors path="order.shipToLastName" /></td>
    </tr>
    <tr>
      <td>Address 1:</td>
      <td><form:input path="order.shippingAddress.addr1" /> 
         <form:errors path="order.shippingAddress.addr1" /></td>
    </tr>
    <tr>
      <td>Address 2:</td>
      <td><form:input path="order.shippingAddress.addr2" /> 
         <form:errors path="order.shippingAddress.addr2" /></td>
    </tr>
    <tr>
      <td>City:</td>
      <td><form:input path="order.shippingAddress.city" /> 
        <form:errors path="order.shippingAddress.city" /></td>
    </tr>
    <tr>
      <td>State:</td>
      <td><form:input path="order.shippingAddress.state" /> 
        <form:errors path="order.shippingAddress.state" /></td>
    </tr>
    <tr>
      <td>Zip:</td>
      <td><form:input path="order.shippingAddress.zip" /> 
        <form:errors path="order.shippingAddress.zip" /></td>
    </tr>
    <tr>
      <td>Country:</td>
      <td><form:input path="order.shippingAddress.country" /> 
        <form:errors path="order.shippingAddress.country" /></td>
    </tr>
  </table>
  <p>
    <input type="image" src="../images/button_submit.gif">
  </p>
</form:form>
</div>
<%@ include file="IncludeBottom.jsp"%>