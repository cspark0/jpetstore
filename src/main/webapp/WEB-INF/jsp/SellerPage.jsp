<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="IncludeTop.jsp"%>
<div align="center">
    <b><font size="4">seller Page</font></b>
    <table class="n13">
    	<tr bgcolor="#FFFF88">
    		<td><a href="<c:url value="/shop/auctionRegister.do"/>">경매 상품 등록</a></td>
    	</tr>
    	<tr bgcolor="#FFFF88">
    		<td><a href="<c:url value="/shop/itemRegister.do"/>">일반 상품 등록</a></td>
    	</tr>
    	<tr bgcolor="#FFFF88">
    		<td><a href='<c:url value="/shop/SellerAuctionManage.do"></c:url>'>경매 상품 관리</a></td>
    	</tr>
    		<tr bgcolor="#FFFF88">
    		<td><a href='<c:url value="/shop/SellerItemManage.do"></c:url>'>일반 상품 관리</a></td>
    	</tr>
    	<tr bgcolor="#FFFF88">
    		<td><a href='<c:url value="/shop/ShippingManage.do"></c:url>'>Shipping Manage</a></td>
    	</tr>
    </table>
</div>

<%@ include file="IncludeBottom.jsp"%>
