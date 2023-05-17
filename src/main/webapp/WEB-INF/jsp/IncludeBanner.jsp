<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br>
<c:if test="${userSession.account.profile.bannerOption}">
  <table class="top">
    <tr>
      <td align="center"><c:out value="${userSession.account.profile.banner.bannerName}" escapeXml="false" /> &nbsp;</td>
    </tr>
  </table>
</c:if>
