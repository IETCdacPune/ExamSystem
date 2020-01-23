<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="../headerLink.jsp" />
</head>
<body onclick="requestFullScreen(document.body)">
	<div class="container">
		<jsp:include page="../menuBar.jsp" />
		<br>
		<div class="jumbotron">
			<h1 >You obtained <strong>${marks}</strong> marks out of ${total} in this exam.</h1>
			<h4>Result:-${result}</h4>
		</div>
	</div>
	
</body>
</html>