<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ page session="false" %>
<html>
<head>
<title>Project</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/common.css" rel="stylesheet" >
<link rel="icon" href="<s:url value="resources/images/logo_med.png" />">
</head>
<body>
<div id="header">
<t:insertAttribute name="header" />
</div>
<div id="content">
<t:insertAttribute name="body" />
</div>
<div id="footer">
<t:insertAttribute name="footer" />
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>