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
<h1>Home</h1>
${feedback }
	<sf:form cssClass="form" method="POST" commandName="searchParam" >
		<sf:select path="speciality" name="">
			<sf:option  value="NONE" label="--- Select ---"/>
	   		<sf:options items="${specialities}" />
   		</sf:select>
   		<input type="submit" value="Search" class="btn btn-primary"/>
	</sf:form>
	<c:if test="${doctors!=null}">
		<table class="table">
		<thead>
			<tr>
			<th>Doctor</th>
			<th>Speciality</th>
			<th>Email</th>
			<th></th>
			<th></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${doctors}" var="doctor">
		<c:if test="${doctor.getSpeciality().toString().equals(search) }">
		<tr>
			<td>Dr. ${doctor.getFirstName()} ${doctor.getLastName()}</td>
			<td>${doctor.getSpeciality()}</td>
			<td>${doctor.getEmail()}</td>
			<td><a href="<c:url value="/schedule/${doctor.getId()}" />">See Schedule</a></td>
		</tr>
		</c:if>
		</c:forEach>
		</tbody>
	</table>
	</c:if>
</body>
</html>