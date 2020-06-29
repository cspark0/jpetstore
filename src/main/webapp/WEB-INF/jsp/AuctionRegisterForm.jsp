<%@ include file="IncludeTop.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
function categoryChange(e) {
   var good_a = ["Angelfish", "Tiger Shark", "Koi", "Goldfish"];
   var good_b = ["Bulldog", "Poodle", "Dalmation", "Golden Retriever","Labrador Retriever","Chihuahua"];
   var good_c = ["Rattlesnake", "Iguana"];
   var good_d = ["Manx", "Persian"];
   var good_e = ["Amazon Parrot", "Finch"];
   var target = document.getElementById("good");
   if(e.value == "DOGS") var d = good_b;
   else if(e.value== "FISH") var d = good_a;
   else if(e.value == "REPTILES") var d = good_c;
   else if(e.value == "CATS") var d = good_d;
   else if(e.value == "BIRDS") var d = good_e;
   
   
   target.options.length = 0;

   for (x in d) {
      var opt = document.createElement("option");
      opt.value = d[x];
      opt.innerHTML = d[x];
      target.appendChild(opt);
   }   
}
</script>
<c:set var="targetUrl"><c:url value="/shop/auctionRegisterSubmitted.do" /></c:set>

<div align="center">
<form:form modelAttribute="auctionForm" action="${targetUrl}" method="post">
  <form:errors cssClass="error" /> <br><br>
  
  <table class="n13">
    <tr>
      <td colspan="2">
        <font color="green" size="4"><b>상품 등록 </b></font></td>
    </tr>
    <tr>
      <td>상품 이름 :</td>
      <td><form:input path="auctionItem.itemId" />
       <form:errors path="auctionItem.itemId" /></td>
    </tr>
    <tr>
      <td>가격:</td>
      <td><form:input path="auctionItem.listPrice" /> 
       <form:errors path="auctionItem.listPrice" /></td>
    </tr>
    <tr>
       <td>단위 가격:</td>
       <td><form:input path="auctionItem.unitCost" /> 
       <form:errors path="auctionItem.unitCost" /></td>
    </tr>
    <tr>
      <td>경매 마감 기한  :</td>

      <td> <input name="keyword" size="14" />
      "yyyy-MM-dd HH:mm" 형식으로 미래의 시각을 입력하세요.
      <%--   <form:errors path="order.expiryDate" /></td> --%>

    </tr>
    <tr>
      <td>카테고리 선택  :</td>
      <td><form:select onchange="categoryChange(this)" id = "category" path="auctionItem.product.categoryId" items="${categories}" /> 
      
       <%--  <form:errors path="order.billToFirstName" /></td> --%>
    </tr>
    <tr>
      <td>상세 카테고리 선택  :</td>
      <td><form:select path="auctionItem.productId" id = "good"/>
      <%--   <form:errors path="order.billToLastName" /></td> --%>
    </tr>
    <tr>
      <td>부가 설명 :</td>
      <td><form:input path="auctionItem.attribute1" />
        <form:errors path="auctionItem.attribute1" /></td> 
    </tr>

  </table>
  <p>
    <input type="image" src="../images/button_submit.gif">
  </p>
</form:form>

</div>

<%@ include file="IncludeBottom.jsp"%>