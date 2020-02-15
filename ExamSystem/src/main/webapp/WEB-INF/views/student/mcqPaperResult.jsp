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
<script type="text/javascript" src="/jquery.fireworks.js"></script>
<style>
html, * {
	margin: 0;
	padding: 0
}

body {
	width: 100%;
	height: 100%;
}

.demo {
	margin: 0 auto;
	top: 20%;
	left: 0%;
	width: 100%;
	height: 80%;
}
</style>
</head>
<body>
	<div class="m-1">
		<jsp:include page="../menuBar.jsp" />
		<br>
		<c:if test="${result eq 'Pass'}">
			<div class="demo" style="height: 90vh;">
				<div class="jumbotron h-75  text-center">
					<h1>
						You obtained <strong>${marks}</strong> marks out of ${total} in
						this exam.
					</h1>
					<h4>Result:-${result}</h4>
				</div>
			</div>
		</c:if>
		<c:if test="${result ne 'Pass'}">
				<div class="jumbotron h-75 text-center">
					<h1>
						You obtained <strong>${marks}</strong> marks out of ${total} in
						this exam.
					</h1>
					<h4>Result:-${result}</h4>
					<i class="far fa-sad-cry"></i> <i class="far fa-sad-cry"></i> <i
			class="far fa-sad-cry"></i>
				</div>
		</c:if>
	</div>
	<script type="text/javascript">
		$(document).ready(()=>{$('div.demo').fireworks();});
		
	</script>
</body>
</html>