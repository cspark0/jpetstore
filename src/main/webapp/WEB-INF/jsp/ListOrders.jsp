<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

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
        <td>  <!-- add click event handler -->
       		<a href="#" onclick="printOrderDetail(${order.orderId}, this);">
        		<fmt:formatNumber value="${order.totalPrice}" pattern="$#,##0.00" />
			</a>
		</td>       
      </tr>
    </c:forEach>
  </table>
</div>

<script src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
<!-- or <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
function printOrderDetail(orderId, a) {	// click event handler for <td> including price
	var td = $(a).parent();
	if ($(td).children("ul").length > 0) {	// if <td> includes <ul> (and order details)
		$(td).children("ul").remove();		// remove <ul> (and order details)
	}
	else {	
		$(td).append("<ul id='detail'></ul>");	// create <ul> of 'detail' in <td>	

		var reqUrl = "/rest/order/" + orderId;	// REST service URI		
		
		/*
		$.ajax({									// Ajax call to the REST service
			type: "GET",
			url: reqUrl,
			success: function(response){	// callback function: get JS object parsed from JSON response
				// add <li> of shipping address into <ul>
				$("#detail").append("<li>Shipping address: " + response.shipAddress1 + ", " + 
							   		  response.shipAddress2 + ", " + response.shipCity + "</li>");
				
				// collect lineitem infos from the response
				var content = "";
				$(response.lineItems).each( function(i, lineItem){	        	
			       	content += "LineItem " + lineItem.lineNumber + ": " + lineItem.quantity +
							" piece(s) of item " + lineItem.itemId + "<br>";
			    
				});
				
				// add <li> of lineitmes into <ul>
				$("#detail").append("<li>" + content + "</li>");	
				// remove id of <ul> for the next click event
				$("#detail").removeAttr("id");	
			},
			error: function(jqXHR){
				alert("ERROR: " + JSON.stringify(jqXHR));				
			}			
		});
		*/
		// Ajax 호출을 위해 Axios library 이용
		axios.get(reqUrl)
			.then(response => {
				var order = response.data;
				
				// add <li> of shipping address into <ul>
				$("#detail").append("<li>Shipping address: " + order.shipAddress1 + ", " + 
						order.shipAddress2 + ", " + order.shipCity + "</li>");
				
				// collect lineitem infos from the response
				var content = "";
				$(order.lineItems).each( function(i, lineItem){	        	
			       	content += "LineItem " + lineItem.lineNumber + ": " + lineItem.quantity +
							" piece(s) of item " + lineItem.itemId + "<br>";
			    
				});
				
				// add <li> of lineitmes into <ul>
				$("#detail").append("<li>" + content + "</li>");	
				// remove id of <ul> for the next click event
				$("#detail").removeAttr("id");	
			})
			.catch(error => { 
				alert("ERROR", error); console.error(error);
			});
	}
};
</script>

<%@ include file="IncludeBottom.jsp"%>
