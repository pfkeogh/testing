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
<h1>Register new Doctor</h1>
<a href="<c:url value="/doctor" />">Back</a>
	<sf:form cssClass="form" method="POST" commandName="doctor">
		<sf:hidden path="id"/>
		First Name: <sf:input path="firstName" />
		<sf:errors path="firstName" cssClass="error" /><br/>
		Last Name: <sf:input path="lastName" />
		<sf:errors path="lastName" cssClass="error" /><br/>
		Email: <sf:input path="email" />
		<sf:errors path="email" cssClass="error" /><br/>
		Male: <sf:radiobutton path="gender" value="male"/> Female: <sf:radiobutton path="gender" value="female"/>
		<sf:errors path="gender" cssClass="error" /><br/>
		Speciality: <sf:select path="speciality">
			<sf:option  value="NONE" label="--- Select ---"/>
	   		<sf:options items="${specialityList}" />
   		</sf:select>
		<sf:errors path="speciality" cssClass="error" /><br/>
		Password: <sf:password path="password" />
		<sf:errors path="password" cssClass="error" /><br/>
		<input type="submit" value="Register" class="btn btn-primary"/>
	</sf:form>
	${feedback}
</body>
</html>