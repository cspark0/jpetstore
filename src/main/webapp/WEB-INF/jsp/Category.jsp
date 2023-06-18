<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ include file="IncludeTop.jsp"%>

<table id="main-menu">
  <tr>
    <td><a href='<c:url value="/shop/index.do"/>'><b>
       <font color="black" size="2">&lt;&lt; Main Menu</font></b></a>
    </td>
  </tr>
</table>

<div align="center">
  <h2><c:out value="${category.name}" /></h2>
  
  <div id="root" align="center"></div>
  <script type="text/javascript">window.categoryId = '${category.categoryId}'</script>
  <script src="/static/js/category.bundle.js"></script>

<!--
  <table class="n23">
    <tr bgcolor="#CCCCCC">
      <td><b>Product ID</b></td>
      <td><b>Name</b></td>
    </tr>
    <c:forEach var="product" items="${productList.pageList}">
      <tr bgcolor="#FFFF88">
        <td><b><a href='<c:url value="/shop/viewProduct.do">
          <c:param name="productId" value="${product.productId}"/></c:url>'>
            <font color="black"><c:out value="${product.productId}" /></font>
          </a></b></td>
        <td><c:out value="${product.name}" /></td>
      </tr>
    </c:forEach>
    <tr>
      <td>
        <c:if test="${!productList.firstPage}">
          <a href='<c:url value="/shop/viewCategory2.do">
            <c:param name="page" value="previous"/></c:url>'>
              <font color="white"><B>&lt;&lt; Prev</B></font></a>
        </c:if> 
        <c:if test="${!productList.lastPage}">
          <a href='<c:url value="/shop/viewCategory2.do">
            <c:param name="page" value="next"/></c:url>'>
              <font color="white"><B>Next &gt;&gt;</B></font></a>
        </c:if>
      </td>
    </tr>
  </table>
-->

</div>
    

<script src="<c:url value='/js/jquery-3.4.1.min.js'/>"></script>
<!-- or <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<script>
/*
function registerProduct() {	
	var productData = {
		productId: $("#prodId").val(),
		name: $("#prodName").val(),
		description: $("#prodDesc").val(),
		categoryId: "${category.categoryId}"
	};
	
	var reqUrl = "/rest/product";			// CreateProduct service URI 		
	$.ajax({								// Ajax call to the REST service
		type: "POST",
		url: reqUrl,
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(productData)		
	}).done(function() {
		alert("A product has been registered succcessfuly!");
		// issue a new request for getting the product list updated 
		location.replace(document.location.origin
				+ "/shop/viewCategory.do?categoryId=${category.categoryId}");
	}).fail(function(jqXHR) {
		alert("ERROR: "+ JSON.stringify(jqXHR));
	});  
};

function deleteProduct(prodId) {	
	var reqUrl = "/rest/product/" + prodId; // DeleteProduct service URI
	
	if (confirm("delete?")) {
		$.ajax({							// Ajax call to the REST service
			type: "DELETE",
			url: reqUrl,
		}).done(function() {
			alert("The product has been removed succcessfuly!")
			// issue a new request for getting the product list updated 
			location.replace(document.location.origin
					+ "/shop/viewCategory.do?categoryId=${category.categoryId}");	
		}).fail(function(jqXHR) {
			alert("ERROR: "+ JSON.stringify(jqXHR));
		});
	}
};

function toggleForm() {
	$("#reg-form").toggle();
};
*/
</script>

<%@ include file="IncludeBottom.jsp"%>
