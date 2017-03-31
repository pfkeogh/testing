<%@page import="java.time.format.DateTimeFormatter"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.spring.pojo.Day" %>
<%@ page import="java.time.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<h1>Dr. ${doctor.getFirstName()}</h1>

<c:if test="${doctor.getSchedule().getStartDate()==null }">The Doctor has not set their schedule yet</c:if>
<c:if test="${doctor.getSchedule().getStartDate()!=null }">
<c:out value="${today}"></c:out><br>
<c:choose>
	<c:when test="${param.week==null}">
		<c:set var="week" value="0"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="week" value="${param.week}"></c:set>
	</c:otherwise>
</c:choose>
<a href="<c:url value='/prevweek/${doctor.getId()}?week=${week}' />"><button class="btn btn-primary">Previous Week</button></a>
<a href="<c:url value='/nextweek/${doctor.getId()}?week=${week}' />"><button class="btn btn-primary">Next Week</button></a>
<table class="table">
<tr>
	<c:forEach items="${days}" var="d">
		<th>${d}</th>
	</c:forEach>
</tr>
<tr>
<c:set var="i" value="${0}"></c:set>
	<c:forEach items="${days}" var="d">
		<td>
			<c:if test="${available[d.num]}">
				<c:forEach items="${times[i]}" var="time">
					<a href="<c:url value="/schedule/${doctor.getId()}/${today.plusDays(i).format(formatter)} ${time}" />">
					${time}</a><br/>
				</c:forEach>
		 	</c:if>		 	
		 </td>
		<c:set var="i" value="${i+1}"></c:set>
	</c:forEach>
</tr>
</table>
</c:if>
</body>
</html>