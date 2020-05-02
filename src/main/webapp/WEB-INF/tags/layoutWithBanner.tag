<%@tag description="Layout with Banner" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:baseLayout>
	<jsp:attribute name="banner">
		<%@include file="/WEB-INF/jsp/customtag/banner.jsp" %>	
	</jsp:attribute>
	<jsp:body>
		<jsp:doBody />
	</jsp:body>
</t:baseLayout>
