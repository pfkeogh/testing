<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<link rel="stylesheet" type="text/css" href="<s:url value="resources/css/common.css" />" >
<div class='container-fluid'>
   <nav id='nav-top'class="navbar navbar-inverse navbar-fixed-top">
     <div class="container-fluid">
       <div class="navbar-header">
         <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
           <span class="sr-only">Toggle navigation</span>
           <span class="icon-bar"></span>
           <span class="icon-bar"></span>
           <span class="icon-bar"></span>
         </button>
         <span id = 'header' class="navbar-brand" >Project</span>
       </div>
       <div id="navbar" class="navbar-collapse collapse">
         <ul class="nav nav-pills navbar-nav navbar-right">
           <li class="active-nav "><a href="<c:url value="/home" />">Home</a></li>
           <li id='user' role="presentation" class="dropdown">
		    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
		      <c:choose>
		      	<c:when test="${sessionScope.user!=null}">
		      		<c:out value="${sessionScope.user.getFirstName()}"></c:out>
		      	</c:when>
		      	<c:when test="${sessionScope.doctor!=null}">
		      		<c:out value="Dr.${sessionScope.doctor.getFirstName()}"></c:out>
		      	</c:when>
		      	<c:otherwise>
		      		<c:out value="User"></c:out>
		      	</c:otherwise>
		      </c:choose>
		      <span class="caret"></span>
		    </a>
		    <ul style="background-color: #cccccc" class="dropdown-menu">
               	<li><a href="<c:url value="/admin" />">Administrator</a></li>
               	<li><a href="<c:url value="/doctor/login" />">Doctor</a></li>
                <li role="separator" class="divider"></li>
                 <c:choose>
                 	<c:when test="${sessionScope.user==null}">
                 		<li><a href="<c:url value="/login" />">Login</a></li>
                 		<li ><a href="<c:url value="/register" />">Register</a></li>
                 	</c:when>
                 	<c:otherwise>
				     <li><a href="<c:url value="/logout" />">Logout</a></li>
                 	</c:otherwise>
			     </c:choose>
		    </ul>
		  </li >
         </ul>
       </div>
     </div>
   </nav>
  </div>