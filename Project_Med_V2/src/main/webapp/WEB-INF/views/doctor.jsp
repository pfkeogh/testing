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
<h1>Doctor</h1>
<c:out value="${param.feedback}"></c:out><br/>

<a href="<c:url value="/doctor/schedule" />">Set Schedule</a>
 | <a href="<c:url value="/doctor/leave" />">Request Leave</a>
 | <a href="<c:url value="/doctor/appts" />">View Appointments</a>
 
 <br/>
 <c:if test="${sessionScope.doctor.getLeave()!=null}">
 </c:if>
</body>
</html>