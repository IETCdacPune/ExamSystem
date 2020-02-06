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
	<div class="container">
		<jsp:include page="./menuBar.jsp" />
		<div class="row">
		<div class="col-3">
			<img alt="${user.prn}" src="/Common/avatar/${user.imgUrl}" class="rounded mx-auto d-block w-100 h-100">
		</div>
		<div class="col-9">
			<h3>PRN:-${user.prn}</h3>
			<h3>Name:-${user.firstName} ${user.lastName}</h3>
			<h4>Email:-${user.emailId}</h4>
		</div>
		</div>
		<jsp:include page="./footerLink.jsp" />
	</div>
</body>
</html>