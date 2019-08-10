<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student dashboard</title>
 <jsp:include page="../headerLink.jsp"/>
</head>
<body>
<div class="container">
 
 <a href="/login" class="btn btn-primary"><span class="fas fa-users"></span> SignIn</a>
        <a href="/logout" class="btn btn-danger">Logout <span class="fa fa-sign-out"></span> </a>
         <a href="/facebook" class="btn btn-primary">Facebook <span class="fa fa-facebook"></span> </a>       
        <a href="/google" class="btn btn-danger"> Google <span class="fa fa-google-plus"></span> </a> 
        <a href="/linkedin" class="btn btn-primary">LinkedIn <span class="fa fa-linkedin"></span> </a> 
    <jsp:include page="../footerLink.jsp"/>
 </div>
</body>
</html>