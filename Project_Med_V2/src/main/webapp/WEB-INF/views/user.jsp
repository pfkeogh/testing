<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
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
<c:out value="Hello "></c:out><c:out value="${sessionScope.user.getFirstName()}"> </c:out><br>
<c:out value="${param.feedback}"></c:out>
<c:if test="${apptlist!=null}">
<c:set var="test" value="test1"/>
<%
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
	LocalDateTime today = LocalDateTime.now(); 
	String day = today.format(formatter);
	today = LocalDateTime.parse(day, formatter);
  	pageContext.setAttribute("today", today);
  	pageContext.setAttribute("formatter", formatter);
%>
<table class="table">
	<tr>
		<th>Appointment</th>
		<th></th>
	</tr>
	<c:forEach items="${apptlist}" var="appt">
	<tr>
		<td>${appt.getTime() }</td>
		<td>
		<c:choose>
		<c:when test="${LocalDateTime.parse(appt.getTime(), formatter).isBefore(today) }">
		<a href="<c:url value='/user/review/${appt.getId()}' />">Leave Feedback</a>
		</c:when>
		<c:otherwise>
		<a href="<c:url value='/user/cancel/${appt.getId()}' />">Cancel</a>
		</c:otherwise>
		</c:choose>
		</td>	
	</tr>
	</c:forEach>
</table>
</c:if>
</body>
</html>