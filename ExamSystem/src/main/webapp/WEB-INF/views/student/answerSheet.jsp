<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Exam System</title>
<jsp:include page="../headerLink.jsp" />
</head>
<body id="body">
<div class="container">
		<jsp:include page="../menuBar.jsp" />
			<c:if test="${not empty studPaper.paper.questionList}">
			<c:forEach items="${studPaper.paper.questionList}" var="question" varStatus="loop">
				<div class="col-12 mt-1">
					<div class="card border-info pl-3">
						<div class="card-head">
							<pre>${loop.index +1 }) ${question.fullQuestion}</pre>
						</div>
						<div class="card-body">
							<c:forEach items="${question.optionList}" var="option"
								varStatus="loop">
								<c:if test="${option.opt == question.correctOption}">
									<div class="alert alert-success"><pre>${option.opt}. ${option.answer}</pre></div>
								</c:if>
								<c:if test="${option.opt != question.correctOption}">
									<div class="alert alert-secondary"><pre>${option.opt}. ${option.answer}</pre></div>

								</c:if>
							</c:forEach>
							<button class="btn btn-light" data-toggle="collapse"
								data-target="#demo${loop.index +1}">Description</button>

							<div id="demo${loop.index +1}" class="collapse">
								${question.description}</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
		</div>
	<jsp:include page="../footerLink.jsp" />
</body>
</html>