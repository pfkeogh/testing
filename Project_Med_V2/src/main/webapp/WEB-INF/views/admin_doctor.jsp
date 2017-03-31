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
<h1>Doctors</h1>
	<a href="<c:url value="/newdoctor" />">Register New Doctor</a><br/>
	<c:out value="${param.feedback}"></c:out>
	<table class="table">
		<thead>
			<tr>
			<th>Doctor</th>
			<th>Speciality</th>
			<th>Email</th>
			<th></th>
			<th></th>
			<th></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${doctors}" var="doctor">
		<tr>
			<td>Dr. ${doctor.getFirstName()} ${doctor.getLastName()}</td>
			<td>${doctor.getSpeciality()}</td>
			<td>${doctor.getEmail()}</td>
			<td><a href="<c:url value='/doctors/edit/${doctor.getId()}' />">Edit</a></td>
			<td><a href="<c:url value='/doctors/delete/${doctor.getId()}' />">Delete</a></td>
			<td>
				<c:if test="${(doctor.getLeave().getStartDate()!=null && !doctor.getLeave().isApproved())}">
				<a href="<c:url value='/doctors/leave/${doctor.getId()}' />">
				Grant leave ${doctor.getLeave().toString()}
				</a>
				</c:if>
				<c:if test="${(doctor.getLeave().getStartDate()!=null && doctor.getLeave().isApproved())}">
				<a href="<c:url value='/doctors/cancelleave/${doctor.getId()}' />">
				Cancel leave ${doctor.getLeave().toString()}
				</a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>