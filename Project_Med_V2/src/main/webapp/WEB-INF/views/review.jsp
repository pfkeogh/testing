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
<h1>Leave Review</h1>
<sf:form commandName="review">
	<sf:hidden path="id"/>
	Please rate your experience (1 Bad)-(5 Great):
	<sf:select path="score">
		<sf:option value="1"></sf:option>
		<sf:option value="2"></sf:option>
		<sf:option value="3"></sf:option>
		<sf:option value="4"></sf:option>
		<sf:option value="5"></sf:option>
	</sf:select >
	<sf:errors path="score" cssClass="error" /><br/>
	Comments:<br>
	<sf:textarea path="text" placeholder="optional"/><br>
	<input type="submit" value="Submit" class="btn btn-primary"/>
</sf:form>
${feedback}
</body>
</html>