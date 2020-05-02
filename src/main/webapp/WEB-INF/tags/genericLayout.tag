<!DOCTYPE html>
<%@tag description="Generic Layout" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" %>   
<%@attribute name="header" fragment="true" required="true" %>  
<%@attribute name="banner" fragment="true" %>
<%@attribute name="footer" fragment="true" required="true" %>
<html>
<head>
	<title>${title}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=windows-1252"	/>
	<meta http-equiv="Cache-Control" content="max-age=0">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="Pragma" content="no-cache">
	<link rel="stylesheet" href="../style/petstore.css" type="text/css" />
</head>
<body>
	<div id="header">
		<jsp:invoke fragment="header" />
	</div>
	<div id="body">
		<jsp:doBody />
	</div>		
	<div id="banner">
		<jsp:invoke fragment="banner" />
	</div>
	<div id="footer">
		<jsp:invoke fragment="footer" />
	</div>
</body>
</html>
