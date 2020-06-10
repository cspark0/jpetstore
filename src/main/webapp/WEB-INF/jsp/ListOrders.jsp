<%@ include file="IncludeTop.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
function getOrder(orderId) {
	var reqUrl = "../rest/order/" + orderId;
	$.ajax({
		type: "get",
		url: reqUrl,
		processData: false,
		success: function(responseJson){	// object parsed from JSON text	
			$("#detail").html("<ul></ul>");
			$("#detail > ul").append("<li>Order ID: " + responseJson.orderId + "</li>");
			$("#detail > ul").append("<li>Order Date: " + new Date(responseJson.orderDate) + "</li>");
			$("#detail > ul").append("<li>User name: " + responseJson.username + "</li>");
			$("#detail > ul").append("<li>Shipping address: " + responseJson.shipAddress1 + ", " + 
				responseJson.shipAddress2 + ", " + responseJson.shipCity + "</li>");
			var content = "";
			$(responseJson.lineItems).each(function(i, lineItem){	        	
		       	content += "LineItem " + lineItem.lineNumber + ": " + lineItem.quantity +
						" piece(s) of item " + lineItem.itemId + "<br>";
			});
			$("#detail > ul").append ("<li>" + content + "</li>");
			$("#detail > ul").append("<li>Total prices: " + responseJson.totalPrice + "</li>");
		},
		error: function(){
			alert("ERROR", arguments);
		}
	});
};
</script>
<div align="center">
<table>
<tr><td>
<div id="detail"></div>
</td></tr>
</table>
</div>
<div align="center">
  <p>
    <font size="4"><b>My Orders</b></font>
  </p>
  <table class="n23">
    <tr bgcolor="#CCCCCC">
      <td><b>Order ID</b></td> <td><b>Date</b></td> <td><b>Total Price</b></td>
    </tr>
    <c:forEach var="order" items="${orderList}">
      <tr bgcolor="#FFFF88">
        <td>
          <b><a href='<c:url value="/shop/viewOrder.do">
              <c:param name="orderId" value="${order.orderId}"/></c:url>'>
              <font color="black"><c:out value="${order.orderId}" /></font>
            </a></b></td>
        <td><fmt:formatDate value="${order.orderDate}"
            pattern="yyyy/MM/dd hh:mm:ss" /></td>
        <td onClick="getOrder(${order.orderId});">
        	<fmt:formatNumber value="${order.totalPrice}"
            pattern="$#,##0.00" /></td>
      </tr>
    </c:forEach>
  </table>
</div>
<%@ include file="IncludeBottom.jsp"%>
