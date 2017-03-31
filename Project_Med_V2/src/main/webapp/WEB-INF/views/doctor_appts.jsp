<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
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
<%
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
	LocalDateTime today = LocalDateTime.now(); 
	String day = today.format(formatter);
	today = LocalDateTime.parse(day, formatter);
  	pageContext.setAttribute("today", today);
  	pageContext.setAttribute("formatter", formatter);
%>
<h1>Appointments</h1>
${feedback }
<a href="<c:url value="/doctor" />">Back</a>
<c:if test="${apptlist!=null}">
<table class="table">
	<tr>
		<th>Appointment</th>
		<th></th>
	</tr>
	<c:forEach items="${apptlist}" var="appt">
	<tr>
		<td>${appt.getTime() }</td>
		<c:choose>
		<c:when test="${LocalDateTime.parse(appt.getTime(), formatter).isBefore(today) }">
		<td><a href="<c:url value='/doctor/review/${appt.getId()}' />">View Review</a></td>
		</c:when>
		<c:otherwise>
		<td><a href="<c:url value='/doctor/cancel/${appt.getId()}' />">Cancel</a></td>	
		</c:otherwise>
		</c:choose>
	</tr>
	</c:forEach>
</table>
</c:if> 
</body>
</html>