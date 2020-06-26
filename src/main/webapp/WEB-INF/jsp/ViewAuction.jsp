<%@ include file="IncludeTop.jsp"%>

<table id="main-menu">
  <tr>
    <td><a href='<c:url value="/shop/index.do"/>'>
      <b><font color="black" size="2">&lt;&lt; Main Menu</font></b></a>
    </td>
  </tr>  
</table>

<div align="center">
  <c:if test="${!empty message}">
    <b><c:out value="your bidding has been submitted successfully. " /></b>
  </c:if>
  
    
</div>

<%@ include file="IncludeBottom.jsp"%>