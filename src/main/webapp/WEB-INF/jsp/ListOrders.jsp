<%@ include file="IncludeTop.jsp"%>

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
            </a></b>
        </td>
        <td><fmt:formatDate value="${order.orderDate}"
            pattern="yyyy/MM/dd hh:mm:ss" />
        </td>
        <td onClick="printOrderDetail(${order.orderId}, this);">
        	<fmt:formatNumber value="${order.totalPrice}"
            pattern="$#,##0.00" />
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

<script src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
<!-- or <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<script>
function printOrderDetail(orderId, td) {
	
	if ($(td).children("ul").length > 0) {	
		$(td).children("ul").remove();
	}
	else {
		$(td).append("<ul id='detail'></ul>");	

		var reqUrl = "../rest/order/" + orderId;
		$.ajax({
			type: "GET",
			url: reqUrl,
			processData: false,
			success: function(response){	// object parsed from JSON text	
				$("#detail").append("<li>Shipping address: " + response.shipAddress1 + ", " + 
							   		  response.shipAddress2 + ", " + response.shipCity + "</li>");
				var content = "";
				$(response.lineItems).each(function(i, lineItem){	        	
			       	content += "LineItem " + lineItem.lineNumber + ": " + lineItem.quantity +
							" piece(s) of item " + lineItem.itemId + "<br>";
				});
				$("#detail").append ("<li>" + content + "</li>");				
				$("#detail").removeAttr("id");
			},
			error: function(){
				alert("ERROR", arguments);
			}
		});  
	}
};
</script>

<%@ include file="IncludeBottom.jsp"%>
