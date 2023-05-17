<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>
  <font color="darkgreen">Account Information</font>
</h3>

<table class="n13">
  <tr>
    <td>First name:</td>
    <td><form:input path="account.firstName" htmlEscape="false"/>
      <form:errors path="account.firstName" cssClass="error" /></td>
  </tr>
  <tr>
    <td>Last name:</td>
    <td><form:input path="account.lastName" htmlEscape="false"/> 
      <form:errors path="account.lastName" cssClass="error" /></td>
  </tr>
  <tr>
    <td>Email:</td>
    <td><form:input path="account.email" htmlEscape="false"/>
      <form:errors path="account.email" cssClass="error" /></td>
  </tr>
  <tr>
    <td>Phone:</td>
    <td><form:input path="account.phone" /> 
      <form:errors path="account.phone" cssClass="error" /></td>
  </tr>
  <tr>
    <td>Address 1:</td>
    <td><form:input path="account.address.addr1" htmlEscape="false"/>
      <form:errors path="account.address.addr1" cssClass="error" /></td>
  </tr>
  <tr>
    <td>Address 2:</td>
    <td><form:input path="account.address.addr2" htmlEscape="false"/>
      <form:errors path="account.address.addr2" cssClass="error" /></td>
  </tr>
  <tr>
    <td>City:</td>
    <td><form:input path="account.address.city" htmlEscape="false"/> 
      <form:errors path="account.address.city" cssClass="error" /></td>
  </tr>
  <tr>
    <td>State:</td>
    <td><form:input path="account.address.state" htmlEscape="false"/> 
      <form:errors path="account.address.state" cssClass="error" /></td>
  </tr>
  <tr>
    <td>Zip:</td>
    <td><form:input path="account.address.zip" /> 
      <form:errors path="account.address.zip" cssClass="error" /></td>
  </tr>
  <tr>
    <td>Country:</td>
    <td><form:input path="account.address.country" htmlEscape="false"/>
      <form:errors path="account.address.country" cssClass="error" /></td>
  </tr>
</table>

<h3>
  <font color="darkgreen">Profile Information</font>
</h3>

<table class="n13" >
  <tr>
    <td>Language Preference:</td>
    <td><form:select path="account.profile.languagePreference" items="${languages}" />
      <form:errors path="account.profile.languagePreference" cssClass="error" /></td>
  </tr>
  <tr>
    <td>Favourite Category:</td>
    <td><form:select path="account.profile.favouriteCategoryId" items="${categories}" /> 
      <form:errors path="account.profile.favouriteCategoryId" cssClass="error" /></td>
  </tr>
  <tr>
    <td colspan="2"><form:checkbox path="account.profile.listOption" label="Enable MyList" /> 
      <form:errors path="account.profile.listOption" cssClass="error" /></td>
  </tr>
  <tr>
    <td colspan="2"><form:checkbox path="account.profile.bannerOption" label="Enable MyBanner" /> 
      <form:errors path="account.profile.bannerOption" cssClass="error" /></td>
  </tr>
</table>