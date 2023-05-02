<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ include file="IncludeTop.jsp"%>

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
      <td><form:input path="order.shipAddress1" /> 
         <form:errors path="order.shipAddress1" /></td>
    </tr>
    <tr>
      <td>Address 2:</td>
      <td><form:input path="order.shipAddress2" /> 
         <form:errors path="order.shipAddress2" /></td>
    </tr>
    <tr>
      <td>City:</td>
      <td><form:input path="order.shipCity" /> 
        <form:errors path="order.shipCity" /></td>
    </tr>
    <tr>
      <td>State:</td>
      <td><form:input path="order.shipState" /> 
        <form:errors path="order.shipState" /></td>
    </tr>
    <tr>
      <td>Zip:</td>
      <td><form:input path="order.shipZip" /> 
        <form:errors path="order.shipZip" /></td>
    </tr>
    <tr>
      <td>Country:</td>
      <td><form:input path="order.shipCountry" /> 
        <form:errors path="order.shipCountry" /></td>
    </tr>
  </table>
  <p>
    <input type="image" src="../images/button_submit.gif">
  </p>
</form:form>
</div>
<%@ include file="IncludeBottom.jsp"%>