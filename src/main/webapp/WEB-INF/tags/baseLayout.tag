<%@tag description="Base Layout" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="banner" fragment="true" required="false" %>

<t:genericLayout title="JPetStore Demo">
	<jsp:attribute name="header">
		<%@include file="/WEB-INF/jsp/customtag/header.jsp" %>	
	</jsp:attribute>
	<jsp:attribute name="footer">
		<%@include file="/WEB-INF/jsp/customtag/footer.jsp" %>	
	</jsp:attribute>
	<jsp:attribute name="banner">
		<jsp:invoke fragment="banner" />	
	</jsp:attribute>
	<jsp:body>
		<jsp:doBody />
	</jsp:body>
</t:genericLayout>
