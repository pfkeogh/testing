<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Set Schedule</h1>
<sf:form cssClass="form" method="POST" commandName="schedule">
		Days Available: <sf:checkboxes items="${days}" path="days" /><br/>
		Start Time: <sf:input type="time" path="startDate" />
		<sf:errors path="startDate" cssClass="error" /><br/>
		End Time: <sf:input  type="time" path="endDate" />
		<sf:errors path="endDate" cssClass="error" /><br/>
		<input type="submit" value="Register" class="btn btn-primary"/>
	</sf:form>
	${feedback}
</body>
</html>