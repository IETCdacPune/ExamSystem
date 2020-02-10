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
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>
	<div class="m-1">
		<jsp:include page="../menuBar.jsp" />
		<br>
		<div class="jumbotron">
			<h1>
				You obtained <strong>${marks}</strong> marks out of ${total} in this
				exam.
			</h1>
			<h4>Result:-${result}</h4>
		</div>
		<c:if test="${result}">
			<div class="demo"></div>
		</c:if>
	</div>
	<script>
		$('.demo').fireworks({
			sound : true,
			opacity : 0.9,
			width : '100%',
			height : '100%'
		});
	</script>
</body>
</html>