<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="../headerLink.jsp" />
</head>
<body>
	<div class="container">
		<jsp:include page="../menuBar.jsp" />
		<br> <br>
		<c:if test="${not empty errmsg}">
		<div class="alert alert-danger alert-dismissible">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>Error!</strong> ${errmsg }
		</div>
		</c:if>
		<c:if test="${not empty list}">
			<c:forEach items="${list}" var="paper" varStatus="loop">
				<div class="col-md-4 mt-1">
					<div class="card bg-info">
						<div class="card-body">
							<h5 class="card-title">${loop.index +1 }) Paper Code:- ${paper.paperCode}<br>Subject:-${paper.subject.name}</h5>
							<h6 class="card-subtitle mb-2">Paper Timing:-${paper.paperTiming}</h6>
							<p>Status:-<c:if test="${paper.enabled}">Enabled</c:if>
							<c:if test="${!paper.enabled}">disabled</c:if> </p>
							<!-- <p class="card-text">Some quick example text to build on the
								card title and make up the bulk of the card's content.</p> -->
							<a href="/Admin/allQuestion/${paper.paperId}" class="text-white">List All Questions</a>
						</div>
					</div>
				</div>
			</c:forEach>
			</c:if>
		</div>
	<jsp:include page="../footerLink.jsp" />
</body>
</html>