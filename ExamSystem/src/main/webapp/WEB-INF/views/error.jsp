<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="./headerLink.jsp" />
</head>
<body>
	<div class="m-1">
		<jsp:include page="./menuBar.jsp" />
		<div class="jumbotron text-center">
			<div class="display-1">${status}</div>
			<div class="display-3">${message}</div>
			<div class="display-3 text-warning"><i class="far fa-sad-tear"></i> <i class="far fa-sad-tear"></i> <i class="far fa-sad-tear"></i></div>
		</div>
		<jsp:include page="./footerLink.jsp" />
	</div>
</body>
</html>