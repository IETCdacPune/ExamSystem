<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="../headerLink.jsp" />
</head>
<body>
	<div class="m-1">
		<jsp:include page="../menuBar.jsp" />
		<c:if test="${not empty errmsg}">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Error!</strong> ${errmsg}
			</div>
		</c:if>
		<div class="row">
		<div class="col-3">
			<img alt="${user.prn}" src="/Common/avatar/${user.imgUrl}" class="rounded mx-auto d-block w-100 h-100">
		</div>
		<div class="col-9">
			<a class="close text-rigth" href="/Student/editProfile"><em class="far fa-edit"></em></a>
			<h3>PRN:-${user.prn}</h3>
			<h3>Name:-${user.firstName} ${user.lastName}</h3>
			<h4>Email:-${user.emailId}</h4>
		</div>
		</div>
		<jsp:include page="../footerLink.jsp" />
	</div>
</body>
</html>