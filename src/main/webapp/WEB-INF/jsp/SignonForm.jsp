<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ include file="IncludeTop.jsp"%>

<c:if test="${!empty message}">
  <b><font color="red"><c:url value="${message}" /></font></b>
</c:if>

<div align="center">
  <form action='<c:url value="/shop/signon.do"/>' method="POST">
    <c:if test="${!empty signonForwardAction}">
      <input type="hidden" name="forwardAction"
        value='<c:url value="${signonForwardAction}"/>' />
    </c:if>
    <table>
      <tr>
        <td colspan="2">Please enter your username and password. <br />&nbsp;
        </td>
      </tr>
      <tr>
        <td>Username:</td>
        <td><input type="text" name="username" size="20" value="j2ee" /></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="password" name="password" size="20" value="j2ee" /></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><input name="update" type="image" src="../images/button_submit.gif" /></td>
      </tr>
    </table>
  </form>
  <a href='<c:url value="/shop/newAccount.do"/>'> 
    <img border="0" src="../images/button_register_now.gif" alt="" />
  </a>
</div>

<%@ include file="IncludeBottom.jsp"%>