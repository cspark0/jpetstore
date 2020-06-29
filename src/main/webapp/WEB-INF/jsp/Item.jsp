
<%@ page contentType="text/html; charset=UTF-8" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
setInterval(
	function(){
	requestAjax();

		},1000
	)
	
	function requestAjax(){
	System.out.println("requestAjax");
		$.ajax
		({
			type : "GET",
			url :  "shop/getDeadline.do"
			dataType : "json",
			success: function(responseJson){
				
			
				var content = "<br>데이터 사용:<br>";
		        if (responseJson.length) {
					$(responseJson).each(function(i, item){
		            	content += "남은시간"+ item.closingTime + 
	          			 "<br><br>";
		            });
				}
				else content = "No result!";	         
				$("#item").append(content);
				$("#time").html(item);
				}

			})

	}

</script>
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
    <tr id = time>시간이 왜 안뜨나</tr>
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
       
        <a href='<c:url value="/shop/addItemToDepositCart.do">
          <c:param name="workingItemId" value="${item.itemId}"/></c:url>'>
             경매 참여(보증금계산)</a>
           </td> </c:if>
    </tr>
   
  </table>
 
</div>


<%@ include file="IncludeBottom.jsp"%>