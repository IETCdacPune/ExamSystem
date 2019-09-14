<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Paper of Subject</title>
<jsp:include page="../headerLink.jsp" />
</head>
<body>
	<div class="container">
		<jsp:include page="../admin/menuBar.jsp" />
		<br> <br>
		<c:if test="${not empty errmsg}">
		<div class="alert alert-danger alert-dismissible">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>Error!</strong> ${errmsg }
		</div>
		</c:if>
		<c:if test="${not empty list}">
			<c:forEach items="${list}" var="question" varStatus="loop">
				<div class="col-12 mt-1">
					<div class="card border-info">
						<div class="card-head"><h5 class="card-title">  &nbsp;${loop.index +1 }) ${question.question}</h5></div>
						<div class="card-body">
							<c:forEach items="${question.optionList}" var="option" varStatus="loop">
								<c:if test="${option.option == question.correctOption}">
									<div class="alert alert-success" role="alert">
									  <p>${option.option} ${option.answer}</p>
									</div>
								</c:if>
								<c:if test="${option.option != question.correctOption}">
									<div class="alert alert-secondary" role="alert">
									  <p>${option.option} ${option.answer}</p>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:forEach>
			</c:if>
		</div>
	<jsp:include page="../footerLink.jsp" />
</body>
</html>