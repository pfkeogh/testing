<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h1>Make appointment with Dr. ${doctor.getFirstName()} ${doctor.getLastName()}</h1>
${doctor.getSpeciality()} <br>
${time}<br>

${feedback }
<form method="post">
	<input type="submit" value="Confirm Appointment" class="btn btn-primary"/>
</form>
</body>
</html>