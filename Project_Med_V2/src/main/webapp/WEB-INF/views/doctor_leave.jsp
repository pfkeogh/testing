<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h1>Request Leave</h1>
<a href="<c:url value="/doctor" />">Back</a>
<sf:form cssClass="form" method="POST" commandName="leave">
		Start Date: <sf:input type="date" path="startDate" />
		<sf:errors path="startDate" cssClass="error" /><br/>
		End Date: <sf:input  type="date" path="endDate" />
		<sf:errors path="endDate" cssClass="error" /><br/>
		<input type="submit" value="Register" class="btn btn-primary"/>
	</sf:form>
	${feedback}
</body>
</html>