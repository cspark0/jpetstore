<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>JPetStore</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="Cache-Control" content="max-age=0">
  <meta http-equiv="Cache-Control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT">
  <meta http-equiv="Pragma" content="no-cache">
  <link rel="stylesheet" href="../style/petstore.css" type="text/css" />
</head>

<body bgcolor="white">
<table class="top">
  <tr>
    <td>
      <a href="<c:url value="/shop/index.do"/>">
        <img border="0" src="../images/logo-topbar.gif" /></a>
    </td>
    <td style="text-align:right">
      <a href="<c:url value="/shop/viewCart.do"/>">
        <img border="0" name="img_cart" src="../images/cart.gif" /></a>
      <img border="0" src="../images/separator.gif" />
      <c:if test="${empty userSession.account}" >
        <a href="<c:url value="/shop/signonForm.do"/>">
          <img border="0" name="img_signin" src="../images/sign-in.gif" /></a>
      </c:if>
      <c:if test="${!empty userSession.account}" >
        <a href="<c:url value="/shop/signoff.do"/>">
          <img border="0" name="img_signout" src="../images/sign-out.gif" /></a>
        <img border="0" src="../images/separator.gif" />
        <a href="<c:url value="/shop/editAccount.do"/>">
          <img border="0" name="img_myaccount" src="../images/my_account.gif" /></a>
      </c:if>
      <img border="0" src="../images/separator.gif" />&nbsp;
      <a href="../help.html"><img border="0" name="img_help" src="../images/help.gif" /></a>
    </td>
    <td style="text-align:left">
      <form action="<c:url value="/shop/searchProducts.do"/>" method="post">
	    <input type="hidden" name="search" value="true"/>
        <input type="text" name="keyword" size="14" />&nbsp;
        <input src="../images/search.gif" type="image"/>
      </form>
    </td>
  </tr>
</table>

<%@ include file="IncludeQuickHeader.jsp" %>
