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
<h1>Login</h1>
<sf:form method="POST" commandName="user">
	Email: <sf:input path="email" /><br/>
	Password: <sf:password path="password" /><br/>
	<c:out value="${error}"></c:out><br/>
	<input class="btn btn-primary" type="submit" value="Login" />
	</sf:form>
</body>
</html>